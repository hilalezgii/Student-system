import java.util.ArrayList;

public class BinarySearchTree {
    private Node root;
    private int studentCount;

    public BinarySearchTree() {
        root = null;
        studentCount = 0;
    }

    public void insert(Student student) {
        root = insertRec(root, student);
        studentCount++;
    }

    private Node insertRec(Node root, Student student) {
        if (root == null) {
            root = new Node(student);
            return root;
        }

        if (student.number < root.data.number)
            root.left = insertRec(root.left, student);
        else if (student.number > root.data.number)
            root.right = insertRec(root.right, student);

        return root;
    }

    public void inorderTraversal() {
        inorderRec(root);
    }

    private void inorderRec(Node root) {
        if (root != null) {
            inorderRec(root.left);
            System.out.println("Numara: " + root.data.number +
                    " İsim: " + root.data.name + " " + root.data.surname +
                    " Ortalama: " + String.format("%.2f", root.data.average));
            inorderRec(root.right);
        }
    }

    public ArrayList<Student> getStudentsAsList() {
        ArrayList<Student> students = new ArrayList<>();
        getStudentsAsListRec(root, students);
        return students;
    }

    private void getStudentsAsListRec(Node root, ArrayList<Student> students) {
        if (root != null) {
            getStudentsAsListRec(root.left, students);
            students.add(root.data);
            getStudentsAsListRec(root.right, students);
        }
    }

    public void createGraphForCourse(String courseName) {
        ArrayList<Student> students = getStudentsAsList();
        System.out.println("Graf (Ders: " + courseName + "):");
        for (Student s1 : students) {
            for (Student s2 : students) {
                if (s1 != s2 && s1.hasCourse(courseName) && s2.hasCourse(courseName)) {
                    System.out.println(s1.name + " <-> " + s2.name);
                }
            }
        }
    }

    public void deleteStudent(int number) {
        root = deleteRec(root, number);
        studentCount--;
    }

    private Node deleteRec(Node root, int number) {
        if (root == null) {
            return root;
        }

        if (number < root.data.number) {
            root.left = deleteRec(root.left, number);
        } else if (number > root.data.number) {
            root.right = deleteRec(root.right, number);
        } else {
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }

            root.data = minValue(root.right);
            root.right = deleteRec(root.right, root.data.number);
        }

        return root;
    }

    private Student minValue(Node root) {
        Student minValue = root.data;
        while (root.left != null) {
            minValue = root.left.data;
            root = root.left;
        }
        return minValue;
    }

    public int getStudentCount() {
        return studentCount;
    }
}
