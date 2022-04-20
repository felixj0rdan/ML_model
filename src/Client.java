import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URISyntaxException;

import scala.sys.process.ProcessBuilderImpl.FileInput;
import smile.classification.RandomForest;
import smile.data.DataFrame;
import smile.io.Read;

public class Client {

	public static void main(String[] args) throws IOException, ClassNotFoundException, URISyntaxException, FileNotFoundException {
		// TODO Auto-generated method stub
		
		String filename = "src/files/Forest.txt";
	    
	    File file = new File(filename);
		
		FileInputStream fis = new FileInputStream(file);
		ObjectInputStream ois =  new  ObjectInputStream(fis);
		
		TestSmile model = (TestSmile) ois.readObject();
		
		System.out.println(model.forest);	
		
		DataFrame irisTest = Read.csv("src/files/IrisTest.csv");
		
		model.testModel(irisTest);
			
	}
}

// @473b46c3
