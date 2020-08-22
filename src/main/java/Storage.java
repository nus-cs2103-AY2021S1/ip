import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Storage {
    private static String FILE_PATH = "src/main/data/input.txt";

    public static void printFileContents() {
        try {
            File f = new File(FILE_PATH); // create a File for the given file path
            Scanner s = new Scanner(f); // create a Scanner using the File as the source
            while (s.hasNext()) {
                System.out.println(s.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }

    public static List<Task> parseFileContents() {
        try{
            File f = new File(FILE_PATH);
            Scanner s = new Scanner(f);
            List<Task> taskArr = new ArrayList<>();
            while (s.hasNext()) {
                String taskString = s.nextLine();
                String[] taskStringArr = taskString.split(" - ");
                Task task = InputParser.parseTask(taskStringArr);
                taskArr.add(task);
            }
            return taskArr;
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            System.exit(0);
            return null;
        }
    }

}
