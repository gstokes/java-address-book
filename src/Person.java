import java.io.Serializable;

public class Person implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String firstName;
	private String secondName;
	
	public Person(String firstName, String secondName) {
		this.firstName = firstName;
		this.secondName = secondName;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getSecondName() {
		return secondName;
	}


	@Override
	public boolean equals(Object object) {
		
		if(object==null)
            return false;
	     if(this.getClass()!=object.getClass())
	            return false;
	
	     Person p=(Person)object;
	     return p.firstName.equals(this.firstName) && p.secondName.equals(this.secondName); 
			
	}
	
	  @Override
	    public int hashCode() {
		  return firstName.hashCode() + secondName.hashCode(); 
	    }

	}

