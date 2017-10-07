package com.merchantcollection.controller;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.merchantcollection.exceptions.DataNotFoundException;
import com.merchantcollection.exceptions.InsufficientDataException;
import com.merchantcollection.service.UPIService;

/**
 * Controller of MerchantAlertSubSystem which will receive request from callback
 * url, send for further processing and returns response to merchant
 */
@RestController
@RequestMapping("/collections")
public class UPIController {

	static Logger lg = Logger.getLogger(UPIController.class.getName());
	@Autowired
	UPIService upiService;

	@Value("${Bank.List}")
	private String bankList;

	@Value("${Bank.Data}")
	private String bankData;

	public JSONObject bankJSON;
	public JSONParser parser;
	public Resource resourceList;
	public Resource resourceData;
	public Object bankListObj;
	public Object bankDataObj;
	public JSONObject bankListJson = null;
	public JSONObject bankDataJson = null;
	public JSONObject issuerJson = null;

	/**
	 * This method will load before completion of startup of server and fetches
	 * the entries from json file like bank list and their corresponding keys,
	 * which will be used furthe in the application
	 */
	@PostConstruct
	public void load() {
		try {
			parser = new JSONParser();
			resourceList = new ClassPathResource(bankList);
			resourceData = new ClassPathResource(bankData);

			if (resourceList.exists() || resourceData.exists()) {
				bankListObj = parser.parse(new FileReader(resourceList.getFile()));
				bankListJson = (JSONObject) bankListObj;

				bankDataObj = parser.parse(new FileReader(resourceData.getFile()));
				bankDataJson = (JSONObject) bankDataObj;

				if (bankListJson.size() != bankDataJson.size()) {
					lg.debug("Insufficient data");
					throw new InsufficientDataException();
				}
			} else {
				lg.debug("Bank List or Data File not found");
				throw new FileNotFoundException();
			}
		} catch (IOException | ParseException | InsufficientDataException e) {
			e.printStackTrace();
			lg.debug(e.getMessage());
		}
	}

	/**
	 * This method binds Data received at callback url to the json parameters
	 * mapped to database.
	 * 
	 * @param issuer
	 *            : This variable indicates Issuers name from whom request is
	 *            received
	 * @param txnDetails
	 *            : Input parameter received from Callback URL.
	 * @return Map<String, String> which will contain the data mapped from
	 *         request to the map as per the DB.
	 */
	public Map<String, String> jsonToMapBinding(String issuer, ModelMap txnDetails) {
		Map<String, String> issuerMap = new HashMap<String, String>();
		try {
			if (!bankDataJson.containsKey(bankListJson.get(issuer))) {
				lg.debug("Data does not exist");
				throw new DataNotFoundException();
			} else {
				issuerJson = (JSONObject) bankDataJson.get(bankListJson.get(issuer));
				issuerMap.put((String) issuerJson.get("amount"), (String) txnDetails.get(issuerJson.get("amount")));
				issuerMap.put((String) issuerJson.get("txnReferenceNum"),
						(String) txnDetails.get(issuerJson.get("txnReferenceNum")));
				if (issuer.equals("kotakBQR")) {
					JSONArray merId = (JSONArray) issuerJson.get("mrechantIdentifier");
					@SuppressWarnings("unchecked")
					Iterator<String> iterator = merId.iterator();
					while (iterator.hasNext()) {
						String merchant = iterator.next();
						if (txnDetails.get(merchant).equals("") || txnDetails.get(merchant).equals(null)) {

						} else {
							issuerMap.put(merchant, (String) txnDetails.get(merchant));
							issuerMap.put((String) issuerJson.get("status"), "SUCCESS");
							issuerMap.put((String) issuerJson.get("custIdentifier"), "NA");
							break;
						}
					}
				} else {
					issuerMap.put((String) issuerJson.get("mrechantIdentifier"),
							(String) txnDetails.get(issuerJson.get("mrechantIdentifier")));
					issuerMap.put((String) issuerJson.get("status"), (String) txnDetails.get(issuerJson.get("status")));
					issuerMap.put((String) issuerJson.get("custIdentifier"),
							(String) txnDetails.get(issuerJson.get("custIdentifier")));
				}
				issuerMap.put((String) issuerJson.get("remarks"), (String) txnDetails.get(issuerJson.get("remarks")));
				issuerMap.put("bankName", (String) issuerJson.get("bankName"));
				issuerMap.put("issuerName", (String) issuerJson.get("issuerName"));
				issuerMap.put("bankInput", txnDetails.toString());
			}
		} catch (DataNotFoundException e) {
			e.printStackTrace();
			lg.debug(e.getMessage());
		}
		return issuerMap;
	}

	/**
	 * @param txnDetails
	 *            : Input parameter received from Callback URL.
	 * @return Response Entity Object with Status and Message Description
	 */
	@RequestMapping(value = "/kotakUPI", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<String> kotakUPICollection(@RequestBody ModelMap txnDetails) {
		try {
			Map<String, String> kotakMap = new HashMap<String, String>();
			kotakMap = jsonToMapBinding("kotakUPI", txnDetails);
			upiService.storeRequest(kotakMap, issuerJson);
			return new ResponseEntity<String>("SUCCESS", HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			lg.debug(e.getMessage());
		}
		return null;
	}
	
	/**
	 * @param txnDetails
	 *            : Input parameter received from Callback URL.
	 * @return Response Entity Object with Status and Message Description
	 */
	@RequestMapping(value = "/kotakBQR", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<String> kotakBQRCollection(@RequestBody ModelMap txnDetails) {
		try {
			Map<String, String> kotakBQRMap = new HashMap<String, String>();
			kotakBQRMap = jsonToMapBinding("kotakBQR", txnDetails);
			upiService.storeRequest(kotakBQRMap, issuerJson);
			return new ResponseEntity<String>("SUCCESS", HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			lg.debug(e.getMessage());
		}
		return null;
	}
	
	/**
	 * @param txnDetails
	 *            : Input parameter received from Callback URL.
	 * @return Response Entity Object with Status and Message Description
	 */
	@RequestMapping(value = "/atomBQR", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<String> atomBQRCollection(@RequestBody ModelMap txnDetails) {
		try {
			Map<String, String> atomMap = new HashMap<String, String>();
			atomMap = jsonToMapBinding("atomBQR", txnDetails);
			upiService.storeRequest(atomMap, issuerJson);
			return new ResponseEntity<String>("SUCCESS", HttpStatus.CREATED);
		} catch (Exception e) {
			e.printStackTrace();
			lg.debug(e.getMessage());
		}
		return null;
	}
}