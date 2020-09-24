package duke.tasks;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/**
 * Represents a TaskList of all the tasks managed in Duke.
 */
public class TaskList {
    protected ArrayList<Task> taskLs;

    public TaskList() {
        taskLs = new ArrayList<>();
    }

    public ArrayList<Task> getTaskLs() {
        return this.taskLs;
    }

    public void add(Task toAdd) {
        this.taskLs.add(toAdd);
    }

    /**
     * Prints out a statement to inform users that the input
     * is invalid.
     */
    public static void confused() {
        String str = "OOPS!!! I'm sorry, but I don't know what that means :-(";
        System.out.println(str);
    }

    /**
     * Removes the task from the list of tasks.
     * @param toDelete task to be removed.
     */
    public void delete(String toDelete) {
        String command = toDelete.replaceAll("[^\\d.]", "");
        int i = Integer.parseInt(command.trim());
        Task deletedTask = this.taskLs.get(i - 1);
        this.taskLs.remove(i - 1);
    }

    /**
     * List all the tasks in the task list.
     * @return output for the console.
     */
    public String list() {
        String printGui = "";
        printGui = printGui + "Here are the tasks in your list: " + "\n";
        int size = this.taskLs.size();
        int i = 1;

        while (i != (size + 1)) {
            printGui = printGui + i + ". " + this.taskLs.get(i - 1) + "\n";
            i++;
        }
        return printGui;
    }

    /**
     * Marks the task as done.
     * @param toPrint contains int to mark the specific task as done.
     */
    public void done(String toPrint) {
        try {
            String command = toPrint.replaceAll("[^\\d.]", "");
            int indexCommand = Integer.parseInt(command.trim());
            Task completedTask = this.taskLs.get(indexCommand - 1);
            completedTask.markAsDone();
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            throw e;
        }
    }

    /**
     * Creates a String for a done input to print out on the console.
     * @param toPrint user input.
     * @return output for the console.
     */
    public String doneString(String toPrint) {
        String command = toPrint.replaceAll("[^\\d.]", "");
        int indexCommand = Integer.parseInt(command.trim());
        Task completedTask = this.taskLs.get(indexCommand - 1);

        String printGui = "";
        printGui = printGui + "Nice! I've marked this task as done: " + "\n";
        printGui = printGui + "[" + completedTask.getStatusIcon() + "] " + completedTask.description;
        return printGui;
    }

    /**
     * Creates a todo task and adds it to the list of tasks.
     * @param toPrint Description of task.
     */
    public void todo(String toPrint) throws DukeException {
        try {
            toPrint = toPrint.substring(4);
            if (toPrint.isEmpty() | toPrint.length() == 1) {
                throw new DukeException("");
            }
            Todo taskTodo = new Todo(toPrint);
            this.taskLs.add(taskTodo);

        } catch (DukeException e) {
            throw e;
        }
    }

    /**
     * Creates a string for a todo input to print out on the console
     * @param toPrint user input
     * @return output for the console
     */
    public String todoString(String toPrint) {
        toPrint = toPrint.substring(4);
        Todo taskTodo = new Todo(toPrint);
        String printGui = "";
        printGui = printGui + "Got it. I've added this task:" + "\n";
        printGui = printGui + taskTodo + "\n";
        printGui = printGui + "Now you have " + this.taskLs.size() + " tasks in the list.";
        return printGui;
    }

    /**
     * Creates a event task and adds it to the list of tasks.
     * @param toPrint Description and at of task.
     */
    public void event(String toPrint) {
        try {
            toPrint = toPrint.substring(5);
            String[] arrToPrint = toPrint.split("/at ");
            Event taskEvent = new Event(arrToPrint[0], arrToPrint[1]);
            this.taskLs.add(taskEvent);

        } catch (ArrayIndexOutOfBoundsException | DateTimeParseException e) {
            throw e;
        }
    }

    /**
     * Creates a string for a event input to print out on the console
     * @param toPrint user input
     * @return output for the console
     */
    public String eventString(String toPrint) {
        toPrint = toPrint.substring(5);
        String[] arrtoPrint = toPrint.split("/at ");

        Event taskEvent = new Event(arrtoPrint[0], arrtoPrint[1]);
        String printGui = "";
        printGui = printGui + "Got it. I've added this task:" + "\n";
        printGui = printGui + taskEvent + "\n";
        printGui = printGui + "Now you have " + this.taskLs.size() + " tasks in the list.";
        return printGui;
    }

    /**
     * Creates a deadline task and adds it to the list of tasks.
     * @param toPrint Description and by of task.
     */
    public void deadline(String toPrint) {
        try {
            toPrint = toPrint.substring(8);
            String[] arrtoPrint = toPrint.split("/by ");

            Deadline taskDeadline = new Deadline(arrtoPrint[0], arrtoPrint[1]);
            this.taskLs.add(taskDeadline);



        } catch (ArrayIndexOutOfBoundsException | DateTimeParseException e) {
            throw e;
        }
    }

    /**
     * Creates a string for a deadline input to print out on the console
     * @param toPrint user input
     * @return output for the console
     */
    public String deadlineString(String toPrint) {
        toPrint = toPrint.substring(8);
        String[] arrtoPrint = toPrint.split("/by ");

        Deadline taskDeadline = new Deadline(arrtoPrint[0], arrtoPrint[1]);
        String printGui = "";
        printGui = printGui + "Got it. I've added this task:" + "\n";
        printGui = printGui + taskDeadline + "\n";
        printGui = printGui + "Now you have " + this.taskLs.size() + " tasks in the list.";
        return printGui;
    }

    /**
     * Finds the tasks in the list of tasks matching the keyword.
     * @param toPrint keyword entered by the user
     * @return String of tasks to be printed on the console
     */
    public String find(String toPrint) {
        try {
            TaskList duplicateTaskLs = new TaskList();
            duplicateTaskLs.taskLs = new ArrayList<>(this.getTaskLs());

            toPrint = toPrint.substring(4);
            String finalToPrint = toPrint;

            duplicateTaskLs.taskLs.removeIf(n -> !n.getDescription().contains(finalToPrint));

            String printGui = "";
            printGui = printGui + "Here are the matching tasks in your list: " + "\n";
            return duplicateTaskLs.findList(printGui);
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            return "Format of find command is incorrect";
        }
    }

    /**
     * Lists the task from the task list.
     * @param printGui Intro before listing the matching tasks.
     * @return String of tasks in the task list.
     */
    public String findList(String printGui) {
        int size = this.taskLs.size();
        int i = 1;
        while (i != (size + 1)) {
            printGui = printGui + i + ". " + this.taskLs.get(i - 1) + "\n";
            i++;
        }
        return printGui;
    }

    public String help() {
        String printGui = "";
        printGui += "Here are the available commands: " + "\n";
        printGui += "todo <instructions> e.g todo read book" + "\n";
        printGui += "deadline <instructions> e.g deadline fly /by 2018-03-03" + "\n";
        printGui += "event <instructions> e.g event jog /at 2017-04-02" + "\n";
        printGui += "done <integer> e.g done 2 to mark the second task as done" + "\n";
        printGui += "find <keyword> e.g find book to find all tasks matching the keyword" + "\n";
        printGui += "type list to see your current task list" +"\n";
        printGui += "type bye to terminate Duke";

        return printGui;
    }

}
