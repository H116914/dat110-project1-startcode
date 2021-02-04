package no.hvl.dat110.system.controller;

import no.hvl.dat110.rpc.*;

public class Display extends RPCStub {

	private byte RPCID = 1;

	public void write(int message) {
		
		byte[] request = RPCUtils.marshallInteger(RPCID, message);
		
		super.rpcclient.call(request);
	}
}
