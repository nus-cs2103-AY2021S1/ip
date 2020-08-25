package duke;

import duke.command.*;
import duke.exception.DukeException;
import duke.exception.WrongInputException;

public class Parser {

    public static Command parse(String command) throws DukeException {

        try {
            String[] cmd = command.split(" ", 2);
            String firstWord = cmd[0];

            if (cmd.length < 2) {

                if (firstWord.equals("list")) {
                    return new ListCommand();

                } else if (firstWord.equals("bye")) {
                    return new ExitCommand();
                } else {
                    throw new DukeException("The description of the task cannot be empty.");

                }

            } else {
                String taskDetails = removeFirstWord(command);

                if (firstWord.equals("done")) {
                    return new DoneCommand(taskDetails);

                } else if (firstWord.equals("todo")) {
                    return new TodoCommand(taskDetails);

                } else if (firstWord.equals("deadline")) {
                    return new DeadlineCommand(taskDetails);

                } else if (firstWord.equals("event")) {
                    return new EventCommand(taskDetails);

                } else if (firstWord.equals("delete")) {
                    return new DeleteCommand(taskDetails);

                } else if (firstWord.equals("show")) {
                    return new ShowCommand(taskDetails);

                } else {
                    throw new WrongInputException();
                    //System.out.println(wrong.getMessage());
                }
            }
        } catch (DukeException exc) {
            System.out.println("Error!");
            throw new DukeException(exc.getMessage());
        }
    }

    private static String removeFirstWord(String command) throws DukeException {
        try {
            String[] cmd = command.split(" ", 2);
            return cmd[1];
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(e.getMessage());
        }
    }

}
