package no.hvl.dat110.messaging;

public class Message {

	private byte[] payload;
	private int payloadLengde; 

	public Message(byte[] payload) {
		if (payload.length <=127) {
			this.payload = payload; // TODO: check for length within boundary
			payloadLengde = payload.length;
		}
	}

	public Message() {
		super();
	}

	public byte[] getData() {
		return this.payload; 
	}

	public byte[] encapsulate() {
		
		byte[] encoded = new byte[128];
		encoded[0]=(byte) payloadLengde;
		for(int i =0; i<payloadLengde; i++) {
			encoded[i+1] = payload[i];
		}
		return encoded;
		
	}

	public void decapsulate(byte[] received) {
		payloadLengde = received[0];
		payload=new byte[payloadLengde];

		for(int i=0; i<payloadLengde; i++) {
			payload[i] = received[i+1];
		}
		
	}
}
