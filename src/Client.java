import java.io.*;
import java.net.URISyntaxException;

public class Client {

	public static void main(String[] args) throws IOException, ClassNotFoundException, URISyntaxException, FileNotFoundException {
		// TODO Auto-generated method stub
		
		// server object to run Train() and Query()
		Server server = new Server();
		
		// to train from the client side
		String irisTrainPath = "src/files/IrisTrain.csv";
		String irisTestPath = "src/files/IrisTest.csv";
		String filename = "src/files/Forest.txt";
		
		server.Train(irisTrainPath, irisTestPath, filename);
		
		// to test from the client side
		String modelFilename = "src/files/Forest.txt";
		String csvFile = "src/files/IrisTest.csv";
		
		System.out.println("Test Results:");
		server.Query(modelFilename, csvFile);
			
	}
	
	
}

// @473b46c3
