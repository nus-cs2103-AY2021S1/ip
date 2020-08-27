package main.java.manager;

import main.java.exceptions.InvalidDescriptionException;
import main.java.exceptions.InvalidTimeException;
import main.java.tasks.Deadline;
import main.java.tasks.Event;
import main.java.tasks.Task;
import main.java.tasks.Todo;

/**
 * Represents a platform that converts commands into tasks and actions.
 * Interacts with the parser by receiving commands,
 * passes converted tasks and actions to the task list,
 * sends the task list to the database for storage,
 * and retrieves the task list from the database if needed.
 */
public class Ui {

    private final TaskList taskList = new TaskList();
    private final Storage database = Storage.initializeDatabase();

    /**
     * Converts a command and input into the corresponding task.
     *
     * @return the task that is converted
     */
    public Task convertTask(Commands command, String input) {
        try {
            switch (command) {
            case DEADLINE:
                String deadlineDesc = input.substring("deadline".length(), input.indexOf("/by")).trim();
                String endTime = input.substring(input.indexOf("/by") + "/by".length()).trim();
                return new Deadline(deadlineDesc, endTime);
            case EVENT:
                String eventDesc = input.substring("event".length(), input.indexOf("/at")).trim();
                String time = input.substring(input.indexOf("/at") + "/at".length()).trim();
                return new Event(eventDesc, time);
            case TODO:
                String todoDesc = input.substring("todo".length()).trim();
                return new Todo(todoDesc);
            }
        } catch (InvalidDescriptionException | InvalidTimeException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    /**
     * Converts a command and index into the corresponding action.
     */
    public void convertAction(Commands command, int index, String input) {
        switch (command) {
        case LIST:
            this.taskList.listTasks();
            break;
        case DONE:
            this.taskList.markTaskAsDone(index);
            break;
        case DELETE:
            this.taskList.deleteTask(index);
            break;
        case DELETE_ALL:
            this.taskList.deleteAllTasks();
            break;
        case FIND:
            this.taskList.findTasks(input);
        }
    }

    /**
     * Passes the task into the task list to be added.
     */
    public void passTask(Task task) {
        this.taskList.addTask(task);
    }

    /**
     * Obtains the total number of tasks in the task list.
     *
     * @return the number of tasks in the task list
     */
    public int getTotalTasks() {
        return this.taskList.getNumberOfTasks();
    }

    /**
     * Stores the task list into the database.
     */
    public void storeTasks() {
        this.database.updateDatabase(this.taskList.getList());
    }

    /**
     * Retrieves the task list from the database.
     */
    public void getSavedTasks() {
        this.taskList.setList(this.database.retrieveTaskList());
    }
}
