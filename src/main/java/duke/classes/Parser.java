package duke.classes;

import java.util.Scanner;

/**
 * Class for taking in commands and analysing the type of command given to the chatbot.
 */
public class Parser {

    protected Scanner scan;

    /**
     * Class constructor.
     */
    public Parser() {
        this.scan = new Scanner(System.in);
    }

    /**
     * Method to determine the type of command given to the chatbot.
     *
     * @param command   Command given to the chatbot.
     * @return Commands from the Commands ENUM class
     */
    public Commands analyse(String command) {
        if (command.startsWith("list")) {
            return Commands.LIST;
        } else if (command.startsWith("find")) {
            return Commands.FIND;
        } else if (command.startsWith("todo")) {
            return Commands.TODO;
        } else if (command.startsWith("deadline")) {
            return Commands.DEADLINE;
        } else if (command.startsWith("event")) {
            return Commands.EVENT;
        } else if (command.startsWith("delete")) {
            return Commands.DELETE;
        } else if (command.startsWith("blah")) {
            return Commands.BLAH;
        } else if (command.startsWith("done")) {
            return Commands.DONE;
        } else {
            return Commands.TASK;
        }
    }
}
