package realmrelay.packets.client;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import realmrelay.packets.Packet;


public class RequestTradePacket extends ClientPacket {
	
	public String name;

	@Override
	public void parseFromInput(DataInput in) throws IOException {
		this.name = in.readUTF();
	}

	@Override
	public void writeToOutput(DataOutput out) throws IOException {
		out.writeUTF(this.name);
	}

}
