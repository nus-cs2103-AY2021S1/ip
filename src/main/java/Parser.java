import java.util.Scanner;

/**
 * Represents a parser to make sense of the user commands.
 */
public class Parser {

    /**
     * Returns the appropriate command to be carried out after scanning the user command.
     * @param fullCommand original user command.
     * @return command to be executed.
     * @throws DukeException for errors such as non-comprehensible user commands.
     */
    public static Command parse(String fullCommand) throws DukeException {
        if (fullCommand.equals("")) {
            throw new DukeException("Input cannot be empty!");
        }

        Scanner parserScanner = new Scanner(fullCommand);

        if (fullCommand.equals("bye")) {
            // goodbye command
            return new ExitCommand();
        } else if (fullCommand.contains("done")) {
            // done command
            return Parser.handleDoneCommand(parserScanner);
        } else if (fullCommand.contains("find")) {
            // find command
            return Parser.handleFindCommand(parserScanner);
        } else if (fullCommand.equals("list")) {
            // list command
            return new ListCommand();
        } else if (fullCommand.contains("delete")) {
            // delete command
            return Parser.handleDeleteCommand(parserScanner);
        } else {
            if (fullCommand.contains("todo")) {
                // add command for todotask
                return Parser.handleTodoCommand(parserScanner);
            } else if (fullCommand.contains("deadline")) {
                // add command for deadline
                return Parser.handleDeadlineCommand(parserScanner);
            } else {
                // add command for event
                if (fullCommand.contains("event")) {
                    return Parser.handleEventCommand(parserScanner);
                } else {
                    throw new DukeException("I'm sorry, but I don't know what that means :-(");
                }
            }
        }
    }

    /**
     * Returns an appropriate done command.
     * @param parserScanner Scanner object which scans the command.
     * @return done command.
     * @throws DukeException if number of task is missing.
     */
    public static Command handleDoneCommand(Scanner parserScanner) throws DukeException {
        parserScanner.skip("done");

        if (!parserScanner.hasNextInt()) {
            throw new DukeException("Please specify which task to mark as done!");
        }

        assert parserScanner.hasNextInt() : "Cannot read task number for done command";
        int taskNumber = parserScanner.nextInt();
        return new DoneCommand(taskNumber);
    }

    /**
     * Returns an appropriate find command.
     * @param parserScanner Scanner object which scans the command.
     * @return find command.
     * @throws DukeException if keyword is missing.
     */
    public static Command handleFindCommand(Scanner parserScanner) throws DukeException {
        parserScanner.skip("find");

        if (!parserScanner.hasNext()) {
            throw new DukeException("Please specify which word you are looking for!");
        }

        assert parserScanner.hasNext() : "Cannot find filter word";
        String filterWord = parserScanner.next();
        return new FindCommand(filterWord);
    }

    /**
     * Returns an appropriate delete command.
     * @param parserScanner Scanner object which scans the command.
     * @return delete command.
     * @throws DukeException if number of task is missing.
     */
    public static Command handleDeleteCommand(Scanner parserScanner) throws DukeException {
        parserScanner.skip("delete");

        if (!parserScanner.hasNextInt()) {
            throw new DukeException("Please specify which task to delete!");
        }

        assert parserScanner.hasNextInt() : "Cannot read task number for delete command";
        int taskNumber = parserScanner.nextInt();
        return new DeleteCommand(taskNumber);
    }

    /**
     * Returns an appropriate add command for a to-do task.
     * @param parserScanner Scanner object which scans the command.
     * @return add command for a to-do task.
     * @throws DukeException if description of to-do task is missing.
     */
    public static Command handleTodoCommand(Scanner parserScanner) throws DukeException {
        Task currTask;
        parserScanner.skip("todo");
        if (parserScanner.hasNext()) {
            parserScanner.skip(" ");
            assert parserScanner.hasNext() : "Cannot read description of todo";
            currTask = new Todo(parserScanner.nextLine());
        } else {
            throw new DukeException("The description of a todo cannot be empty.");
        }
        assert currTask != null : "Task to perform cannot be null";
        return new AddCommand(currTask);
    }

    /**
     * Returns an appropriate add command for a deadline task.
     * @param parserScanner Scanner object which scans the command.
     * @return add command for a deadline task.
     * @throws DukeException if description or date of deadline task is missing.
     */
    public static Command handleDeadlineCommand(Scanner parserScanner) throws DukeException {
        Task currTask;
        parserScanner.skip("deadline");
        parserScanner.useDelimiter(" /by ");

        String description = "";
        String date = "";

        if (!parserScanner.hasNext()) {
            throw new DukeException("The description of a deadline cannot be empty.");
        } else {
            parserScanner.skip(" ");
            description = parserScanner.next();
        }

        if (!parserScanner.hasNext()) {
            throw new DukeException("The date of the deadline cannot be empty.");
        } else {
            date = parserScanner.next();
        }

        assert !description.equals("") : "empty deadline description";
        assert !date.equals("") : "empty deadline date";

        currTask = new Deadline(description, date);
        assert currTask != null : "Task to perform cannot be null";
        return new AddCommand(currTask);
    }

    /**
     * Returns an appropriate add command for an event task.
     * @param parserScanner Scanner object which scans the command.
     * @return add command for an event task.
     * @throws DukeException if description or date of event task is missing.
     */
    public static Command handleEventCommand(Scanner parserScanner) throws DukeException {
        Task currTask;
        parserScanner.skip("event");
        parserScanner.useDelimiter(" /at ");

        String description = "";
        String date = "";

        if (!parserScanner.hasNext()) {
            throw new DukeException("The description of an event cannot be empty.");
        } else {
            parserScanner.skip(" ");
            description = parserScanner.next();
        }

        if (!parserScanner.hasNext()) {
            throw new DukeException("The date of the event cannot be empty.");
        } else {
            date = parserScanner.next();
        }

        assert !description.equals("") : "empty event description";
        assert !date.equals("") : "empty event date";

        currTask = new Event(description, date);
        assert currTask != null : "Task to perform cannot be null";
        return new AddCommand(currTask);
    }
}
