package duke.tasks;

import java.util.Scanner;

public class Parser {

    public static Command parse(String toPrint) {
        if (toPrint.startsWith("list")) {
            return new ListCommand(toPrint);
        }
        else if (toPrint.startsWith("done")) {
            return new DoneCommand(toPrint);
        }
        else if (toPrint.startsWith("delete")) {
            return new DeleteCommand(toPrint);
        }
        else if (toPrint.startsWith("todo")) {
            return new TodoCommand(toPrint);
        }
        else if (toPrint.startsWith("event")) {
            return new EventCommand(toPrint);
        }
        else if (toPrint.startsWith("deadline")) {
            return new DeadlineCommand(toPrint);
        }
        else if (toPrint.startsWith("bye")) {
            return new ByeCommand();
        }
        else if (toPrint.startsWith("find")) {
            return new FindCommand(toPrint);
        }
        else {
            System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
            return null;
        }
    }

}
