package business;

import java.io.Serializable;

final public class Author extends Person implements Serializable {
	private String bio;
	public String getBio() {
		return bio;
	}
	
	public Author(String f, String l, String t, Address a, String bio) {
		super(f, l, t, a);
		this.bio = bio;
	}


	@Override
	public String toString() {
		return this.getFirstName() + " " + this.getLastName();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {return false;}
		return obj.hashCode() == this.hashCode();
	}

	@Override
	public int hashCode() {
		return this.getFirstName().trim().hashCode()*3 +  this.getLastName().trim().hashCode()*5;
	}

	private static final long serialVersionUID = 7508481940058530471L;
}
