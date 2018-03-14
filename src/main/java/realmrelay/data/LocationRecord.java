package realmrelay.data;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;


public class LocationRecord extends Location {
	
	public int time;

        public LocationRecord()
        {
        }

    public LocationRecord(int _arg_1, double _arg_2, double _arg_3)
    {
        this.time = _arg_1;
        this.x = (float) _arg_2;
        this.y = (float) _arg_3;
    }
        
        
        
        
        
	
	@Override
	public void parseFromInput(DataInput in) throws IOException {
		this.time = in.readInt();
		super.parseFromInput(in);
	}
	
	@Override
	public void writeToOutput(DataOutput out) throws IOException {
		out.writeInt(this.time);
		super.writeToOutput(out);
	}

}
