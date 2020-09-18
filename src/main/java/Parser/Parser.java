package parser;

import command.ByeCommand;
import command.Command;
import command.DeadlineCommand;
import command.DeleteCommand;
import command.DoneCommand;
import command.EventCommand;
import command.FindCommand;
import command.ListCommand;
import command.TodoCommand;
import command.UndoCommand;
import exception.DukeException;

/**
 * Parser handles the user input and parse them to corresponding command class.
 */
public class Parser {
    /**
     * Parses user input to corresponding command class.
     * @param input user input.
     * @return the object of command class.
     * @throws DukeException exception for invalid user input.
     */
    public static Command parse(String input) throws DukeException {
        assert input.length() > 0 : "There is no user input.";
        String[] words = input.split("\\s");
        String firstWord = words[0];
        if (input.equals("bye")) { //bye command
            return new ByeCommand();
        } else if (input.equals("list")) { //list command
            System.out.println("list");
            return new ListCommand();
        } else if (input.equals("undo")) {
            return new UndoCommand();
        } else if (firstWord.equals("find")) { //find command
            return new FindCommand(words);
        } else if (firstWord.equals("done")) { //done command
            return new DoneCommand(words);
        } else if (firstWord.equals("delete")) { //done command
            System.out.println("delete");
            return new DeleteCommand(words);
        } else if (firstWord.equals("todo")) { //todo command
            System.out.println("todo");
            return new TodoCommand(words);
        } else if (firstWord.equals("deadline")) { //complete deadline command
            return new DeadlineCommand(words);
        } else if (firstWord.equals("event")) {
            return new EventCommand(words);
        } else { //exception handler
            throw new DukeException();
        }
    }
}
