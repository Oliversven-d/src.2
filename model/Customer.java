package model;

import java.util.regex.Pattern;

public class Customer {
	private final String firstName;
	private final String lastName;
	private final String email;
	
	public Customer(String firstName, String lastName, String email) {
		String regex = "^(.+)@(.+).(.+)$";
		Pattern pattern = Pattern.compile(regex);
		if(!pattern.matcher(email).matches()) {
			throw new IllegalArgumentException("Invalid email!");
		}
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}
	
	
	
	public String getFirstName() {
		return firstName;
	}



	public String getLastName() {
		return lastName;
	}



	public String getEmail() {
		return email;
	}
	
	@Override
    public int hashCode() {
		int result = 17;
	    if (this.getFirstName() != null) {
	        result = 31 * result + this.getFirstName().hashCode();
	    }
	    if (this.getLastName() != null) {
	        result = 31 * result + this.getLastName().hashCode();
	    }
	    if (this.getEmail() != null) {
	        result = 31 * result + this.getEmail().hashCode();
	    }
	    return result;
    }
	
	@Override
	public boolean equals(Object obj) {
		if(this == obj)
            return true;

        if(obj == null || obj.getClass()!= this.getClass())
            return false;
        
        Customer c = (Customer) obj;
		return this.getFirstName().equals(c.getFirstName()) && (this.getLastName().equals(c.getLastName()) && (this.getEmail().equals(c.getEmail())));
	}

	public String toString() {
		return "First Name: " + this.firstName + " Last Name: " + this.lastName + " Email: " + this.email;
	}
}
