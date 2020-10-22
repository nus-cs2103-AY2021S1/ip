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
        scan = new Scanner(System.in);
    }

    /**
     * Method to determine the type of command given to the chatbot.
     * @param command   Command given to the chatbot.
     * @return Commands from the Commands ENUM class
     */
    public Command analyse(String command) {
        //Assertion: Commands given are valid & accounted for
        assert command.length() > 2 : "Command might be invalid";
        if (command.startsWith("list")) {
            return Command.LIST;
        } else if (command.startsWith("find")) {
            return Command.FIND;
        } else if (command.startsWith("todo")) {
            return Command.TODO;
        } else if (command.startsWith("deadline")) {
            return Command.DEADLINE;
        } else if (command.startsWith("event")) {
            return Command.EVENT;
        } else if (command.startsWith("delete")) {
            return Command.DELETE;
        } else if (command.startsWith("blah")) {
            return Command.BLAH;
        } else if (command.startsWith("done")) {
            return Command.DONE;
        } else if (command.startsWith("bye")) {
            return Command.BYE;
        } else if (command.startsWith("tag")) {
            return Command.TAG;
        } else {
            return Command.INVALID;
        }
    }
}
