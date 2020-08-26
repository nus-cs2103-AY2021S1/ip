package parser;
import exception.DukeException;
import command.*;


public class Parser {
    public static final String LINE = "_______________________________________\n";

    public static Command parse(String userInput) throws DukeException {
        String[] inputSplit = userInput.split(" ", 2);
        String userCommand = inputSplit[0];
        if (userCommand.equals("bye")) {  // For exiting the program
            return new ByeCommand();
        } else if (userCommand.equals("list")) {  // For viewing items in to do list
            return new ListCommand();
        } else if (userCommand.equals("done")) {  // For marking items in the to do list as done
            try {
                return new DoneCommand(inputSplit[1]);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeException(LINE + "Invalid input! Please specify which task you have completed! \n" + LINE);
            }
        } else if (userCommand.equals("todo")) { // Add new to do task
            try {
                return new TodoCommand(inputSplit[1]);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeException(LINE + "Invalid input! Please specify your todo description! \n" + LINE);
            }
        } else if (userCommand.equals("deadline")) { // Add new deadline
            try {
                return new DeadlineCommand(inputSplit[1]);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeException(LINE + "Invalid input! Please specify your deadline description and details! \n" + LINE);
            }

        } else if (userCommand.equals("event")) { // Add new event
            try {
                return new EventCommand(inputSplit[1]);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeException(LINE + "Invalid input! Please specify your event description and details! \n" + LINE);
            }

        } else if (userCommand.equals("delete")) { // Delete task
            try {
                return new DeleteCommand(inputSplit[1]);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeException(LINE + "Invalid input! Please specify which task you want to delete! \n" + LINE);
            }
        } else if (userCommand.equals("filter")) { // Filter taskList
            try {
                return new FilterCommand(inputSplit[1]);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeException(LINE + "Invalid input! Please specify which date you want to filter! \n" + LINE);
            }
        } else {
            throw new DukeException(LINE + "Invalid command! \n" + LINE);
        }
    }
}
