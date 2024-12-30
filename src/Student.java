public class Student {
    int number;
    String name;
    String surname;
    Course courseList;
    double average;

    public Student(int number, String name, String surname) {
        this.number = number;
        this.name = name;
        this.surname = surname;
        this.courseList = null;
        this.average = 0.0;
    }

    public void addCourse(String name, int grade) {
        Course newCourse = new Course(name, grade);
        if (courseList == null) {
            courseList = newCourse;
        } else {
            Course temp = courseList;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = newCourse;
        }
        calculateAverage();
    }

    private void calculateAverage() {
        if (courseList == null) return;

        double total = 0;
        int count = 0;
        Course temp = courseList;
        while (temp != null) {
            total += temp.grade;
            count++;
            temp = temp.next;
        }
        this.average = total / count;
    } public boolean hasCourse(String courseName) {
        Course temp = courseList;
        while (temp != null) {
            if (temp.name.equals(courseName)) {
                return true;
            }
            temp = temp.next;
        }
        return false;
    }
}