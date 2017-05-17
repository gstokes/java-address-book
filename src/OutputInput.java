
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public class OutputInput {
	private Map<Person, Address> addressbook = new ConcurrentHashMap<Person, Address>();
	//Delimiter used in CSV file
	private static final String COMMA_DELIMITER = ",";
	private static final String NEW_LINE_SEPARATOR = "\n";

	//CSV file header
	private static final String FILE_HEADER = "firstName,secondName,addressLine1,county,country,postalCode,phoneNumber,email";
	
	public void outputFileToCSV(Map<Person, Address> addressBook) {
		System.out.println("About to output address book to file: ");

		FileWriter fileWriter = null;
		String fileName = "tmp/temp.csv";

		try {
			fileWriter = new FileWriter(fileName);

			//Write the CSV file header
			fileWriter.append(FILE_HEADER.toString());

			//Add a new line separator after the header
			fileWriter.append(NEW_LINE_SEPARATOR);

			//Write address book to CSV file
			for (Map.Entry<Person, Address> entry : addressBook.entrySet()) {
				fileWriter.append(String.valueOf(entry.getKey().getFirstName()));
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(String.valueOf(entry.getKey().getSecondName()));
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(String.valueOf(entry.getValue().getAddressLine01()));
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(String.valueOf(entry.getValue().getCounty()));
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(String.valueOf(entry.getValue().getCountry()));
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(String.valueOf(entry.getValue().getPostalCode()));
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(String.valueOf(entry.getValue().getPhoneNumber()));
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(String.valueOf(entry.getValue().getEmail()));
				fileWriter.append(NEW_LINE_SEPARATOR);
			}
			System.out.println("CSV file was created successfully !!!");

		} catch (Exception e) {
			System.out.println("Error in CsvFileWriter !!!");
			e.printStackTrace();
		} finally {
			try {
				fileWriter.flush();
				fileWriter.close();
			} catch (IOException e) {
				System.out.println("Error while flushing/closing fileWriter !!!");
				e.printStackTrace();
			}
		}
	}
	
	public Map<Person, Address> readCSVFile() throws IOException{
		BufferedReader br = null;  
		String line = "";
		String splitBy = ","; 
		List<Person> personList = new ArrayList<Person>();  
		List<Address> addressList = new ArrayList<Address>();  
		
		try{
			br = new BufferedReader(new FileReader("tmp/temp.csv"));
			
			br.readLine(); //skip header line
			while ((line = br.readLine()) != null) 
			{  
				String[] csvInput = line.split(splitBy);

				Person personObj = new Person(csvInput[0], csvInput[1]);
				personList.add(personObj);
				
				Address addressObj =  new Address(csvInput[2], csvInput[3], csvInput[4], csvInput[5], csvInput[6], csvInput[7]);
				addressList.add(addressObj);
				
				addressbook.put(personObj, addressObj );
			}
			br.close();
			
		}catch (Exception e) {
            System.err.println("CSV file cannot be read : " + e);
          }
		return addressbook;
 }
		


	
	public void output(Map<Person, Address> addressBook){
		System.out.println("About to output address book to file: ");
		
		try {
	        FileOutputStream fileOut = new FileOutputStream("tmp/temp.ser");
	        ObjectOutputStream out = new ObjectOutputStream(fileOut);
	        out.writeObject(addressBook);
	        out.close();
	        fileOut.close();
	        System.out.printf("Serialized data has been saved to tmp/temp");
	      }catch(IOException i) {
	         i.printStackTrace();
	      }
	}
	
	public Map<Person, Address> input(){
		try {
	         FileInputStream fileIn = new FileInputStream("tmp/temp.ser");
	         ObjectInputStream in = new ObjectInputStream(fileIn);
	         addressbook = (Map<Person, Address>) in.readObject();
	         in.close();
	         fileIn.close();
	      }catch(IOException i) {
	         i.printStackTrace();
	         return null;
	      }catch(ClassNotFoundException c) {
	         System.out.println("Address book not found");
	         c.printStackTrace();
	         return null;
	      }
		return addressbook;
	}
}
