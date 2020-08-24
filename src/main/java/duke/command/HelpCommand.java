package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class HelpCommand extends Command {

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String help = "These are the available commands:\n" +
                "bye - exits the program\n" +
                "deadline <description> /by <due date and time> - " +
                "adds a deadline with the given description and due date to the duke.task list\n" +
                "delete <duke.task number> - deletes the duke.task corresponding to the number from the duke.task list\n" +
                "done <duke.task number> - marks the duke.task corresponding to the number as done\n" +
                "event <description> /at <due date and time> - " +
                "adds an event with the given description and due date to the duke.task list\n" +
                "help - shows this list of commands\n" +
                "list - shows the contents of the duke.task list\n" +
                "todo <description> - adds a todo duke.task with the given description to the duke.task list";
        ui.say(help);
    }
}
