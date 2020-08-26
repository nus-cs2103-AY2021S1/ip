package duke;

import duke.command.*;

public class Parser {

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
        } else if(keyWord.equals("find")) {
            return new FindTaskCommand(command);
        } else {
            throw new DukeException("☹ OOPS!!! wait..... I don't understand your order my sir.");
        }
    }
}
