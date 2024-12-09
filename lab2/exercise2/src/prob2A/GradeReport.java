package prob2A;

public class GradeReport {
	private Student student;
	private double grade;
	
	GradeReport(Student student){
		this.student = student;
	}

	public Student getStudent() {
		return student;
	}

	public double getGrade() {
		return grade;
	}

	public void setGrade(double grade) {
		this.grade = grade;
	}
}
