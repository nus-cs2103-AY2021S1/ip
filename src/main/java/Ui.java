import java.util.Scanner;
import java.util.stream.Stream;

public class Ui {
    Scanner sc;
    TaskList taskList;
    Parser parser;
    char line = '*';


    Ui(TaskList taskList, Storage storage) {
        this.sc = new Scanner(System.in);
        this.taskList = taskList;
        this.parser = new Parser(taskList, storage);
        start();
    }


    /**
     * Handles I/O interaction with users and passes the command to the Parser
     */
    void handleInteraction() {
        boolean isValid = true;
        while (sc.hasNext() && isValid) {
            String input = sc.nextLine();
            printTopLine();
            isValid = parser.handleCommand(input);
            printBottomLine();
        }
        Printer.printCustomStatement("Goodbye!");
    }

    /**
     * Prints a welcome message to the user
     */
    void start() {
        printTopLine();
        System.out.println("Hello! I'm Duke \n What can I do for you?");
        printBottomLine();
    }

    /**
     * Prints the top line, e.g. *******************
     */
    void printTopLine() {
        Stream.generate(() -> line).limit(50).forEach(System.out::print); // _ _ _ _ _
        System.out.println();
    }

    /**
     * Prints the bottom line e.g. ****************
     */
    void printBottomLine() {
        System.out.println();
        Stream.generate(() -> line).limit(50).forEach(System.out::print); // _ _ _ _ _
        System.out.println();
    }
}

