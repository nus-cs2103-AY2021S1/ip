import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static final String line = "____________________________________________________________";
   private static Commands comm;
    public static ArrayList<Task> ListOfItems = new ArrayList<Task>();

    public static void main(String[] args) {


        runDuke();

    }

    private static void runDuke() {

        UI.introduction();
        Parser.parseCode(Storage.load(Storage.FILE_PATH), new UI(), false);
    }

}


