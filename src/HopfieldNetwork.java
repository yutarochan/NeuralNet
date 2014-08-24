import org.ejml.simple.SimpleMatrix;

public class HopfieldNetwork {

	private SimpleMatrix hopmat;
	private int datasize = 0;
	
	public HopfieldNetwork(int datasize) {
		this.datasize = datasize;
	}
	
	public void train(String pattern) {
		double[][] pmat = new double[pattern.length()][1];
		for (int i = 0; i < pmat.length; i++)
			pmat[i][0] = Character.getNumericValue(pattern.charAt(i));
		
		SimpleMatrix cmat = new SimpleMatrix(pmat);
		convertToBipolar(cmat);
		
		SimpleMatrix tmat = cmat.copy().transpose();
		
		// Generate Identity Matrix
		double[][] idmat = new double[datasize][datasize];
		for (int i = 0; i < datasize; i++) {
			for (int j = 0; j < datasize; j++) {
				if (i == j) idmat[i][j] = 1;
				else idmat[i][j] = 0;
			}
		}
		
		hopmat = (cmat.mult(tmat)).minus(new SimpleMatrix(idmat));
		
		System.out.println("Training Result with pattern: " + pattern);
		hopmat.print();
	}
	
	public double toBipolar(double d) {
		return ((2 * d) - 1);
	}
	
	public int toBoolean(int num) {
		return (num + 1) / 2;
	}
	
	public SimpleMatrix convertToBipolar(SimpleMatrix mat) {
		for (int i = 0; i < mat.numRows(); i++)
			for (int j = 0; j < mat.numCols(); j++)
				mat.set(i, j, toBipolar(mat.get(i, j)));
		return mat;
	}
}
