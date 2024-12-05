package lesson3.labs.prob1;

public class PersonWithJob {

	private person Person;
	
	private double salary;
	
	public double getSalary() {
		return salary;
	}

	public Person getPerson(){
	   return person;
	}

	PersonWithJob(Person person, double s) {
		this.person = person;
		salary = s;
	}
	
	@Override
	public boolean equals(Object aPerson) {
		if(aPerson == null) return false; 
		if(aPerson instanceof PersonWithJob){
			PersonWithJob p = (PersonWithJob)aPerson;
			return p.getPerson().equals(this.person) && this.getSalary()==p.getSalary();
		} else {
			return false;
		}
	}
	public static void main(String[] args) {
		Person p1 = new PersonWithJob("Joe", 30000);
		Person p2 = new Person("Joe");
		//As PersonsWithJobs, p1 should be equal to p2
		System.out.println("p1.equals(p2)? " + p1.equals(p2)); // false
		System.out.println("p2.equals(p1)? " + p2.equals(p1));
	}


}
