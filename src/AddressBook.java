import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class AddressBook {
	private boolean state = false;
	private Map<Person, Address> addressBook = new HashMap<Person, Address>();
	private static OutputInput outputInput = new OutputInput();
	
	public void addContact(Person p, Address a){
			this.addressBook.put(p, a);
	}
	
	public boolean removeContact(Person p){
		if(hasContact(p)){
			this.addressBook.remove(p);
			return true;
		}
		return false;
	}
	
	public boolean editContact(Person newP, Address newA, Person oldP){
		if(newP != null && oldP != null){
			addressBook.put(newP, addressBook.remove(oldP));
			if(newA != null){
				addressBook.put(newP, newA);
				return true;
			}
			return true;
		}
		return false;
	} 
	
	public boolean hasContact(Person p) {		
/*		for (Map.Entry<Person, Address> entry : addressBook.entrySet())
		{
			String nameToDelete = p.getFirstName();
			String nameToCompare = entry.getKey().getFirstName();
			if(nameToDelete != null && nameToCompare != null && nameToDelete.equals(nameToCompare)){
				return true;
			}else{
				return false;
			}
		}
		return false;*/
		if(addressBook.containsKey(p)){
			return true;
		}
		return false;	
    }
	
	public void printHashMap(){
		for (Map.Entry<Person, Address> entry : addressBook.entrySet())
		{
			System.out.println("- Contact -");
		    System.out.println(entry.getKey().getFirstName() + " " + entry.getKey().getSecondName());
		    System.out.println("- Address -");
		    System.out.println(entry.getValue().getAddressLine01() + " , " + entry.getValue().getCounty());
		}
	}
	
	public void outputAddressBook(){
		outputInput.outputFileToCSV(addressBook);
	}

	public Map<Person, Address>readAddressBookFile(){
		try {
			return addressBook = outputInput.readCSVFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return addressBook;
	}


	public Map<Person, Address> getAddressBook() {
			return addressBook;
	}
	
	public boolean isState() {
		return state;
	}

	public OutputInput getOutputInput() {
		state= true;
		return outputInput;
	}

}
