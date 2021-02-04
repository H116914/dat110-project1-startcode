package no.hvl.dat110.system.controller;

import java.io.IOException;

import no.hvl.dat110.rpc.*;

public class Display extends RPCStub {

	private byte RPCID = 1;

	public void write(int message) {
		byte[] request = null;
		
		try {
			request = RPCUtils.marshallInteger(RPCID, message);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		super.rpcclient.call(request);
	}
}
