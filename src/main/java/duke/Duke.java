package duke;

import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.UI;

import java.util.Scanner;
import java.io.File;

/**
 * The Duke program implements a chatbot application that would take in the input from
 * the user as a todo list.
 */
public class Duke {

    /**
     * Empty line to be printed after each command
     */
    private final static String LINE = "*********************************************************";
    /**
     * Parser that makes sense of the user's inputs
     */
    private static Parser parser;


    /**
     * The main method that runs the chatbot application.
     */
    public static void main(String[] args) {
        File f = new File("D:\\24092014\\Joven\\UNI STUFF\\CS2103\\IP\\task.txt");
        welcome();
        Storage storage = new Storage(f);
        TaskList shelf = new TaskList(storage.loadFile());
        UI ui = new UI(shelf, storage);
        parser = new Parser(ui);
        Scanner sc = new Scanner(System.in);
        handler(sc);
        sc.close();
    }

    /**
     * This method shows the welcome message for the chatbot. It is what users see when they launch the chatbot.
     */
    public static void welcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(LINE);
        System.out.println("Hello from " + logo);
        System.out.println("What can I do for you pal? :D");
        System.out.println(LINE);
    }

    /**
     * This method will run the chatbot program.
     * <p>
     * It will accept user's inputs and run the program,
     * only stopping once the user inputs bye, which will cause the program to stop.
     * </p>
     *
     * @param sc Scanner object used to read users inputs
     */
    public static void handler(Scanner sc) {
        while (true) {
            String response = sc.nextLine();
            parser.listen(response);
            System.out.println(LINE);
            if (parser.toStop(response)) {
                break;
            }
        }
    }
}
