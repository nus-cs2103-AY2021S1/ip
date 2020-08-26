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

public class Parser {
    public static void parseCommands(String userCommand, Ui ui, Storage storage) throws InvalidUserCommandException {
        if (UserCommands.EXIT.getCommandWord().equals(userCommand)) {
            ui.showGoodbyeMessage();
        } else if (UserCommands.LIST.getCommandWord().equals(userCommand)) {
            try {
                ui.showTaskList(storage.load());
            } catch (StorageException e) {
                ui.showErrorMessage(e);
            }
        } else {
            String[] commandDetails = userCommand.split(" ", 2);

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
                throw new InvalidUserCommandException(ui.showInvalidUserCommand(userCommand));
            }
        }
    }
    
    private static void addTodo(String todo, Ui ui, Storage storage) {
        try {
            TaskList tasks = storage.load();

            Todo newTodoTask = new Todo(todo);
            tasks.addNewTask(newTodoTask);
            storage.save(tasks, ui);
            ui.showAddedNewTaskMessage(newTodoTask, storage.load());
        } catch (StorageException e) {
            ui.showErrorMessage(e);
        }
    }
    
    private static void addDeadline(String deadline, Ui ui, Storage storage) {
        try {
            TaskList tasks = storage.load();
            String[] deadlineInformation;

            // Retrieve deadline description and deadline date
            deadlineInformation = deadline.split("/by ");
            String description = deadlineInformation[0];
            String deadlineDate = TaskDate.getDate(deadlineInformation[1]);

            Deadline newDeadlineTask = new Deadline(description, deadlineDate);
            tasks.addNewTask(newDeadlineTask);
            storage.save(tasks, ui);
            ui.showAddedNewTaskMessage(newDeadlineTask, storage.load());   
        } catch (StorageException e) {
            ui.showErrorMessage(e);
        }
    }

    private static void addEvent(String event, Ui ui, Storage storage) {
        try {
            TaskList tasks = storage.load();
            String[] eventInformation;

            // Retrieve event description and event date
            eventInformation = event.split("/at ");
            String description = eventInformation[0];
            String eventDate = TaskDate.getDate(eventInformation[1]);

            Event newEventTask = new Event(description, eventDate);
            tasks.addNewTask(newEventTask);
            storage.save(tasks, ui);
            ui.showAddedNewTaskMessage(newEventTask, storage.load());
        } catch (StorageException e) {
            ui.showErrorMessage(e);
        }
    }
    
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
            } else {
                throw new TaskDoesNotExistException(index);
            }
            
        } catch (InvalidUserCommandException | StorageException e) {
            ui.showErrorMessage(e);
        }
    }
    
    /**
     * Processes the list index in the user command and returns the integer value of the task 
     * index. If a non-numerical number is found, an exception is thrown.
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
            throw new InvalidIndexNumberException();
        }
    }
    
    private static void markTaskAsDone(String taskToMarkDone, Ui ui, Storage storage) {
        try {
            TaskList tasks = storage.load();
            int index = parseTaskIndex(taskToMarkDone);

            // Checks if index is valid
            if (index > 0 && index <= tasks.totalNumberOfTasks()) {
                Task doneTask = tasks.getTask(index -1);
                if (doneTask.hasDoneStatus()) {
                    ui.showAlreadyMarkDoneMessage(doneTask);
                } else {
                    tasks.deleteTask(index - 1);
                    doneTask.markAsDone();
                    tasks.updateTaskList(doneTask, index - 1);
                    storage.save(tasks, ui);
                    ui.showMarkDoneMessage(doneTask);
                }
            } else {
                throw new TaskDoesNotExistException(index);
            }

        } catch (InvalidUserCommandException | StorageException e) {
            ui.showErrorMessage(e);
        }
    }
}
