import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();
        Scanner scanner = new Scanner(System.in);

        try {
            File file = new File("src/students.txt");
            Scanner fileScanner = new Scanner(file);

            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] parts = line.split(" ");

                int number = Integer.parseInt(parts[0]);
                Student student = new Student(number, parts[1], parts[2]);

                for (int i = 3; i < parts.length; i += 2) {
                    student.addCourse(parts[i], Integer.parseInt(parts[i + 1]));
                }

                bst.insert(student);
            }
            fileScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Dosya bulunamadı!");
            return;
        }

        while (true) {
            System.out.println("\n1. Öğrencileri numaraya göre listele");
            System.out.println("2. Öğrencileri ortalamaya göre listele");
            System.out.println("3. Öğrenci sil");
            System.out.println("4. Yeni öğrenci ekle");
            System.out.println("5. Öğrenci sayısını göster");
            System.out.println("6. Ders grafiği oluştur");
            System.out.println("7. Çıkış");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("\nÖğrenciler (Numaraya göre):");
                    bst.inorderTraversal();
                    break;

                case 2:
                    System.out.println("\nÖğrenciler (Ortalama sırasına göre):");
                    ArrayList<Student> studentsByAverage = bst.getStudentsAsList();
                    studentsByAverage.sort(Comparator.comparingDouble(s -> s.average));
                    for (Student s : studentsByAverage) {
                        System.out.println("Numara: " + s.number + ", İsim: " + s.name + " " + s.surname + ", Ortalama: " + String.format("%.2f", s.average));
                    }
                    break;

                case 3:
                    System.out.println("Silinecek öğrenci numarası:");
                    int numToDelete = scanner.nextInt();
                    bst.deleteStudent(numToDelete);
                    break;

                case 4:
                    System.out.println("Öğrenci numarası:");
                    int newNum = scanner.nextInt();
                    System.out.println("Adı:");
                    String newName = scanner.next();
                    System.out.println("Soyadı:");
                    String newSurname = scanner.next();

                    Student newStudent = new Student(newNum, newName, newSurname);

                    System.out.println("Ders sayısı:");
                    int courseCount = scanner.nextInt();
                    for (int i = 0; i < courseCount; i++) {
                        System.out.println("Ders adı:");
                        String courseName = scanner.next();
                        System.out.println("Notu:");
                        int grade = scanner.nextInt();
                        newStudent.addCourse(courseName, grade);
                    }

                    bst.insert(newStudent);
                    break;

                case 5:
                    System.out.println("Toplam öğrenci sayısı: " + bst.getStudentCount());
                    break;

                case 6:
                    System.out.println("Ders adı girin (Graf oluşturulacak):");
                    String courseName = scanner.next();
                    bst.createGraphForCourse(courseName);
                    break;

                case 7:
                    System.out.println("Program sonlandırılıyor...");
                    return;

                default:
                    System.out.println("Geçersiz seçim!");
            }
        }
    }
}