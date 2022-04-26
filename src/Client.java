import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URISyntaxException;

import smile.data.DataFrame;
import smile.io.Read;

public class Client {

	public static void main(String[] args) throws IOException, ClassNotFoundException, URISyntaxException, FileNotFoundException {
		// TODO Auto-generated method stub
		
		// now we will load the previously loaded object 
		String filename = "src/files/Forest.txt";
	    
	    File file = new File(filename);
		
//		FileInputStream fis = new FileInputStream(file);
//		ObjectInputStream ois =  new  ObjectInputStream(fis);
	    
	    TestSmile model=null;
		
	    // try with resource
		try (FileInputStream fis = new FileInputStream(file); ObjectInputStream ois =  new  ObjectInputStream(fis)){
			// the object is stored in an object model  
			model = (TestSmile) ois.readObject();
		}
		
		
		
		System.out.println(model.forest);	
		
		// to load the testing data set
		DataFrame irisTest = Read.csv("src/files/IrisTest.csv");
		
		model.testModel(irisTest);
			
	}
}

// @473b46c3
