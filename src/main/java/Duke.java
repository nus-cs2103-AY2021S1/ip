import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        // File file = new File("data/duke.txt");
        // try {
        //     Scanner sc = new Scanner(file);
        //     while (sc.hasNextLine()) {
        //         String data = sc.nextLine();
        //         System.out.println(data);
        //     }
        // } catch (FileNotFoundException e) {
        //     System.err.println(e);
        // }
        TaskManager manager = new TaskManager();
        manager.initialise();
    }
}
