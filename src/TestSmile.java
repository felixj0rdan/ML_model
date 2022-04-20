import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Serializable;
import java.util.*;

import smile.classification.*;
import smile.data.DataFrame;
import smile.data.formula.Formula;
import smile.validation.ClassificationMetrics;
import smile.validation.metric.Accuracy;

public class TestSmile implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	RandomForest forest;
	ClassificationMetrics metrics;
	double accuracy;
	int[] testResult;
	
	void trainModel(DataFrame irisTrain) {
			
		Formula formula = Formula.lhs("V5");
	    
	    System.out.println();
	    
	    Properties prop = new Properties();
	    prop.setProperty("smile.random.forest.trees", "200");
	    RandomForest forest = RandomForest.fit(formula, irisTrain, prop);
	    ClassificationMetrics metrics = forest.metrics();
	    this.forest = forest;
	    this.metrics = metrics;
	    
	}
	
	void testModel(DataFrame irisTest) {
		this.testResult = forest.predict(irisTest);
		for(int j: this.testResult)
	    	System.out.println(j);
	}
	
	int[] testAccuracy(String fileToVerify) throws FileNotFoundException {
		int[] iAccu = this.csvToArr(fileToVerify);
	    this.accuracy = Accuracy.of(testResult, iAccu)*100.0;
	    return iAccu;
	}
	
	void modelMetrics() {
		System.out.format("OOB error rate = %.2f%%%n", (this.metrics.accuracy));
		System.out.println("Accuracy= "+this.accuracy);   
	}
	
	int[] csvToArr(String path) throws FileNotFoundException {
		Scanner obj = new Scanner(new BufferedReader(new FileReader(path)));
		int i = 0;
	    int[] iAccu = new int[this.testResult.length];
	    while(obj.hasNextLine()) {
	    	iAccu[i] = Integer.parseInt(obj.nextLine());
	    	i++;
	    }
	    return iAccu;
	}

}
