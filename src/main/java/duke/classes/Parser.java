package duke.classes;

import java.util.Scanner;

public class Parser {

    protected Scanner scan;

    public Parser() {
        this.scan = new Scanner(System.in);
    }

    public Commands analyse(String command) {
        if (command.startsWith("list")) {
            return Commands.LIST;
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
