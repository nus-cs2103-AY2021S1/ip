import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * Main class which cretaes the application Duke that creates a checklist for tasks to do
 */
public class Duke {
    public static final String line = "____________________________________________________________";
   private static Commands comm;
    public static ArrayList<Task> ListOfItems = new ArrayList<Task>();

    public static void main(String[] args) {


        runDuke();

    }

    /**
     * runs the program
     */
   public static void runDuke() {

        UI.introduction();
        Parser.parseCode(Storage.load(Storage.FILE_PATH), new UI(), false);
    }

}


