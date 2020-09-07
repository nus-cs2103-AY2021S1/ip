package duke;

import duke.task.Task;

/**
 * Encapsulates the user interface of duke
 */
public class Ui {

    /** String to print upon adding a new task to the task list */
    private final String ADD_TASK_MESSAGE = "Got it. I've added this task:";

    /** String to print upon archiving all tasks */
    private final String ARCHIVE_ALL_TASKS_MESSAGE = "Noted. All tasks have been archived.";

    /** String to print upon archiving of a task */
    private final String ARCHIVE_TASK_MESSAGE = "Noted. I've archived this task:";

    /** String to print upon completion of a task */
    private final String COMPLETE_TASK_MESSAGE = "Nice! I've marked this task as done:";

    /** String to print upon deletion of a task */
    private final String DELETE_TASK_MESSAGE = "Noted. I've removed this task:";

    /** String to print on exit */
    private final String EXIT_MESSAGE = "Bye. Hope to see you again soon!";

    /** String to print at the start of a sublist of matched tasks */
    private final String FOUND_TASKS_MESSAGE = "Here are the matching tasks in your list:";

    /** String to print if no matched tasks were found */
    private final String FOUND_ZERO_TASK_MESSAGE = "I'm sorry, but none of the tasks match the keyword";

    /** Strings to print on start-up */
    private final String[] STARTUP_MESSAGE = new String[] {"Hello! I'm Duke", "What can I do for you?"};

    /** Horizontal line */
    private final String HORIZONTAL_LINE = "    ____________________________________________________________";

    /** String to print upon detecting an invalid character */
    private final String INVALID_CHARACTER_MESSAGE = "You have entered an invalid character \"%s\"";

    /** String to print when an invalid input is detected */
    private final String INVALID_SYNTAX_MESSAGE = "OOPS!!! I'm sorry, but I don't know what that means :(";

    /** String to print when an invalid task index is detected */
    private final String INVALID_TASK_INDEX_MESSAGE = "Please enter a valid task index";

    /** String to print as a header preceding the task list */
    private final String LIST_TASKS_MESSAGE = "Here are the tasks in your list:";

    /** String to print to reflect the number of tasks in the current task list */
    private final String NUM_OF_TASKS_MESSAGE = "Now you have %d tasks in the list";

    /** Text indentation */
    private final String TEXT_INDENTATION = "     ";

    /** String to print when the task list is empty */
    private final String ZERO_TASK_MESSAGE = "Your task list is currently empty. YAY!!! :D";

    /**
     * Gets output strings upon archiving all tasks.
     *
     * @return Output strings upon archiving all tasks
     */
    public String[] getArchiveAllTasksStrings() {
        String[] strings = new String[] {ARCHIVE_ALL_TASKS_MESSAGE, ZERO_TASK_MESSAGE};
        return strings;
    }

    /**
     * Gets output strings upon archiving a task.
     *
     * @param tasks
     * @param task
     * @return Output strings upon archiving a task
     */
    public String[] getArchiveTaskStrings(TaskList tasks, Task task) {
        assert tasks != null;
        assert task != null;
        String[] strings = new String[] {ARCHIVE_TASK_MESSAGE, task.toString(),getNumOfTasksString(tasks)};
        return strings;
    }

    /**
     * Gets the string to print to reflect the number of tasks in the current task list.
     *
     * @param tasks Task list
     * @return zeroTaskMessage if the task list is empty, numOfTasksMessage formatted with the number of
     * tasks if the list is not empty
     */
    private String getNumOfTasksString(TaskList tasks) {
        assert tasks != null;
        int numOfTasks = tasks.getNumOfTasks();
        if (numOfTasks == 0) {
            return ZERO_TASK_MESSAGE;
        } else {
            return String.format(NUM_OF_TASKS_MESSAGE, numOfTasks);
        }
    }

    /**
     * Gets output strings upon completing a task.
     *
     * @param task Completed task
     * @return Output strings upon completing a task
     */
    public String[] getCompleteTaskStrings(Task task) {
        assert task != null;
        String[] strings = new String[] {COMPLETE_TASK_MESSAGE, task.toString()};
        return strings;
    }

    /**
     * Gets output strings upon creating a new task.
     *
     * @param tasks Current task list
     * @param task Created task
     * @return Output strings upon creating a new task
     */
    public String[] getCreateTaskStrings(TaskList tasks, Task task) {
        assert tasks != null;
        assert task != null;
        String[] strings = new String[] {ADD_TASK_MESSAGE, task.toString(), getNumOfTasksString(tasks)};
        return strings;
    }

    /**
     * Gets strings upon deleting a task.
     *
     * @param tasks Current task list
     * @param task Deleted task
     * @return Output strings upon deleting a task
     */
    public String[] getDeleteTaskStrings(TaskList tasks, Task task) {
        assert tasks != null;
        assert task != null;
        String[] strings = new String[] {DELETE_TASK_MESSAGE, task.toString(),getNumOfTasksString(tasks)};
        return strings;
    }

    /**
     * Gets strings upon exit.
     *
     * @return Output strings upon exit
     */
    public String[] getExitStrings() {
        String[] strings = new String[] {EXIT_MESSAGE};
        return strings;
    }

    /**
     * Prints strings upon start-up.
     */
    void printHello() {
        print(STARTUP_MESSAGE);
    }

    /**
     * Gets strings upon start-up.
     *
     * @return Output strings upon start-up
     */
    String[] getHelloStrings() {
        return STARTUP_MESSAGE;
    }

    public String[] getInvalidCharacterStrings(String invalidChar) {
        String[] strings = new String[] {String.format(INVALID_CHARACTER_MESSAGE, invalidChar)};
        return strings;
    }

    /**
     * Gets strings upon handling an invalid input.
     *
     * @return Output strings upon handling an invalid input
     */
    public String[] getInvalidInputStrings() {
        String[] strings = new String[] {INVALID_SYNTAX_MESSAGE};
        return strings;
    }

    /**
     * Gets strings upon receiving an invalid task index.
     *
     * @return Output strings upon receiving an invalid task index
     */
    public String[] getInvalidTaskIndexStrings() {
        String[] strings = new String[] {INVALID_TASK_INDEX_MESSAGE};
        return strings;
    }

    /**
     * Gets string representation of all tasks.
     *
     * @param tasks Current task list
     * @return Output string representation of all tasks
     */
    public String[] getTaskListStrings(TaskList tasks) {
        assert tasks != null;
        if (tasks.isEmpty()) {
            return new String[] {ZERO_TASK_MESSAGE};
        } else {
            String[] strings = new String[tasks.getNumOfTasks() + 1];
            strings[0] = LIST_TASKS_MESSAGE;
            for (int i = 1; i < strings.length; i++) {
                strings[i] = tasks.getTaskAt(i - 1).toString();
            }
            return strings;
        }
    }

    /**
     * Gets strings upon finding matching tasks.
     *
     * @param tasks Sublist of tasks
     * @return Output strings upon finding matching tasks
     */
    public String[] getTasksWithKeywordStrings(Task[] tasks) {
        assert tasks != null;
        String[] strings = new String[tasks.length + 1];
        if (tasks.length == 0) {
            strings[0] = FOUND_ZERO_TASK_MESSAGE;
        } else {
            strings[0] = FOUND_TASKS_MESSAGE;
            for (int i = 1; i < strings.length; i++) {
                strings[i] = tasks[i - 1].toString();
            }
        }
        return strings;
    }

    /**
     * Prints the strings with top and bottom horizontal lines and indentation.
     *
     * @param strings Array of strings to be printed
     */
    public void print(String[] strings) {
        assert strings != null;
        System.out.println(HORIZONTAL_LINE);
        for(String string : strings) {
            System.out.print(TEXT_INDENTATION);
            System.out.println(string);
        }
        System.out.println(HORIZONTAL_LINE);
    }
}
