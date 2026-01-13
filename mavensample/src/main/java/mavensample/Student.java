package mavensample;

public class Student {
	private int studentId;
	private int rollNo;
	private String name;
	public Student(int studentId,int rollNo,String name) {
		this.studentId=studentId;
		this.rollNo=rollNo;
		this.name=name;
	}
	public void display() {
		System.out.println("Student ID     : "+studentId);
		System.out.println("Roll No        : "+rollNo);
		System.out.println("Name           : "+name);
	}

}