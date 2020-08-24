import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class ReadFile {
    static void printFileContents(String filePath) throws FileNotFoundException {
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNext()) {
            System.out.println(s.nextLine());
        }
    }
    static void updateContents(String filePath, ArrayList<Task> list) throws FileNotFoundException {
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNext()) {
            Task.addTaskFromFile(s.nextLine(), list, false);
        }
    }

    public static void main(String[] args) {
        try {
            printFileContents("data");
            LocalDate k = LocalDate.parse("2019-12-01");
            System.out.println(k.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }
}
