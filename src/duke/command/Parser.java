package duke.command;

import duke.exception.InvalidIndexNumberException;
import duke.exception.InvalidUserCommandException;
import duke.exception.StorageException;
import duke.exception.TaskDoesNotExistException;
import duke.storage.Storage;
import duke.storage.TaskList;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import duke.ui.Ui;

/**
 * Processes user commands to determine the appropriate course of action to display the correct 
 * response by Duke.
 */
public class Parser {
    private static final String DEADLINE_TASK_SEPARATOR = "/by ";
    private static final String EVENT_TASK_SEPARATOR = "/at ";
    
    /**
     * Processes user commands to determine the appropriate actions needed to display the 
     * appropriate output.
     * 
     * @param userCommand User input command.
     * @param ui User interface which handles the printing the appropriate output.
     * @param storage Local storage which handles the loading and saving of task lists.
     * @throws InvalidUserCommandException If a user command is not recognized by the program.
     */
    public static void parseCommands(String userCommand, Ui ui, Storage storage) throws InvalidUserCommandException {
        if (UserCommands.EXIT.getCommandWord().equals(userCommand)) {
            ui.showGoodbyeMessage();
        } else if (UserCommands.LIST.getCommandWord().equals(userCommand)) {
            try {
                // Load the saved TaskList from the storage and display it
                ui.showTaskList(storage.load());
            } catch (StorageException e) {
                // Unable to load saved TaskList
                ui.showErrorMessage(e);
            }
            
        // For multi-word user commands
        } else {
            // Extract the first word of the user command to obtain the type of user command
            String[] commandDetails = userCommand.split(" ", 2);

            // Contains type of user command
            String command = commandDetails[0];
            
            if (UserCommands.TODO.getCommandWord().equals(command)) {
                addTodo(commandDetails[1], ui, storage);
            } else if (UserCommands.DEADLINE.getCommandWord().equals(command)) {
                addDeadline(commandDetails[1], ui, storage);
            } else if (UserCommands.EVENT.getCommandWord().equals(command)) {
                addEvent(commandDetails[1], ui, storage);
            } else if (UserCommands.DELETE.getCommandWord().equals(command)) {
                deleteTask(commandDetails[1], ui, storage);
            } else if (UserCommands.DONE.getCommandWord().equals(command)) {
                markTaskAsDone(commandDetails[1], ui, storage);
            } else {
                // User commands is not recognized by the system
                throw new InvalidUserCommandException(ui.showInvalidUserCommand(userCommand));
            }
        }
    }

    /**
     * Adds a new todo task to the task list.
     * 
     * @param todo Description of new todo task.
     * @param ui User interface which displays a new todo has been added to the task list.
     * @param storage Local storage that loads the old task list and saves the updated task list.
     */
    private static void addTodo(String todo, Ui ui, Storage storage) {
        try {
            TaskList tasks = storage.load();

            Todo newTodoTask = new Todo(todo);
            tasks.addNewTask(newTodoTask);
            storage.save(tasks, ui);
            ui.showAddedNewTaskMessage(newTodoTask, storage.load());
        } catch (StorageException e) {
            // Unable to load or save task list
            ui.showErrorMessage(e);
        }
    }

    /**
     * Adds a new task with a deadline to the task list.
     * 
     * @param deadline Description of new task with the deadline date.
     * @param ui User interface which displays a new deadline has been added to the task list.
     * @param storage Local storage that loads the old task list and saves the updated task list.
     */
    private static void addDeadline(String deadline, Ui ui, Storage storage) {
        try {
            TaskList tasks = storage.load();
            String[] deadlineInformation;

            // Retrieve deadline description and deadline date
            deadlineInformation = deadline.split(DEADLINE_TASK_SEPARATOR);
            String description = deadlineInformation[0];
            String deadlineDate = TaskDate.getDate(deadlineInformation[1]);

            Deadline newDeadlineTask = new Deadline(description, deadlineDate);
            tasks.addNewTask(newDeadlineTask);
            storage.save(tasks, ui);
            ui.showAddedNewTaskMessage(newDeadlineTask, storage.load());   
        } catch (StorageException e) {
            // Unable to load or save task list
            ui.showErrorMessage(e);
        }
    }

    /**
     * Adds a new event task to the task list.
     * 
     * @param event Description of new event with event date.
     * @param ui User interface which displays a new event has been added to the task list.
     * @param storage Local storage that loads the old task list and saves the updated task list.
     */
    private static void addEvent(String event, Ui ui, Storage storage) {
        try {
            TaskList tasks = storage.load();
            String[] eventInformation;

            // Retrieve event description and event date
            eventInformation = event.split(EVENT_TASK_SEPARATOR);
            String description = eventInformation[0];
            String eventDate = TaskDate.getDate(eventInformation[1]);

            Event newEventTask = new Event(description, eventDate);
            tasks.addNewTask(newEventTask);
            storage.save(tasks, ui);
            ui.showAddedNewTaskMessage(newEventTask, storage.load());
        } catch (StorageException e) {
            // Unable to load or save task list
            ui.showErrorMessage(e);
        }
    }

    /**
     * Deletes the specified task from the task list.
     * 
     * @param taskToDelete List index of the task to be deleted.
     * @param ui User interface which displays that the task has been deleted to the task list.
     * @param storage Local storage that loads the old task list and saves the updated task list.
     */
    private static void deleteTask(String taskToDelete, Ui ui, Storage storage) {
        try {
            TaskList tasks = storage.load();
            int index = parseTaskIndex(taskToDelete);
            
            // Checks if index is valid
            if (index > 0 && index <= tasks.totalNumberOfTasks()) {
                Task deletedTask = tasks.getTask(index - 1);
                tasks.deleteTask(index - 1);
                storage.save(tasks, ui);
                ui.showDeleteTaskMessage(deletedTask, storage.load());

            // If task specified does not exist in the task list
            } else {
                throw new TaskDoesNotExistException(index);
            }
            
        // If index specified by users is not a number or task list fails to load or save
        } catch (InvalidUserCommandException | StorageException e) {
            ui.showErrorMessage(e);
        }
    }

    /**
     * Processes the list index in the user command and returns the numerical value of the task 
     * index as an integer. If a non-numerical number is found, an exception is thrown.
     * 
     * @param inputIndex List index indicated in the user command.
     * @return Integer value of list index.
     * @throws InvalidIndexNumberException If inputIndex is not an integer.
     */
    private static int parseTaskIndex(String inputIndex) throws InvalidIndexNumberException {
        try {
            int index = Integer.parseInt(inputIndex);
            return index;
        } catch (NumberFormatException e) {
            // Unable to convert inputIndex from String to an int
            throw new InvalidIndexNumberException();
        }
    }

    /**
     * Marks the specified task as done.
     * 
     * @param taskToMarkDone List index of the task to be marked as done.
     * @param ui User interface which displays the specified task has been marked as done.
     * @param storage Local storage that loads the old task list and saves the updated task list.
     */
    private static void markTaskAsDone(String taskToMarkDone, Ui ui, Storage storage) {
        try {
            TaskList tasks = storage.load();
            int index = parseTaskIndex(taskToMarkDone);

            // Checks if index is valid
            if (index > 0 && index <= tasks.totalNumberOfTasks()) {
                Task doneTask = tasks.getTask(index -1);
                
                // Task has already been marked as done
                if (doneTask.hasDoneStatus()) {
                    ui.showAlreadyMarkDoneMessage(doneTask);
                } else {
                    doneTask.markAsDone();
                    tasks.updateTaskList(doneTask, index - 1);
                    storage.save(tasks, ui);
                    ui.showMarkDoneMessage(doneTask);
                }
                
            // If task specified does not exist in the task list
            } else {
                throw new TaskDoesNotExistException(index);
            }

        // If index specified by users is not a number or task list fails to load or save
        } catch (InvalidUserCommandException | StorageException e) {
            ui.showErrorMessage(e);
        }
    }
}
