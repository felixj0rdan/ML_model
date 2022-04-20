import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.URISyntaxException;

import smile.data.DataFrame;
import smile.io.Read;

public class Server {

	public static void main(String[] args) throws IOException, URISyntaxException {
		// TODO Auto-generated method stub
		DataFrame irisTrain = Read.csv("src/files/IrisTrain.csv");
	    DataFrame irisTest = Read.csv("src/files/IrisTest.csv");

	    System.out.println(irisTrain);
	    
	    TestSmile model = new TestSmile();  // a new random forest model
	    
	    model.trainModel(irisTrain);  // to train the model
	    
	    model.testModel(irisTest);    // now testing the model
	    	    
	    model.testAccuracy("src/files/IrisAccu.csv");   // to compare the prediction with actual classifications
	    
	    model.modelMetrics();  // to produce the metrics of the model
	    
	    System.out.println(model.forest);
	    
	    
	    // now we will store the object in a text file to access later 
	    String filename = "src/files/Forest.txt";
	    
	    File file = new File(filename);
	    
	    FileOutputStream fos = new FileOutputStream(file);
	    ObjectOutputStream oos = new ObjectOutputStream(fos);
	    
	    oos.writeObject(model);
	     
	}

}
