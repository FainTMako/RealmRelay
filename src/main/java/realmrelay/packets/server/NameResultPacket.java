package realmrelay.packets.server;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import realmrelay.packets.Packet;


public class NameResultPacket extends ServerPacket {
	
	public boolean success;
	public String errorText;

	@Override
	public void parseFromInput(DataInput in) throws IOException {
		this.success = in.readBoolean();
		this.errorText = in.readUTF();
	}

	@Override
	public void writeToOutput(DataOutput out) throws IOException {
		out.writeBoolean(this.success);
		out.writeUTF(this.errorText);
	}

}
