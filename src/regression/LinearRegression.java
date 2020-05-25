package regression;
import org.apache.commons.math3.linear.LUDecomposition;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;

public class LinearRegression {
	// Avem Regresia Liniara ca fiind definita sub forma hw(x)= wTx
	//si o ecutie normala de forma w=(xTx)-1 xTy
	                                                   
	private RealMatrix w,estimate;
	
    public LinearRegression(double[][] xArray, double[][] yArray) throws Exception {
    	//metoda creata o apelam in constructor.
    	applyNormalEquation(MatrixUtils.createRealMatrix(xArray),MatrixUtils.createRealMatrix(yArray));
    }
    //implementam o metoda care transforma o ecutia normala in doua matrici X,Y.
    private void applyNormalEquation(RealMatrix x, RealMatrix y) throws Exception {
    	LUDecomposition lUDecomposition = new LUDecomposition(x.transpose().multiply(x));//  multiplicarea 
    	if (lUDecomposition.getDeterminant() ==0.0) throw new Exception("singular matrix/ no inverse");
    // punem o exceptie in caz ca X transpus  nu are un invers
    	//  determinatul este 0 se activeaza exceptia
    	else {w= lUDecomposition.getSolver().getInverse().multiply((x.transpose().multiply(y)));}
        estimate = x.multiply(w);
        
    }
    public double estimateRent(String entry) {
    	return MatrixUtils.createColumnRealMatrix(new double [] {1,Double.valueOf(entry)}).transpose().multiply(w).getData()[0][0];
    	//returnam chiria pe baza metrilor patrati
    }
    //creem un getter pentru ambele 
     public RealMatrix getW() { return w;}
     public RealMatrix getEstimate() {return estimate;}
}
