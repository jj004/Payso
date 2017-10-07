package com.merchantcollection.service;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.SMSUtility.SMSUtility;
import com.merchantcollection.dao.IMerchantContactDetailsDao;
import com.merchantcollection.dao.IMerchantTerminalDao;
import com.merchantcollection.dao.UPIDao;
import com.merchantcollection.entity.MerchantContactDetails;
import com.merchantcollection.entity.MerchantTerminal;
import com.merchantcollection.entity.TransactionDetails;
import com.merchantcollection.exceptions.DataNotFoundException;
import com.merchantcollection.exceptions.DatabaseTransactionException;

/**
 * This Service layer will process data in the map and stores transaction into
 * database for further use and also sends notification SMS to merchant
 */
@Service
public class UPIServiceImpl implements UPIService {

	static Logger lg = Logger.getLogger(UPIServiceImpl.class.getName());

	@Value("${NAME}")
	private String name;

	@Value("${TXNID}")
	private String txnId;

	@Value("${SUCCESSSTART}")
	private String successStart;

	@Value("${SUCCESSEND}")
	private String successEnd;

	@Value("${FAILEDSTART}")
	private String failedStart;

	@Value("${FAILEDEND}")
	private String failedEnd;

	@Autowired
	UPIDao upiDao;

	@Autowired
	IMerchantTerminalDao merTerminalDao;

	@Autowired
	IMerchantContactDetailsDao merContactDao;

	/**
	 * This method will store transaction data into DB and also send SMS
	 * notification to merchant.
	 */
	@Override
	public void storeRequest(Map<String, String> input, JSONObject requestKey) {
		// SMSUtils smsUtils = new SMSUtils();
		List<MerchantTerminal> merchantTerminal = null;
		try {
			TransactionDetails txnDetails = new TransactionDetails();
			/**
			 * fetch the correct merchant terminal from database as per the
			 * merchant identifier received.
			 */
			if (input.get("issuerName").equals("KotakBQR")) {
				JSONArray merId = (JSONArray) requestKey.get("mrechantIdentifier");
				@SuppressWarnings("unchecked")
				Iterator<String> iterator = merId.iterator();
				while (iterator.hasNext()) {
					String merchant = iterator.next();
					lg.debug("Merchant Identifier Value = " + merchant);
					if (input.get(merchant).equals("") || input.get(merchant).equals(null)) {

					} else {
						merchantTerminal = merTerminalDao.findByMpanOrVpanOrRpan(input.get(merchant));
						break;
					}
				}
			} else {
				merchantTerminal = merTerminalDao
						.findByVpaOrTerminalId(input.get(requestKey.get("mrechantIdentifier")));
			}

			MerchantTerminal merTerminal = new MerchantTerminal();
			Iterator<MerchantTerminal> it = merchantTerminal.iterator();

			while (it.hasNext()) {
				merTerminal = it.next();
				if (merTerminal != null) {
					/**
					 * Create object of transaction entity with data to store
					 * into transaction table.
					 */
					txnDetails.setAmount(input.get(requestKey.get("amount")));
					txnDetails.setMerchantTerminal(merTerminal);
					txnDetails.setPayerVpa(input.get(requestKey.get("custIdentifier")));
					txnDetails.setRemarks(input.get(requestKey.get("remarks")));
					txnDetails.setStatus(input.get(requestKey.get("status")));
					txnDetails.setTxnReferenceNum(input.get(requestKey.get("txnReferenceNum")));
					txnDetails.setBankName(input.get("bankName"));
					txnDetails.setIssuerName(input.get("issuerName"));
					txnDetails.setBankInput(input.get(requestKey.get("bankInput")));
					/**
					 * Save transaction data into txn_details table.
					 */
					TransactionDetails txnResult = upiDao.save(txnDetails);
					/**
					 * create message and fetch mobile number of merchant
					 * terminal to send notification.
					 */
					if (txnResult != null) {
						String mobileNumber = "";
						String msg = "";
						List<MerchantContactDetails> merContactList = merContactDao.findByMerchantTerminal(merTerminal);
						MerchantContactDetails merContact = new MerchantContactDetails();
						Iterator<MerchantContactDetails> iterator = merContactList.iterator();
						
						while (iterator.hasNext()) {
							merContact = iterator.next();
							if (merContact != null) {
								mobileNumber = merContact.getMobileNumber();
								lg.debug("Mobile Number : "+mobileNumber);
								if (mobileNumber.isEmpty()) {
									lg.debug("Mobile Number not found");
									throw new DataNotFoundException();
								} else {
									lg.debug("Txn Details fetched" + txnResult.getStatus());
									if (txnResult.getStatus().equals("SUCCESS")) {
										/*
										 * Send SMS to merchant if transaction is successful.
										 */
										lg.debug("Payment Success! SMS Init" + txnResult.getUpdated());
										msg = name + " " + successStart + " " + txnDetails.getAmount() + " "
												+ successEnd + " " + txnDetails.getUpdated() + " " + txnId + " "
												+ txnDetails.getTxnReferenceNum();

										lg.debug("Mobile Number = " + mobileNumber + "\n Message = " + msg);

										// boolean smsResult =
										// smsUtils.sendSMS(mobileNumber, msg);
										boolean smsResult = SMSUtility.sendSMS(mobileNumber, msg);

										if (smsResult) {
											lg.debug("SMS SUCCESS");
										} else {
											lg.debug("SMS FAILED");
										}
									} else {
										/**
										 * Send SMS to merchant if transaction is failed.
										 */
										lg.debug("Payment Failed! SMS Init");
										msg = name + " " + failedStart + " " + txnDetails.getAmount() + " " + txnId
												+ " " + txnDetails.getTxnReferenceNum() + " " + failedEnd;

										lg.debug("Mobile Number = " + mobileNumber + "\n Message = " + msg);

										boolean smsResult = SMSUtility.sendSMS(mobileNumber, msg);
										if (smsResult) {
											lg.debug("SMS SUCCESS");
											lg.info("SMS SUCCESS");
										} else {
											lg.debug("SMS FAILED");
											lg.info("SMS FAILED");
										}
									}
								}
							} else {
								lg.debug("Data Not Found in Object");
								throw new DataNotFoundException();
							}
						}
					} else {
						lg.debug("Txn Not Stored");
						throw new DatabaseTransactionException();
					}
				} else {
					lg.debug("Merchant Code/Merchant Termninal Not Found");
					throw new DataNotFoundException();
				}
			}
		} catch (DataNotFoundException | DatabaseTransactionException e) {
			e.printStackTrace();
		}
	}
}