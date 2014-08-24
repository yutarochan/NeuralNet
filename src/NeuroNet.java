
public class NeuroNet {
	
	public static void main(String[] args) {
		HopfieldNetwork hopnet = new HopfieldNetwork(4);
		hopnet.train("0101");
	}
	
}
