import java.util.Scanner;

public class MainApp {
	private static Scanner scanner = new Scanner(System.in);
	private static AddressBook addressBook = new AddressBook();
	
	public static void main(String[] args) {
		 boolean quit = false;
	        int choice = 0;
	        
	        //initial load of any contacts
	        //readAddressBook();

	        printInstructions();
	        while (!quit) {
	            System.out.println("\nEnter your choice: ");
	            choice = scanner.nextInt();
	            scanner.nextLine();

	            switch (choice) {
	                case 0:
	                    printInstructions();
	                    break;
	                case 1:
	                    addContact();
	                    break;
	                case 2:
	                    printContacts();
	                    break;
	                case 3:
	                    modifyContact();
	                    break;
	                case 4:
	                    removeContact();
	                    break;
	                case 5:
	                    searchForContact();
	                    break;
	                case 6:
	                	outputAddressBook();
	                    break;
	                case 7:
	                	readAddressBook();
	                    break;
	                case 8:
	                	quitAddressBook();
	                    break;
	            }
	        }
	}
	
    private static void printInstructions() {
        System.out.println("\nPress ");
        System.out.println("\t 0 - To print choice options.");
        System.out.println("\t 1 - To add Contact.");
        System.out.println("\t 2 - To print Contacts.");
        System.out.println("\t 3 - To modify a Contact.");
        System.out.println("\t 4 - To remove a Contact.");
        System.out.println("\t 5 - To search for a Contact.");
        System.out.println("\t 6 - Output to file.");
        System.out.println("\t 7 - Read in file.");
        System.out.println("\t 8 - To quit the application.");
    }
    
    private static void addContact(){
    	//Input contact
    	System.out.println("Please enter the contacts first name:");
    	String firstName = scanner.nextLine();
    	System.out.println("Please enter the contacts surname name: ");
    	String surName = scanner.nextLine();
    	Person person = new Person(firstName, surName );
    	
    	if(addressBook.hasContact(person)){
    		System.out.println("Contact is already on file");
    	}else{
    	
	    	//Input address
	    	System.out.println("Enter first line of address: ");
	    	String addressLine01= scanner.nextLine();
	    	System.out.println("Enter county: ");
	    	String county= scanner.nextLine();
	    	System.out.println("Enter country: ");
	    	String country = scanner.nextLine();
	    	System.out.println("Enter Postal code: ");
	    	String postalcode= scanner.nextLine();
	    	System.out.println("Enter phoneNumber: ");
	    	String phoneNumber= scanner.nextLine();
	    	System.out.println("Enter email: ");
	    	String email= scanner.nextLine();
	    	Address address = new Address(addressLine01, county, country, postalcode, phoneNumber, email);
	    	
	    	addressBook.addContact(person, address);
	    	System.out.println("New contact details added ");
	    	};
	    	printInstructions();
    }
    private static void  printContacts(){
    	addressBook.printHashMap();
    	printInstructions();
    }
    
    private static void  modifyContact(){

    	Address newAddress = null;

		System.out.println("====== You are about to edit a contacts details ======");
		System.out.println("Enter first name of existing contact: ");
		String existingFirstName= scanner.nextLine();
		System.out.println("Enter surname of existing contact: ");
		String existingSecondName= scanner.nextLine();
		Person existingP = new Person(existingFirstName, existingSecondName);

		if(!addressBook.hasContact(existingP)){
			System.out.println("Contact cannot be found");
		}else{

			System.out.println("Contact has been found");

			System.out.println("====== Please enter the new contacts details ======");
			System.out.println("Enter first name: ");
			String firstName= scanner.nextLine();
			System.out.println("Enter surname: ");
			String secondName= scanner.nextLine();
			Person newP = new Person(firstName, secondName);

			System.out.println("====== Do you wish to update the contacts address Y/N ======");
			String answer= scanner.nextLine();

			if((answer).equals("y") ){
				//Input address
				System.out.println("Enter first line of address: ");
				String addressLine01= scanner.nextLine();
				System.out.println("Enter county: ");
				String county= scanner.nextLine();
				System.out.println("Enter country: ");
				String country = scanner.nextLine();
				System.out.println("Enter Postal code: ");
				String postalcode= scanner.nextLine();
				System.out.println("Enter phoneNumber: ");
				String phoneNumber= scanner.nextLine();
				System.out.println("Enter email: ");
				String email= scanner.nextLine();
				newAddress = new Address(addressLine01, county, country, postalcode, phoneNumber, email);				
			}
			if(addressBook.editContact(newP, newAddress, existingP)){
				System.out.println("====== Contact details have being updated ======");
			};
		}
		printInstructions();
    }
    
    private static void  removeContact(){
    	System.out.println("Enter first name of contact: ");
    	String firstName= scanner.nextLine();
    	System.out.println("Enter surname of contact: ");
    	String secondName= scanner.nextLine();
    	Person p = new Person(firstName, secondName);
    	
    	if(addressBook.removeContact(p)){
    		System.out.println("====== Contact has been removed ======");
    	}
    	else{
    		System.out.println("====== Contact could not be found ======");
    	}
    	printInstructions();
    }
    
    private static void searchForContact(){
    	System.out.println("Enter first name of contact: ");
    	String firstName= scanner.nextLine();
    	System.out.println("Enter surname of contact: ");
    	String secondName= scanner.nextLine();
    	Person p = new Person(firstName, secondName);
    	
    	if(addressBook.hasContact(p)){
    		System.out.println("====== Contact exists ======");
    	}
    	else{
    		System.out.println("====== Contact could not be found ======");
    	}
    	printInstructions();
    }
    
    private static void outputAddressBook(){
    	addressBook.outputAddressBook();
    	printInstructions();
    }
    
    private static void readAddressBook(){
    	addressBook.readAddressBookFile();
    	printInstructions();
    }

    private static void quitAddressBook(){
    	if(addressBook.isState()){
    		System.exit(0);
    	}
    	System.out.println("**-The address book has been modfifed do you want to save changes Y/N -**");
    	String answer = scanner.nextLine();
    	if((answer).equals("y") ){
    		outputAddressBook();
    		System.exit(0);
    	}else{
    		System.exit(0);
    	}

    }
    
	

}
