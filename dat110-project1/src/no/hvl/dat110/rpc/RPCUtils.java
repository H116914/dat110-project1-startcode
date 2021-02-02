package no.hvl.dat110.rpc;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.util.Arrays;

import no.hvl.dat110.TODO;

public class RPCUtils {

	// Utility methods for marshalling and marshalling of parameters and return values
	// in RPC request and RPC responses
	// data bytearrays and return byte arrays is according to the 
	// RPC message syntax [rpcid,parameter/return value]
	
	public static byte[] marshallString(byte rpcid, String str) throws IOException {
		
		// TODO: marshall RPC identifier and string into byte array
		
		byte[] encoded;

		byte[] strBytes = str.getBytes();
		encoded = new byte[] {rpcid};
		
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		outputStream.write(encoded);
		outputStream.write(strBytes);
		
		encoded	= outputStream.toByteArray();
		

		return encoded;
	}

	public static String unmarshallString(byte[] data) {

		// TODO: unmarshall String contained in data into decoded
		
		String decoded;

		byte[] newData = Arrays.copyOfRange(data, 1, data.length);
		decoded = new String(newData);

		return decoded;
	}

	public static byte[] marshallVoid(byte rpcid) {

		byte[] encoded;

		// TODO: marshall RPC identifier in case of void type
		
		encoded = new byte[] {rpcid};

		return encoded;

	}

	public static void unmarshallVoid(byte[] data) {

		// TODO: unmarshall void type
		
		// gjør tydeligvis ingenting.
	}
	
	

	public static byte[] marshallBoolean(byte rpcid, boolean b) {

		byte[] encoded = new byte[2];

		encoded[0] = rpcid;

		if (b) {
			encoded[1] = 1;
		} else {
			encoded[1] = 0;
		}

		return encoded;
	}

	public static boolean unmarshallBoolean(byte[] data) {

		return (data[1] > 0);

	}

	public static byte[] marshallInteger(byte rpcid, int x) throws IOException {

		byte[] encoded;

		Integer a = x; 
		
		encoded = new byte[] {rpcid, a.byteValue()};
		
		return encoded;
	}

	public static int unmarshallInteger(byte[] data) {

		int decoded;

		// TODO: unmarshall integer contained in data

		byte[] bytes = Arrays.copyOfRange(data, 1, data.length);
		
		decoded = new BigInteger(1,bytes).intValue();
		return decoded;

	}
}
