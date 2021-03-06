package realmrelay.packets.client;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import realmrelay.packets.Packet;


public class JoinGuildPacket extends ClientPacket {
	
	public String guildName;

	@Override
	public void parseFromInput(DataInput in) throws IOException {
		this.guildName = in.readUTF();
	}

	@Override
	public void writeToOutput(DataOutput out) throws IOException {
		out.writeUTF(this.guildName);
	}

}
