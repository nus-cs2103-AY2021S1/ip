package duke.command;

import java.io.IOException;

import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;

/**
 * Represents an AddCommand that is part of the Command class, regarding adding a task.
 */

public class AddCommand extends Command {


    public AddCommand(String command) {
        super(command, false);
    }

    /**
     * Executes the command to add a task to the list of tasks.
     *
     * @param list Tasklist containing tasks.
     * @param ui Ui for displaying output.
     * @param storage Storage of tasks in a txt file.
     */
    public void execute(TaskList list, Ui ui, Storage storage) {
        try {
            String[] type = this.command.split(" ", 2);
            Task task;
            if (type[0].equals("todo")) {
                if (type.length == 1) {
                    throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                }
                task = new Todo(type[1]);
                storage.addToFile("T | 0 | " + task.getDescription());
            } else if (type[0].equals("deadline")) {
                if (type.length == 1) {
                    throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
                }
                String second = type[1];
                String[] secondSplit = second.split(" /by ", 2);
                task = new Deadline(secondSplit[0], secondSplit[1]);
                storage.addToFile("D | 0 | " + task.getDescription() + " | " + secondSplit[1]);
            } else if (type[0].equals("event")) {
                if (type.length == 1) {
                    throw new DukeException("☹ OOPS!!! The description of a event cannot be empty.");
                }
                String second = type[1];
                String[] secondSplit = second.split(" /at ", 2);
                task = new Event(secondSplit[0], secondSplit[1]);
                storage.addToFile("E | 0 | " + task.getDescription() + " | " + secondSplit[1]);
            } else {
                throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
            list.add(task);
            ui.addedTaskMessage(task, list.size());
        } catch (DukeException e) {
            ui.errorEncounter(e);
        } catch (IOException e) {
            ui.errorEncounter(e);
        }
    }

    @Override
    public String executeChat(TaskList list, Ui ui, Storage storage) {
        try {
            String[] type = this.command.split(" ", 2);
            Task task;
            if (type[0].equals("todo")) {
                if (type.length == 1) {
                    throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                }
                task = new Todo(type[1]);

            } else if (type[0].equals("deadline")) {
                if (type.length == 1) {
                    throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
                }
                String second = type[1];
                String[] secondSplit = second.split(" /by ", 2);
                task = new Deadline(secondSplit[0], secondSplit[1]);
            } else if (type[0].equals("event")) {
                if (type.length == 1) {
                    throw new DukeException("☹ OOPS!!! The description of a event cannot be empty.");
                }
                String second = type[1];
                String[] secondSplit = second.split(" /at ", 2);
                task = new Event(secondSplit[0], secondSplit[1]);
            } else {
                throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
            return ui.addedTaskMessage(task, list.size(), true);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}
