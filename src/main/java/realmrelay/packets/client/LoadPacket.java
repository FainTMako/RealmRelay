package realmrelay.packets.client;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class LoadPacket extends ClientPacket {
	
	public int charId;
	public boolean isFromArena;

	@Override
	public void parseFromInput(DataInput in) throws IOException {
		this.charId = in.readInt();
		this.isFromArena = in.readBoolean();
	}

	@Override
	public void writeToOutput(DataOutput out) throws IOException {
		out.writeInt(this.charId);
		out.writeBoolean(this.isFromArena);
	}

}
