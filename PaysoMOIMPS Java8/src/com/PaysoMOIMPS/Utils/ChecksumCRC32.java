package com.PaysoMOIMPS.Utils;

import java.nio.charset.Charset;
import java.util.zip.CRC32;

public class ChecksumCRC32 {
	public static long generateChecksum(String str){
		long cksum = 0;	
		byte[] bytes;
		bytes = str.getBytes(Charset.forName("UTF-8"));
		CRC32 crc32 = new CRC32();
		crc32.update(bytes);
		cksum = crc32.getValue();
		return cksum;
	}
}
