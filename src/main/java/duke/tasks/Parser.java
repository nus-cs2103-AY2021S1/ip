package duke.tasks;

/**
 * Represents a parser where the inputs from the user is filtered according to
 * the command issued.
 */
public class Parser {

    /**
     * Creates a command that is specified by the user. The command will be
     * executed in the main program.
     * @param toPrint input from the user.
     * @return type of command to be executed.
     */
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
