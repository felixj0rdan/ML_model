import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;

import smile.data.DataFrame;
import smile.io.Read;

public class Server {
	
	private static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	public static void main(String[] args) throws IOException, URISyntaxException {
		// TODO Auto-generated method stub
		String irisTrainPath = "src/files/IrisTrain.csv";
		String irisTestPath = "src/files/IrisTest.csv";
		String filename = "src/files/Forest.txt";
		
		Server server = new Server();
		
		server.Train(irisTrainPath, irisTestPath, filename);
		
	}
	     
	void Train(String irisTrainPath, String irisTestPath, String filename) throws IOException, URISyntaxException {
		DataFrame irisTrain = Read.csv(irisTrainPath);
	    DataFrame irisTest = Read.csv(irisTrainPath);
	    
	    TestSmile model = new TestSmile();  // a new random forest model
	    
	    model.trainModel(irisTrain);  // to train the model
	    
//	    model.testModel(irisTest);    // now testing the model
	    	    
//	    model.testAccuracy("src/files/IrisAccu.csv");   // to compare the prediction with actual classifications
	    
	    model.modelMetrics();  // to produce the metrics of the model
	    
	    logger.log(Level.INFO, model.metrics.toString());
	    
	    
	    // now we will store the object in a text file to access later 
	    File file = new File(filename);
	    
	    // by using try with resources 
	    try(FileOutputStream fos = new FileOutputStream(file); ObjectOutputStream oos = new ObjectOutputStream(fos)){
	    	oos.writeObject(model);
	    }
	    catch (Exception e) {
			// TODO: handle exception
	    	System.out.println(e);
		}
    }
	
	void Query(String filename, String csvFile) throws IOException, URISyntaxException {
		// now we will load the previously loaded object 
				
			    
			    File file = new File(filename);
				
//				FileInputStream fis = new FileInputStream(file);
//				ObjectInputStream ois =  new  ObjectInputStream(fis);
			    
			    TestSmile model=null;
				
			    // try with resource
				try (FileInputStream fis = new FileInputStream(file); ObjectInputStream ois =  new  ObjectInputStream(fis)){
					// the object is stored in an object model  
					model = (TestSmile) ois.readObject();
				}
				catch (Exception e) {
					// TODO: handle exception
					System.out.println(e);
				}
				
				
				logger.log(Level.INFO, model.metrics.toString());
				
				// to load the testing data set
				DataFrame irisTest = Read.csv(csvFile);
				
				model.testModel(irisTest);
	}

}
