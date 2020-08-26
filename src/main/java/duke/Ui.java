package duke;

import java.util.Scanner;

public class Ui {

    protected Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

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
     */
    public void formatPrint(String str) {
        String divider = "----------------------------\n";
        String newStr = ("   " + divider + str + "\n" + divider).replaceAll("(\r\n|\n)", "\r\n   ");
        System.out.println(newStr);
    }

    /**
     * Prints error message in the correct format.
     * @param str Error message.
     */
    public void printError(String str) {
        String divider = "############################\n";
        String newStr = ("   " + divider + str + "\n" + divider).replaceAll("(\r\n|\n)", "\r\n   ");
        System.out.println(newStr);
    }

    /**
     * Prints greetings to the user.
     */
    public void printGreetings() {
        String greeting = "Hello I'm duke.Duke, your favourite chatbot! \n\n"
                + "Type 'help' to see the list of command I support. ";
        formatPrint(greeting);
    }

    /**
     * Prints the list of supported commands.
     */
    public void printHelp() {
        String supportedCommands = "I support these commands: \n"
                + "todo: \n"
                + "    add a todo item with a description. \n    format: todo {description} \n"
                + "deadline: \n"
                + "    add a deadline with a description and date. \n"
                + "    format: deadline {description} /by {yyyy-mm-dd} \n"
                + "event: \n"
                + "    add an event with a description, date, start time and end time. \n"
                + "    format: event {description} /at {yyyy-mm-dd} {hh:mm} {hh:mm}\n"
                + "done: \n"
                + "    mark an item as done. \n    format: done {taskNumber} \n"
                + "delete: \n"
                + "    delete an item. \n    format: delete {taskNumber} \n"
                + "list: \n"
                + "    see all the tasks you have now. \n"
                + "bye: \n"
                + "    say goodbye to me :<";
        formatPrint(supportedCommands);
    }

    /**
     * Prints goodbye message.
     */
    public void printBye() {
        formatPrint("Bye! Hope to see you again soon! ");
    }

    /**
     * Shows the list of tasks.
     * @param taskList
     */
    public void showList(TaskList taskList) {
        formatPrint(taskList.printList());
    }

    /**
     * Shows the added task.
     * @param task Added task.
     * @param taskList List of tasks.
     */
    public void showAddedTask(Task task, TaskList taskList) {
        formatPrint(String.format("Got it. I've added this task: \n   %s\nNow you have %d task%s in the list.",
                task, taskList.getSize(), taskList.getSize() > 1 ? "s": ""));
    }

    /**
     * Shows the deleted task.
     * @param task Deleted task.
     * @param taskList List of tasks.
     */
    public void showDeletedTask(Task task, TaskList taskList) {
        formatPrint(String.format("Got it. I've deleted this task: \n   %s\nNow you have %d task%s in the list.",
                task, taskList.getSize(), taskList.getSize() > 1 ? "s": ""));
    }

    /**
     * Shows the task marked as done.
     * @param task Task marked as done.
     */
    public void showMarkedAsDone(Task task) {
        formatPrint("Nice! I've marked this task as done: \n   " + task);
    }

}
