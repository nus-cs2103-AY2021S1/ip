package command;

import exception.TodoException;
import storage.Storage;
import task.Task;
import task.Todo;
import taskList.TaskList;
import undoStack.UndoStack;

import java.util.ArrayList;


/**
 * A subclass of Command.
 * Handles "todo" command.
 */
public class TodoCommand extends Command {
    private static final String ADD_TASK_TITLE = "Got it. I've added this task:";
    private String[] input;

    /**
     * Constructor.
     * @param input user input.
     */
    public TodoCommand(String[] input) {
        super();
        this.input = input;
    }

    /**
     * Executes the command.
     * @param tasks a list of tasks.
     * @param storage the storage working on data file.
     * @throws TodoException to show incorrect user input.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws TodoException {
        UndoStack.add(tasks);
        ArrayList<Task> store = tasks.getTaskList();
        if (input.length == 1) {
            throw new TodoException("☹ OOPS!!! The description of a todo cannot be empty.");
        }
        String description = "";
        for (int i = 1; i < input.length; i++) {
            description = description + input[i] + " ";
        }
        Todo newTask = new Todo(description.trim());

        store.add(newTask);
        storage.save(new TaskList(store));
        return ADD_TASK_TITLE + "\n"
                + newTask + "\n"
                + "Now you have " + store.size() + " tasks in the list.";
    }

    /**
     * Returns isDone to stop user from entering command.
     * @return false to continue to accept user input.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
