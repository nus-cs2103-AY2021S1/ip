package seedu.duke;

import seedu.duke.command.*;
import seedu.duke.exception.DukeException;
import seedu.duke.exception.DukeNotSureException;

/**
 * Takes care of parsing user commands.
 */
public class Parser {

    /**
     * Returns the command that the user has inputted.
     * @param input User command inputted.
     * @return Command the user wants
     * @throws DukeException If the command is invalid
     */
    public Command parse(String input) throws DukeException {
        String endCommand = "bye";
        String listCommand = "list";
        String doneCommand = "done";
        String deleteCommand = "delete";
        String toDoCommand = "todo";
        String deadlineCommand = "deadline";
        String eventCommand = "event";
        String findCommand = "find";
        String sortCommand = "sort";
        String helpCommand = "help";

        if (input.equals(listCommand)) {
            return new ListCommand();
        } else if (input.equals(endCommand)) {
            return new ByeCommand();
        } else if(input.equals(helpCommand)) {
            return new HelpCommand();
        } else {
            String[] words = input.split(" ", 2);
            if (words[0].equals(doneCommand)) {
                return new DoneCommand(words);
            } else if (words[0].equals(deleteCommand)) {
                return new DeleteCommand(words);
            } else if (words[0].equals(findCommand)) {
                return new FindCommand(words);
            } else if (words[0].equals(toDoCommand)) {
                return new AddTodo(words);
            } else if (words[0].equals(deadlineCommand)) {
                return new AddDeadline(words);
            } else if (words[0].equals(eventCommand)) {
                return new AddEvent(words);
            } else if (words[0].equals(sortCommand)) {
                return new SortCommand(words);
            } else { // user typed a command not in duke
                throw new DukeNotSureException("Man I don't know what you want? :s I'm not very smart ok??");
            }
        }
    }
}
