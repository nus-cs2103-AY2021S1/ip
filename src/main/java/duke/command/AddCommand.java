package duke.command;

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
     * @return String of what was added to the task list, in duke's template message.
     */
    public String executeToString(TaskList tasks, Ui ui, Storage storage) {
        Task t = new Task(userInput);
        String result = "";
        try {
            t.validate();

            String fileString = tasks.listToString();

            if (t.isTodo()) {
                ToDo todo = t.convertToTodo();
                tasks.add(todo);
                fileString += todo.taskToText() + "\n";

                result =  ui.showAddTaskResponse(todo, tasks.size());

            } else if (t.isDeadline()) {
                Deadline deadline = t.convertToDeadline();
                tasks.add(deadline);
                fileString += deadline.taskToText() + "\n";

                result =  ui.showAddTaskResponse(deadline, tasks.size());

            } else if (t.isEvent()) {
                Event event = t.convertToEvent();
                tasks.add(event);
                fileString += event.taskToText() + "\n";

                result =  ui.showAddTaskResponse(event, tasks.size());

            }

            // saves fileString to txt file
            Storage.save(fileString);

        } catch (DukeException e) {
            result = e.getMessage();
        }
        return result;
    }
}

