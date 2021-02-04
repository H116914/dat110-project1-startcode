package no.hvl.dat110.rpc;


import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

public class RPCUtils {

	// Utility methods for marshalling and marshalling of parameters and return values
	// in RPC request and RPC responses
	// data bytearrays and return byte arrays is according to the 
	// RPC message syntax [rpcid,parameter/return value]
	
	public static byte[] marshallString(byte rpcid, String str) {
		
		// TODO: marshall RPC identifier and string into byte array
		
		byte[] encoded;

		byte[] strByte = str.getBytes();
		
		encoded = new byte[strByte.length+1];
		encoded[0] = rpcid;
		for(int i = 1; i < strByte.length+1; i++) {
			encoded[i] = strByte[i-1];
		}
		
		return encoded;
	}

	public static String unmarshallString(byte[] data) {

		// TODO: unmarshall String contained in data into decoded
		
		byte[] dataUtenID = fjernMetodeByte(data);
		String decoded = new String(dataUtenID, StandardCharsets.UTF_8);

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

	public static byte[] marshallInteger(byte rpcid, int x) {

		byte[] encoded;

		byte[] intByte = BigInteger.valueOf(x).toByteArray();
		
		encoded = new byte[intByte.length+1];
		encoded[0] = rpcid;
		for(int i = 1; i < intByte.length+1; i++) {
			encoded[i] = intByte[i-1];
		}
		
		return encoded;
	}

	public static int unmarshallInteger(byte[] data) {

		int decoded;
		
		byte[] dataUtenID = fjernMetodeByte(data);
		decoded = ByteBuffer.wrap(addPadding(dataUtenID)).getInt();
		
		return decoded;

	}
	
	private static byte[] addPadding(byte[] intBytes) {
		int nodvendigLengde = 4;
		byte[] paddedBytes;
		
		if(intBytes.length < nodvendigLengde) {
			paddedBytes = new byte[nodvendigLengde];
			
			for(int i = 0; i < nodvendigLengde-intBytes.length; i++) {
				paddedBytes[i] = 0;
			}
			for(int i = nodvendigLengde-intBytes.length; i < nodvendigLengde; i++) {
				paddedBytes[i] = intBytes[i-(nodvendigLengde-intBytes.length)];
			}
		}else {
			paddedBytes = intBytes;
		}
		return paddedBytes;
	}

	private static byte[] fjernMetodeByte(byte[] dataInn) {
		byte[] dataUt = new byte[dataInn.length-1];
		
		for(int i = 0; i < dataUt.length; i++) {
			dataUt[i] = dataInn[i+1];
		}
		
		return dataUt;
	}
}
