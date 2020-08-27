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
            return new DoneCommand(inputSplit[1]);
        } else if (userCommand.equals("todo")) { // Add new to do task
            return new TodoCommand(inputSplit[1]);
        } else if (userCommand.equals("deadline")) { // Add new deadline
            return new DeadlineCommand(inputSplit[1]);
        } else if (userCommand.equals("event")) { // Add new event
            return new EventCommand(inputSplit[1]);
        } else if (userCommand.equals("delete")) { // Delete task
            return new DeleteCommand(inputSplit[1]);
        } else if (userCommand.equals("filter")) { // Filter taskList
            return new FilterCommand(inputSplit[1]);
        } else {
            return new InvalidCommand();
        }
    }
}
