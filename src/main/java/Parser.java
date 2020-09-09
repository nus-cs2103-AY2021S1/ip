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
        Scanner parserScanner = new Scanner(fullCommand);

        if (fullCommand.equals("bye")) {
            // goodbye command
            return new ExitCommand();
        } else if (fullCommand.contains("done")) {
            // done command

            parserScanner.skip("done");
            assert parserScanner.hasNextInt() : "Cannot read task number for done command";
            int taskNumber = parserScanner.nextInt();
            return new DoneCommand(taskNumber);
        } else if (fullCommand.contains("find")) {
            // done command
            parserScanner.skip("find");
            assert parserScanner.hasNext() : "Cannot find filter word";
            String filterWord = parserScanner.next();
            return new FindCommand(filterWord);
        } else if (fullCommand.equals("list")) {
            // list command
            return new ListCommand();
        } else if (fullCommand.contains("delete")) {
            // delete command
            parserScanner.skip("delete");
            assert parserScanner.hasNextInt() : "Cannot read task number for delete command";
            int taskNumber = parserScanner.nextInt();
            return new DeleteCommand(taskNumber);
        } else {
            Task currTask;

            if (fullCommand.contains("todo")) {
                // add command for todotask
                parserScanner.skip("todo");
                if (parserScanner.hasNext()) {
                    parserScanner.skip(" ");
                    assert parserScanner.hasNext() : "Cannot read description of todo";
                    currTask = new Todo(parserScanner.nextLine());
                } else {
                    throw new DukeException("The description of a todo cannot be empty.");
                }
            } else if (fullCommand.contains("deadline")) {
                // add command for deadline
                parserScanner.skip("deadline ");
                parserScanner.useDelimiter(" /by ");
                currTask = new Deadline(parserScanner.next(), parserScanner.next());
            } else {
                // add command for event
                if (fullCommand.contains("event")) {
                    parserScanner.skip("event ");
                    parserScanner.useDelimiter(" /at ");
                    currTask = new Event(parserScanner.next(), parserScanner.next());
                } else {
                    throw new DukeException("I'm sorry, but I don't know what that means :-(");
                }
            }
            assert currTask != null : "Task to perform cannot be null";
            return new AddCommand(currTask);
        }
    }
}
