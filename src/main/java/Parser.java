import java.util.Scanner;

/**
 * Represents a parser to make sense of the user commands.
 */
public class Parser {

    /**
     * Returns the appropriate command to be carried out after scanning the user command.
     * @param fullCommand original user command
     * @return command to be executed
     * @throws DukeException for errors such as non-comprehensible user commands
     */
    public static Command parse(String fullCommand) throws DukeException {
        Scanner s2 = new Scanner(fullCommand);
        if (fullCommand.equals("bye")) {
            // goodbye command
            return new ExitCommand();
        } else if (fullCommand.contains("done")) {
            // done command
            s2.skip("done");
            int taskNumber = s2.nextInt();
            return new DoneCommand(taskNumber);
        } else if (fullCommand.equals("list")) {
            // list command
            return new ListCommand();
        } else if (fullCommand.contains("delete")) {
            // delete command
            s2.skip("delete");
            int taskNumber = s2.nextInt();
            return new DeleteCommand(taskNumber);
        } else {
            Task currTask;
            if (fullCommand.contains("todo")) {
                // add command for todotask
                s2.skip("todo");
                if (s2.hasNext()) {
                    s2.skip(" ");
                    currTask = new Todo(s2.nextLine());
                } else {
                    throw new DukeException("The description of a todo cannot be empty.");
                }
            } else if (fullCommand.contains("deadline")) {
                // add command for deadline
                s2.skip("deadline ");
                s2.useDelimiter(" /by ");
                currTask = new Deadline(s2.next(), s2.next());
            } else {
                // add command for event
                if (fullCommand.contains("event")) {
                    s2.skip("event ");
                    s2.useDelimiter(" /at ");
                    currTask = new Event(s2.next(), s2.next());
                } else {
                    throw new DukeException("I'm sorry, but I don't know what that means :-(");
                }
            }
            return new AddCommand(currTask);
        }
    }
}
