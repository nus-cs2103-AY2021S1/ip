package duke.util;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.CommandList;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.ListCommand;

import duke.exception.DukeException;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.ToDo;

//TODO: change exceptions to throw from creation instead of checking.
public class Parser {
    public static Command parse(String cmd) {
        int idx = cmd.indexOf(' ');
        CommandList commandList;
        Command command = null;

        try {
            commandList = (idx > 0) ? CommandList.valueOf(cmd.substring(0, idx)) : CommandList.valueOf(cmd);
        } catch (IllegalArgumentException iae) {
            throw new DukeException("This is not in my command list");
        }

        switch (commandList) {
        case bye: //fallthrough
        case exit:
            command = new ExitCommand();
            break;
        case list:
            command = new ListCommand();
            break;
        case todo:
            command = new AddCommand(todo(cmd.substring(4).strip()));
            break;
        case deadline:
            command = new AddCommand(deadline(cmd.substring(8)));
            break;
        case event:
            command = new AddCommand(event(cmd.substring(5)));
            break;
        case done:
            try {
                int selected = Integer.parseInt(cmd.substring(4).strip());
                command = new DoneCommand(selected - 1);
            } catch (NumberFormatException nfe) {
                throw new DukeException("This is not a number for \"done\" command: " + cmd.substring(4));
            }
            break;
        case delete:
            try {
                int selected = Integer.parseInt(cmd.substring(6).strip());
                command = new DeleteCommand(selected - 1);
            } catch (NumberFormatException nfe) {
                throw new DukeException("This is not a number for \"delete\" command: " + cmd.substring(6));
            }
            break;
        }
        return command;
    }

    //TODO: to be removed since ToDo checks for description_blank
    private static ToDo todo(String string) {
        if (string.isBlank()) {
            throw new DukeException("The description cannot be empty");
        }
        return new ToDo(string);
    }

    private static Deadline deadline(String string) {
        String[] split = string.split("/by");

        if (split.length == 1) {
            throw new DukeException("I can't find the \"/by\" keyword...");
        }

        if (split[0].isBlank() || split[1].isBlank()) { //TODO: can be removed since check is done in constructor
            throw new DukeException("The description or deadline of \"deadline\" cannot be empty");
        }
        return new Deadline(split[0].strip(), Util.convertStringToDateTime(split[1].strip()));
    }

    private static Event event(String string) {
        String[] split = string.split("/at");

        if (split.length == 1) {
            throw new DukeException("I can't find the \"/at\" keyword...");
        }

        if (split[0].isBlank() || split[1].isBlank()) {//TODO: can be removed since check is done in constructor
            throw new DukeException("The description or date of \"event\" cannot be empty");
        }

        return new Event(split[0].strip(), Util.convertStringToDateTime(split[1].strip()));
    }
}
