import java.io.DataOutputStream;
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
	    
	    TestSmile model = new TestSmile();
	    
	    model.trainModel(irisTrain);
	    model.testModel(irisTest);
	    	    
	    model.testAccuracy("src/files/IrisAccu.csv");
	    
	    model.modelMetrics();
	    
	    System.out.println(model.forest);
	    
	    String filename = "src/files/Forest.txt";
	    
	    File file = new File(filename);
	    
	    FileOutputStream fos = new FileOutputStream(file);
	    ObjectOutputStream oos = new ObjectOutputStream(fos);
	    
	    oos.writeObject(model);
	     
	}

}
