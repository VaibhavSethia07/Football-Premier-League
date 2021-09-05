package TableEntities;

public class Owner {
	private int ownerID;
	private String firstName;
	private char mInit;
	private String lastName;
	private String email;

	public Owner(int ownerID, String firstName, char mInit, String lastName, String email) {
		super();
		this.ownerID = ownerID;
		this.firstName = firstName;
		this.mInit = mInit;
		this.lastName = lastName;
		this.email = email;
	}



	public int getOwnerID() {
		return ownerID;
	}



	public void setOwnerID(int ownerID) {
		this.ownerID = ownerID;
	}



	public String getFirstName() {
		return firstName;
	}



	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}



	public char getmInit() {
		return mInit;
	}



	public void setmInit(char mInit) {
		this.mInit = mInit;
	}



	public String getLastName() {
		return lastName;
	}



	public void setLastName(String lastName) {
		this.lastName = lastName;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return String.format("Owner [id=%s, firstName=%s, mInit=%s, lastName=%s, email=%s]", ownerID, firstName, mInit, lastName, email);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
