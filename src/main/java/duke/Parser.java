package duke;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.ListCommand;

import java.util.Arrays;

public class Parser {

    protected String inputString;

    Parser(String inputString) {
        this.inputString = inputString;
    }

    public boolean stringContainsItemFromList(String inputStr, String[] items) {
        return Arrays.stream(items).anyMatch(inputStr::contains);
    }

    public Command parse() throws DukeException {
        String[] addCommands = new String[]{"todo", "deadline", "event"};
        String[] splitInput = getString().split(" ");
        String commandType = splitInput[0];
        if (commandType.equals("")) {
            throw new DukeException("Your Input String cannot be Empty!");
        } else if (commandType.equals("done")) {
            return new DoneCommand(splitInput);
        } else if (commandType.equals("delete")) {
            return new DeleteCommand(splitInput);
        } else if (commandType.equals("list")) {
            return new ListCommand(splitInput);
        } else if (commandType.equals("bye")) {
            return new ExitCommand(splitInput);
        } else if (stringContainsItemFromList(commandType, addCommands)) {
            return new AddCommand(splitInput);
        } else {
            throw new DukeException("Your Input Command is not Recognized!");
        }
    }

        public AddCommand parseFromFile(boolean isDone) throws DukeException {
            //Assuming that written file will only contain events to be added
            String[] addCommands = new String[]{"todo", "deadline", "event"};
            String[] splitInput = getString().split(" ");
            String commandType = splitInput[0];
            if (commandType.equals("")) {
                throw new DukeException("File has empty input String!");
            } else if (stringContainsItemFromList(commandType, addCommands)) {
                return new AddCommand(splitInput, isDone);
            } else {
                throw new DukeException("Your Input Command from the file is not Recognized!");
            }
        }

    public String getString() {
        return this.inputString;
    }

}
