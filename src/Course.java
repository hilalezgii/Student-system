public class Course {
    String name;
    int grade;
    Course next;

    public Course(String name, int grade) {
        this.name = name;
        this.grade = grade;
        this.next = null;
    }
}