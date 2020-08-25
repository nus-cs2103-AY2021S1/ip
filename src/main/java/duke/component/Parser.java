package duke.component;

import duke.command.*;
import duke.task.*;

/**
 * Interpreter of user's input
 */
public class Parser {

    /**
     * Parses an input string and gives the corresponding command
     * @param input the input typed in by the user
     * @return the command that corresponds to the input
     * @throws DukeException if the input is invalid or illegal
     */
    public static Command parse(String input) throws DukeException {

        if (input.equals("bye")) {
            return new ByeCommand();
        } else if (input.equals("list")) {
            return new ListCommand();
        } else if (input.startsWith("find")) {
            if (input.length() <= 5) {
                throw new DukeException("Please enter the keyword you want to search!");
            }
            return new FindCommand(input.substring(5));
        }else if (input.startsWith("done")) {
            if (input.length() <= 5) {
                throw new DukeException("I don't know which task should be marked as completed.");
            }
            int index;
            try {
                index = Integer.parseInt(input.substring(5)) - 1;
            } catch (NumberFormatException e) {
                throw new DukeException("The task index should be a number.");
            }
            return new DoneCommand(index);
        } else if (input.startsWith("delete")){
            if (input.length() <= 7) {
                throw new DukeException("I don't know which task should be deleted.");
            }
            int index;
            try {
                index = Integer.parseInt(input.substring(7)) - 1;
            } catch (NumberFormatException e) {
                throw new DukeException("The task index should be a number.");
            }
            return new DeleteCommand(index);
        } else {
            Task newTask;
            if (input.startsWith("todo")) {
                if (input.length() <= 5) {
                    throw new DukeException("The description of a todo cannot be empty.");
                }
                newTask = new Todo(input.substring(5));
            } else if (input.startsWith("deadline")) {
                if (input.length() <= 9) {
                    throw new DukeException("The description of a deadline cannot be empty.");
                }
                String description = input.substring(9);
                int index = description.indexOf("/by");
                if (index == -1 || description.length() - index <= 4) {
                    throw new DukeException("I don't know when the deadline is");
                }
                String by = description.substring(index + 4);
                description = description.substring(0, index - 1);
                newTask = new Deadline(description, by);
            } else if (input.startsWith("event")) {
                if (input.length() <= 6) {
                    throw new DukeException("The description of a event cannot be empty.");
                }
                String description = input.substring(6);
                int index = description.indexOf("/at");
                if (index == -1 || description.length() - index <= 4) {
                    throw new DukeException("I don't know when the event take place");
                }
                String at = description.substring(index + 4);
                description = description.substring(0, index - 1);
                newTask = new Event(description, at);
            } else {
                throw new DukeException();
            }
            return new AddCommand(newTask);
        }
    }

}
