package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import duke.util.DateTimeHandler;

/**
 * Encapsulates the handling of user's input.
 */
public class Parser {

    private final Storage storage;
    private final Ui ui;
    private final TaskList taskList;

    public Parser(Storage storage) {
        this.storage = storage;
        this.ui = this.storage.getUi();
        this.taskList = this.storage.getTaskList();
    }

    /**
     * Processes the input fed to Duke.
     *
     * @param input Input string.
     * @throws DukeException If input is invalid.
     */
    public void processInput(String input) throws DukeException {
        if (input.equals("bye")) {
            this.ui.printMessage("Bye. Hope to see you again soon!");
        } else if (input.equals("list")) {
            this.ui.printTaskList(this.taskList);
        } else if (input.contains("delete")) {
            deleteTask(input);
        } else if (input.contains("done")) {
            markTaskAsDone(input);
        } else if (input.contains("get")) {
            printTasksFromDate(input);
        } else if (input.contains("todo") || input.contains("event") || input.contains("deadline")) {
            createTask(input);
        } else {
            throw new DukeException("I'm sorry, but I don't know what that means. \u2639\n");
        }
    }

    /**
     * Returns a footer containing the number of tasks in the list.
     *
     * @return Footer string containing the number of tasks.
     */
    private String numOfTasksFooter() {
        int numOfTasks = this.taskList.getNumOfTasks();
        if (numOfTasks == 1) {
            return "Now you have " + numOfTasks + " task in the list.\n";
        } else {
            return "Now you have " + numOfTasks + " tasks in the list.\n";
        }
    }

    /**
     * Deletes a task specified by the user.
     *
     * @param input Input string indicating which task's index to be deleted.
     * @throws DukeException If input is invalid.
     */
    private void deleteTask(String input) throws DukeException {
        try {
            int number = Integer.parseInt(input.substring(7));
            int index = number - 1;
            Task task = this.taskList.getTask(index);
            this.taskList.deleteTask(index);    // delete task from the list
            this.storage.writeToSaveFile();    // edit the data in storage
            String confirmationMessage = "Noted. I've removed this task:\n"
                    + task.toString()
                    + "\n"
                    + this.numOfTasksFooter();
            this.ui.printMessage(confirmationMessage);    // print delete confirmation message
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Which task do you want to delete?\n");
        } catch (NumberFormatException e) {
            throw new DukeException("Enter the index of the task to be deleted.\n");
        }
    }

    /**
     * Marks a task as done.
     *
     * @param input Input string describing which task's index is done.
     * @throws DukeException If input is invalid.
     */
    private void markTaskAsDone(String input) throws DukeException {
        try {
            int number = Integer.parseInt(input.substring(5));
            Task task = this.taskList.getTask(number - 1);
            task.markAsDone();
            this.storage.writeToSaveFile();    // write task's data to storage
            String confirmationMessage = "Nice! I've marked this as done:\n"
                    + task.toString()
                    + "\n";
            this.ui.printMessage(confirmationMessage);    // print mark task as done confirmation message
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Which task have you done?\n");
        } catch (NumberFormatException e) {
            throw new DukeException("Enter the index of the task done.\n");
        }
    }

    /**
     * Prints the tasks with the date required by the user.
     *
     * @param input Date of tasks required.
     * @throws DukeException If input is invalid.
     */
    private void printTasksFromDate(String input) throws DukeException {
        try {
            String requiredDate = DateTimeHandler.parseDate(input.substring(4));
            boolean hasRequiredTasks = false;
            StringBuilder requiredTasks = new StringBuilder();

            int number = 0;
            for (int i = 0; i < this.taskList.getNumOfTasks(); i++) {
                String taskString = this.taskList.getTask(i).toString();
                if (taskString.contains(requiredDate)) {
                    hasRequiredTasks = true;
                    number++;
                    requiredTasks.append(number).append(". ").append(taskString).append("\n");
                }
            }

            if (hasRequiredTasks) {
                String taskMessage = "Here are the task(s) from " + requiredDate + ":\n" + requiredTasks;
                this.ui.printMessage(taskMessage);
            } else {
                this.ui.printMessage("You have no tasks from " + requiredDate + ".");
            }
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Enter the date you want to get tasks from.\n");
        }
    }

    /***
     * Creates a task.
     * Either a todo, an event or a deadline.
     *
     * @param input Input string describing a task.
     * @throws DukeException If input is invalid.
     */
    private void createTask(String input) throws DukeException {
        Task task;
        String taskString;

        try {
            if (input.contains("todo")) {    // todo
                taskString = input.substring(5);
                task = new Todo(taskString);
            } else if (input.contains("event")) {    // event
                taskString = input.substring(6);
                String[] arr = taskString.split(" /at ", 2);
                if (arr.length < 2 || arr[1].equals("")) {
                    throw new DukeException("Enter the date and time of the event after \"/at\".\n");
                }
                task = new Event(arr[0], arr[1]);
            } else {    // deadline
                taskString = input.substring(9);
                String[] arr = taskString.split(" /by ", 2);
                if (arr.length < 2 || arr[1].equals("")) {
                    throw new DukeException("Enter the date and time of the deadline after \"/by\".\n");
                }
                task = new Deadline(arr[0], arr[1]);
            }
        } catch (IndexOutOfBoundsException e) {
            String typeOfTask = input.contains("todo")
                    ? "a todo"
                    : input.contains("event")
                    ? "an event"
                    : "a deadline";
            throw new DukeException("The description of " + typeOfTask + " cannot be empty.\n");
        }

        this.taskList.addTask(task);
        this.storage.writeToSaveFile();    // write task's data to storage
        String confirmationMessage = "Got it. I've added this task:\n"
                + task.toString()
                + "\n"
                + numOfTasksFooter();
        this.ui.printMessage(confirmationMessage);    // print create task confirmation message
    }
}
