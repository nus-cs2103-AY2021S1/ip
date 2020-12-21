package duke.command;

import java.util.ArrayList;
import java.util.List;

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
     * @return "Bob's" response to user commands
     */
    public static String parseCommands(String userCommand, Ui ui, Storage storage) {
        // For single-word user commands
        if (UserCommands.EXIT.getCommandWord().equals(userCommand)) {
            return ui.showGoodbyeMessage();
        } else if (UserCommands.LIST.getCommandWord().equals(userCommand)) {
            try {
                // Load the saved TaskList from the storage and display it
                return ui.showTaskList(storage.load());
            } catch (StorageException e) {
                // Unable to load saved TaskList
                return ui.showErrorMessage(e);
            }
        }

        return parseMultiWordUserCommands(userCommand, ui, storage);
    }

    /**
     * Processes multi-word user commands.
     *
     * @param userCommand User input command.
     * @param ui User interface which handles the printing the appropriate output.
     * @param storage Local storage which handles the loading and saving of task lists.
     * @return "Bob's" response to multi-word user commands.
     * @throws InvalidUserCommandException If a user command is not recognized by the program.
     */
    private static String parseMultiWordUserCommands(String userCommand, Ui ui, Storage storage)
            throws InvalidUserCommandException {
        // Extract the first word of the user command to obtain the type of user command
        String[] commandDetails = userCommand.split(" ", 2);

        // Contains type of user command
        String command = commandDetails[0];
        try {
            if (UserCommands.TODO.getCommandWord().equals(command)) {
                return addTodo(commandDetails[1], ui, storage);
            } else if (UserCommands.DEADLINE.getCommandWord().equals(command)) {
                return addDeadline(commandDetails[1], ui, storage);
            } else if (UserCommands.EVENT.getCommandWord().equals(command)) {
                return addEvent(commandDetails[1], ui, storage);
            } else if (UserCommands.DELETE.getCommandWord().equals(command)) {
                return deleteTask(commandDetails[1], ui, storage);
            } else if (UserCommands.DONE.getCommandWord().equals(command)) {
                return markTaskAsDone(commandDetails[1], ui, storage);
            } else if (UserCommands.FIND.getCommandWord().equals(command)) {
                return searchTaskListForKeyword(commandDetails[1], ui, storage);
            } else {
                // User commands is not recognized by the system
                throw new InvalidUserCommandException(ui.showInvalidUserCommand(userCommand));
            }
            // If user input only consists of the type of command but no other details
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InvalidUserCommandException(ui.showInvalidUserCommand(userCommand));
        }
    }

    /**
     * Determines is the task already exists in the task list.
     *
     * @param task Task to be determined if it already exists in the task list.
     * @param tasks Task list saved in the local storage.
     * @return True if the task is a duplicate task. Otherwise, returns false.
     */
    private static boolean isRepeatedTask(Task task, TaskList tasks) {
        for (int i = 1; i <= tasks.getTotalNumberOfTasks(); i++) {
            Task taskInTaskList = tasks.getTask(i);
            boolean isRepeatedTask = task.equals(taskInTaskList);

            if (isRepeatedTask) {
                return true;
            }
        }

        return false;
    }

    /**
     * Adds a new todo task to the task list.
     *
     * @param todo Description of new todo task.
     * @param ui User interface which displays a new todo has been added to the task list.
     * @param storage Local storage that loads the old task list and saves the updated task list.
     * @return Message notifying the new todo task has successfully been added to the task list.
     */
    private static String addTodo(String todo, Ui ui, Storage storage) {
        try {
            TaskList tasks = storage.load();
            assert tasks != null;

            Todo newTodoTask = new Todo(todo);
            if (isRepeatedTask(newTodoTask, tasks)) {
                return ui.showTaskIsAlreadyInTaskListMessage(newTodoTask);
            }

            tasks.addNewTask(newTodoTask);
            storage.save(tasks);

            return ui.showAddedNewTaskMessage(newTodoTask, storage.load());
        } catch (StorageException e) {
            // Unable to load or save task list
            return ui.showErrorMessage(e);
        }
    }

    /**
     * Retrieves deadline and event tasks' information. The information includes the description and date of the
     * deadline or event.
     *
     * @param taskDescriptionAndDate Deadline and event tasks' information.
     * @param taskSeparator Deadline or event indicator that splits the information to obtain the description and date
     * @return Deadline and event tasks' description and date.
     */
    private static String[] getTaskInformation(String taskDescriptionAndDate, String taskSeparator) {
        String[] taskInformation;

        taskInformation = taskDescriptionAndDate.split(taskSeparator);
        return taskInformation;
    }

    /**
     * Returns the task description.
     *
     * @param taskInformation Task information including the task description and date.
     * @return Task description.
     */
    private static String getTaskDescription(String[] taskInformation) {
        return taskInformation[0];
    }

    /**
     * Returns the task date.
     *
     * @param taskInformation Task information including the task description and date.
     * @return Task date.
     */
    private static String getTaskDate(String[] taskInformation) {
        String date = taskInformation[1];
        return TaskDate.getDate(date);
    }

    /**
     * Adds a new task with a deadline to the task list.
     *
     * @param deadline Description of new task with the deadline date.
     * @param ui User interface which displays a new deadline has been added to the task list.
     * @param storage Local storage that loads the old task list and saves the updated task list.
     * @return Message notifying the new task with a deadline has successfully been added to the task list.
     */
    private static String addDeadline(String deadline, Ui ui, Storage storage) {
        try {
            TaskList tasks = storage.load();
            assert tasks != null;

            // Retrieve deadline description and deadline date
            String[] deadlineInformation = getTaskInformation(deadline, DEADLINE_TASK_SEPARATOR);
            String description = getTaskDescription(deadlineInformation);
            String deadlineDate = getTaskDate(deadlineInformation);

            Deadline newDeadlineTask = new Deadline(description, deadlineDate);

            if (isRepeatedTask(newDeadlineTask, tasks)) {
                return ui.showTaskIsAlreadyInTaskListMessage(newDeadlineTask);
            }

            tasks.addNewTask(newDeadlineTask);
            storage.save(tasks);

            return ui.showAddedNewTaskMessage(newDeadlineTask, storage.load());
        } catch (StorageException e) {
            // Unable to load or save task list
            return ui.showErrorMessage(e);
        }
    }

    /**
     * Adds a new event task to the task list.
     *
     * @param event Description of new event with event date.
     * @param ui User interface which displays a new event has been added to the task list.
     * @param storage Local storage that loads the old task list and saves the updated task list.
     * @return Message notifying the new event task has successfully been added to the task list.
     */
    private static String addEvent(String event, Ui ui, Storage storage) {
        try {
            TaskList tasks = storage.load();
            assert tasks != null;

            // Retrieve event description and event date
            String[] eventInformation = getTaskInformation(event, EVENT_TASK_SEPARATOR);
            String description = getTaskDescription(eventInformation);
            String eventDate = getTaskDate(eventInformation);

            Event newEventTask = new Event(description, eventDate);

            if (isRepeatedTask(newEventTask, tasks)) {
                return ui.showTaskIsAlreadyInTaskListMessage(newEventTask);
            }

            tasks.addNewTask(newEventTask);
            storage.save(tasks);

            return ui.showAddedNewTaskMessage(newEventTask, storage.load());
        } catch (StorageException e) {
            // Unable to load or save task list
            return ui.showErrorMessage(e);
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
     * Deletes the specified task from the task list.
     *
     * @param taskToDelete List index of the task to be deleted.
     * @param ui User interface which displays that the task has been deleted to the task list.
     * @param storage Local storage that loads the old task list and saves the updated task list.
     * @return Message notifying that the task has been deleted from the task list.
     * @throws TaskDoesNotExistException If taskToDelete is less than 0 or more than the greatest task index.
     */
    private static String deleteTask(String taskToDelete, Ui ui, Storage storage) throws TaskDoesNotExistException {
        try {
            TaskList tasks = storage.load();
            assert tasks != null;

            int index = parseTaskIndex(taskToDelete);
            boolean isValidTask = index > 0 && index <= tasks.getTotalNumberOfTasks();

            // If task specified does not exist in the task list
            if (!isValidTask) {
                throw new TaskDoesNotExistException(index);
            }

            Task deletedTask = tasks.getTask(index);
            tasks.deleteTask(index);
            storage.save(tasks);

            return ui.showDeleteTaskMessage(deletedTask, storage.load());

        // If index specified by users is not a number or task list fails to load or save
        } catch (InvalidUserCommandException | StorageException e) {
            return ui.showErrorMessage(e);
        }
    }

    /**
     * Marks the specified task as done.
     *
     * @param taskToMarkDone List index of the task to be marked as done.
     * @param ui User interface which displays the specified task has been marked as done.
     * @param storage Local storage that loads the old task list and saves the updated task list.
     * @return Message notifying users the task is marked done or has already been marked done.
     * @throws TaskDoesNotExistException If taskToMarkDone is less than 0 or more than the greatest task list index.
     */
    private static String markTaskAsDone(
            String taskToMarkDone, Ui ui, Storage storage) throws TaskDoesNotExistException {

        try {
            TaskList tasks = storage.load();
            assert tasks != null;

            int index = parseTaskIndex(taskToMarkDone);
            boolean isValidTask = index > 0 && index <= tasks.getTotalNumberOfTasks();

            // If task specified does not exist in the task list
            if (!isValidTask) {
                throw new TaskDoesNotExistException(index);
            }

            Task doneTask = tasks.getTask(index);

            // Task has already been marked as done
            if (doneTask.hasDoneStatus()) {
                return ui.showAlreadyMarkDoneMessage(doneTask);
            } else if (!doneTask.hasDoneStatus()) {
                doneTask.markAsDone();
                tasks.updateTaskList(doneTask, index);
                storage.save(tasks);
                return ui.showMarkDoneMessage(doneTask);

            // Should not reach this line
            } else {
                assert false;
                return "";
            }

        // If index specified by users is not a number or task list fails to load or save
        } catch (InvalidUserCommandException | StorageException e) {
            return ui.showErrorMessage(e);
        }
    }

    /**
     * Adds the task to the list of tasks containing the keyword if the task contains the keyword indicated by users.
     *
     * @param keyword Keyword indicated by users.
     * @param task The task to be determined if it contains the keyword.
     * @param tasksContainingKeyword A list consisting of tasks that contains the keyword.
     */
    private static void addTaskIfIncludesKeyword(String keyword, Task task, List<Task> tasksContainingKeyword) {
        boolean containsKeyword = task.toString().contains(keyword);

        if (containsKeyword) {
            tasksContainingKeyword.add(task);
        }
    }

    /**
     * Retrieves the tasks that contain a keyword indicated by users.
     *
     * @param keyword Keyword indicated by users.
     * @param tasks Task list saved in the local storage.
     * @return A list of tasks saved in the task list that also contain the keyword.
     */
    private static List<Task> getTasksContainingKeyword(String keyword, TaskList tasks) {
        List<Task> tasksContainingKeyword = new ArrayList<>();

        for (int i = 1; i <= tasks.getTotalNumberOfTasks(); i++) {
            Task task = tasks.getTask(i);
            addTaskIfIncludesKeyword(keyword, task, tasksContainingKeyword);
        }

        return tasksContainingKeyword;
    }

    /**
     * Finds all the tasks in the task list that contains the keyword.
     *
     * @param keyword Keyword found in the filtered tasks.
     * @param ui User interface which displays the filtered tasks.
     * @param storage ocal storage that loads the saved task list.
     * @return All the tasks in the task list that contains the keyword.
     */
    private static String searchTaskListForKeyword(String keyword, Ui ui, Storage storage) {
        try {
            TaskList tasks = storage.load();

            List<Task> tasksContainingKeyword = getTasksContainingKeyword(keyword, tasks);

            assert tasks != null;

            TaskList filteredTaskList = new TaskList(tasksContainingKeyword);
            return ui.showFilteredByKeywordTaskList(filteredTaskList, keyword);
        } catch (StorageException e) {
            return ui.showErrorMessage(e);
        }
    }
}
