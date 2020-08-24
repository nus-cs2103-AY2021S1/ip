package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.task.*;

/**
 * Command when user adds a new task, which can be a to-do, event, or deadline.
 */
public class AddCommand extends Command {
    /**The specific type of task to be added*/
    private final Commands c;
    private final String userInput;

    public AddCommand(String userInput, Commands c) {
        this.userInput = userInput;
        this.c = c;
    }

    @Override
    public void execute(TaskList task, Ui ui, Storage storage) throws DukeException {
        switch (c) {
            case TODO:
                addToDoTask(this.userInput, task, ui, storage);
                break;
            case EVENT:
                addEventTask(this.userInput, task, ui, storage);
                break;
            case DEADLINE:
                addDeadlineTask(this.userInput, task, ui, storage);
                break;
            default:
                throw new DukeException("I don't recognize the type of task you are trying to add");
        }

    }

    private static boolean emptyToDoDescription(String userInput) {
        return userInput.length() <= 4;
    }

    private void addToDoTask(String userInput ,TaskList taskList, Ui ui, Storage storage) throws DukeException {
        if (emptyToDoDescription(userInput)) {
            throw new DukeException("The description of a todo cannot be empty.");
        }

        String taskDescription = userInput.substring(5);
        ToDo newToDoItem = new ToDo(taskDescription);
        addItem(newToDoItem, taskList, ui, storage); // Add to taskList
    }

    private void addEventTask(String userInput ,TaskList taskList, Ui ui, Storage storage) throws DukeException {
        String taskDescription = userInput.substring(6, userInput.indexOf("/at") - 1);
        String eventDateTime = userInput.substring(userInput.indexOf("/at") + 4);
        Event newEventItem  = new Event(taskDescription, eventDateTime);
        addItem(newEventItem, taskList, ui, storage);
    }

    private void addDeadlineTask(String userInput ,TaskList taskList, Ui ui, Storage storage) throws DukeException {
        String taskDescription = userInput.substring(9, userInput.indexOf("/by") - 1);
        String deadlineBy = userInput.substring(userInput.indexOf("/by") + 4);
        Deadline newDeadlineItem = new Deadline(taskDescription, deadlineBy);
        addItem(newDeadlineItem, taskList, ui, storage);
    }

    // Adds a task item to duke.Duke's taskList, which may be an duke.tasks.Event, ToDoItem, duke.Deadline
    public void addItem(Task newTask, TaskList taskList, Ui ui, Storage storage) {
        taskList.add(newTask);
        storage.createTask(newTask); // Add to storage database
        int listSize = taskList.size();
        ui.print("Got it. I've added this task:\n   " +
                newTask.toString() + "\nNow you have " + (listSize)
                + (listSize > 1 ? " tasks" : " task")
                + " in the list.");

    }
}
