package duke;

import java.util.Scanner;

import duke.task.Task;

public class Ui {

    protected Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Reads input from the scanner.
     * @return User input.
     */
    public String readInputs() {
        String input = sc.nextLine().strip();
        return input;
    }

    public void close() {
        sc.close();
    }

    /**
     * Formats the element to be printed into the correct format.
     * @param str The element to be printed.
     * @return The text to be displayed.
     */
    public String formatPrint(String str) {
        String divider = "----------------------------\n";
        return (divider + str + "\n" + divider);
    }

    /**
     * Prints error message in the correct format.
     * @param str Error message.
     * @return The text to be displayed.
     */
    public String printError(String str) {
        String divider = "############################\n";
        return (divider + str + "\n" + divider);
    }

    /**
     * Prints greetings to the user.
     * @return The text to be displayed.
     */
    public String printGreetings() {
        String greeting = "Hello I'm Duke, your favourite chatbot! \n\n"
                + "Type 'help' to see the list of command I support. ";
        return formatPrint(greeting);
    }

    /**
     * Prints the list of supported commands.
     * @return The text to be displayed.
     */
    public String printHelp() {
        String supportedCommands = "I support these commands: \n"
                + "todo: \n"
                + "    add a todo item with a description. \n    format: todo {description} \n"
                + "deadline: \n"
                + "    add a deadline with a description and date. \n"
                + "    format: deadline {description} /by {yyyy-mm-dd} \n"
                + "event: \n"
                + "    add an event with a description, date, \n    start time and end time. \n"
                + "    format: event {description} /at \n    {yyyy-mm-dd} {hh:mm} {hh:mm}\n"
                + "done: \n"
                + "    mark an item as done. \n    format: done {taskNumber} \n"
                + "delete: \n"
                + "    delete an item. \n    format: delete {taskNumber} \n"
                + "list: \n"
                + "    see all the tasks you have now. \n"
                + "find: \n"
                + "    search your task list with keywords. \n    format: find {keywords} \n"
                + "snooze: \n"
                + "    snooze your task by one day. \n    format: snooze {taskNumber} \n"
                + "bye: \n"
                + "    say goodbye to me :<";
        return formatPrint(supportedCommands);
    }

    /**
     * Prints goodbye message.
     * @return The text to be displayed.
     */
    public String printBye() {
        return formatPrint("Bye! Hope to see you again soon! ");
    }

    /**
     * Shows the list of tasks.
     * @param taskList Task list.
     * @return The text to be displayed.
     */
    public String showList(TaskList taskList) {
        return formatPrint(taskList.printList());
    }

    /**
     * Shows the added task.
     * @param task Added task.
     * @param taskList List of tasks.
     * @return The test to be displayed.
     */
    public String showAddedTask(Task task, TaskList taskList) {
        return formatPrint(String.format("Got it. I've added this task: \n   %s\nNow you have %d task%s in the list.",
                task, taskList.getSize(), taskList.getSize() > 1 ? "s" : ""));
    }

    /**
     * Shows the tasks that match the search keyword.
     * @param taskList Task list.
     * @param keyword Keyword for finding matching tasks.
     * @return The text to be displayed.
     */
    public String showMatchingTasks(TaskList taskList, String keyword) {
        return formatPrint(taskList.printMatchingTasks(taskList.findTasks(keyword)));
    }

    /**
     * Shows the deleted task.
     * @param task Deleted task.
     * @param taskList List of tasks.
     * @return The text to be displayed.
     */
    public String showDeletedTask(Task task, TaskList taskList) {
        return formatPrint(String.format("Got it. I've deleted this task: \n   %s\nNow you have %d task%s in the list.",
                task, taskList.getSize(), taskList.getSize() > 1 ? "s" : ""));
    }

    /**
     * Shows the snoozed task.
     * @param task Snoozed task.
     * @param taskList List of tasks.
     * @return The text to be displayed.
     */
    public String showSnoozedTask(Task task, TaskList taskList) {
        return formatPrint(String.format("Got it. I've snoozed this task by 1 day: \n   %s\n"
                        + "Now you have %d task%s in the list.",
                task, taskList.getSize(), taskList.getSize() > 1 ? "s" : ""));
    }

    /**
     * Shows the task marked as done.
     * @param task Task marked as done.
     * @return The text to be displayed.
     */
    public String showMarkedAsDone(Task task) {
        return formatPrint("Nice! I've marked this task as done: \n   " + task);
    }

}
