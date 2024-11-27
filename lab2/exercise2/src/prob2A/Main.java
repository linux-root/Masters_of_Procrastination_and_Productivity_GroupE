package prob2A;

public class Main {

	public static void main(String[] args) {
		Student student = new Student("MIU");
		student.getGradeReport().setGrade(99);
		
		System.out.println("MIU's grade: " + student.getGradeReport().getGrade());
		
		GradeReport gradeReport = student.getGradeReport();
		System.out.println("The gradeReport belongs to " + gradeReport.getStudent());
	}

}
