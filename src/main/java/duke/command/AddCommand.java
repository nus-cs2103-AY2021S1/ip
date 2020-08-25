package duke.command;

import duke.Duke;
import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Represents a user command that adds a task to the task list.
 * This includes user input of todo, deadline, and event.
 */
public class AddCommand extends Command {
    private String userInput;

    public AddCommand(String userInput) {
        this.userInput = userInput;
    }

    // checks if the program should exit.
    public boolean isExit() {
        return false;
    }

    /**
     * Checks for the type of task added by the user based on the user input, and
     * adds the appropriate type of task to the task list.
     *
     * @param tasks List of <code>Task</code> objects.
     * @param ui Ui object created by Duke.
     * @param storage Storage object created by Duke.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task t = new Task(userInput);
        try {
            t.validate();

            String fileString = tasks.listToString();

            if (t.isTodo()) {
                // add to do to fileString
                ToDo todo = t.convertToTodo();
                tasks.add(todo);
                fileString += todo.taskToText() + "\n";

                // saves fileString to txt file
                Storage.save(Duke.FILENAME, fileString);

                // print template message
                System.out.println("    Got it. I've added this task:\n"
                        + "        " + todo + '\n'
                        + "    Now you have " + tasks.size() + " tasks in the list.");

            } else if (t.isDeadline()) {
                Deadline d = t.convertToDeadline();
                tasks.add(d);
                fileString += d.taskToText() + "\n";

                // saves fileString to txt file
                Storage.save(Duke.FILENAME, fileString);

                System.out.println("    Got it. I've added this task:\n"
                        + "        " + d + '\n'
                        + "    Now you have " + tasks.size() + " tasks in the list.");

            } else if (t.isEvent()) {
                Event e = t.convertToEvent();
                tasks.add(e);
                fileString += e.taskToText() + "\n";

                // saves fileString to txt file
                Storage.save(Duke.FILENAME, fileString);

                System.out.println("    Got it. I've added this task:\n"
                        + "        " + e + '\n'
                        + "    Now you have " + tasks.size() + " tasks in the list.");
            }
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }

    }
}

