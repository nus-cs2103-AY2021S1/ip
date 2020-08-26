package duke;
import duke.command.*;

/**
 * Parser class that deals with making sense of the user command
 */
public class Parser {

    /**
     * Static method that takes in the user's command and return a Command object. If no command matches, it will throw a DukeException
     * @param  command input from the user
     * @return      a Command object
     */
    public static Command parse(String command) throws DukeException {
        String arr[] = command.split(" ");
        String keyWord = arr[0];

        if (keyWord.equals("bye")) {
            return new ExitCommand(command);
        } else if (keyWord.equals("list")) {
            return new ShowListCommand(command);
        } else if(keyWord.equals("todo")) {
            return new AddTodoCommand(command);
        } else if(keyWord.equals("deadline")){
            return new AddDeadlineCommand(command);
        } else if (keyWord.equals("event")){
            return new AddEventCommand(command);
        } else if(keyWord.equals("done")) {
            return new CompleteTaskCommand(command);
        } else if(keyWord.equals("delete")) {
            return new DeleteTaskCommand(command);
        } else {
            throw new DukeException("☹ OOPS!!! wait..... I don't understand your order my sir.");
        }
    }
}
