package duke;
import java.io.File;
import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    boolean exited = false;
    TaskList tl;
    Storage s;
    Ui(TaskList tl, Storage s) {
        this.tl = tl;
        this.s = s;
    }

    /**
     * Starts the scanner and takes in the commands
     */
    public void start() {
        String logo = " ____        _\n"
                + "|  _ \\ _   _| | _____\n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo + "\n" + "I can be your friend who manages your task!");

        try {
            while (!exited) {
                Scanner sc = new Scanner(System.in);
                String command = sc.nextLine();
                Parser.processCommand(command, tl, this);
            }
        } catch(Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Stops the scanner when the user inputs in "bye"
     */
    public void stop() {
        exited = true;
    }

    /**
     * Prints out a string everytime the user puts up a task
     * @param arr temporary list of tasks
     * @param t the task to print out
     */
    public static void print(ArrayList<Task> arr, Task t) {
        String keyword = "";
        String toPrint = "";
        if (t instanceof Deadline) {
            keyword = "by";
            toPrint = " (" + keyword + ": " + t.time + ")";
        } else if (t instanceof Event) {
            keyword = "at";
            toPrint = " (" + keyword + ": " + t.time + ")";
        }
        System.out.println("Got it. I've added this task:" + "\n" +
                t.getIndicator() + t.getIcon() + t.name + toPrint + "\n" +
                "Now you have " + arr.size() + " tasks in the list."
        );
    }

    public static void printForDone(ArrayList<Task> arr, Task t) {
        String keyword = "";
        String toPrint = "";
        if (t instanceof Deadline) {
            keyword = "by";
            toPrint = " (" + keyword + ": " + t.time + ")";
        } else if (t instanceof Event) {
            keyword = "at";
            toPrint = " (" + keyword + ": " + t.time + ")";
        }
        System.out.println("Got it. Duke has marked this task as done:" + "\n" +
                t.getIndicator() + t.getIcon() + t.name + toPrint + "\n" +
                "Now you have " + arr.size() + " tasks in the list."
        );
    }

    /**
     * Prints out a string everytime the user deletes a task
     * @param arr temporary list of tasks
     * @param t the task to print out
     */
    public static void printForDelete(ArrayList<Task> arr, Task t) {
        String keyword = "";
        String toPrint = "";
        if (t instanceof Deadline) {
            keyword = "by";
            toPrint = " (" + keyword + ": " + t.time + ")";
        } else if (t instanceof Event) {
            keyword = "at";
            toPrint = " (" + keyword + ": " + t.time + ")";
        }
        System.out.println("Got it. Duke has removed this task:" + "\n" +
                t.getIndicator() + t.getIcon() + t.name + toPrint + "\n" +
                "Now you have " + arr.size() + " tasks in the list."
        );
    }

    /**
     * Prints out the bye statement
     */
    public void respondToBye() {
        System.out.println("Bye. Please come back soon :(");
        this.stop();
    }

    /**
     * Prints out a string saying that the list is empty
     */
    public void respondToEmptyList() {
        System.out.println("You currently do not have any todos.");
    }

    /**
     * Prints out the list of tasks when the user inputs in "list"
     * @throws Exception
     */
    public void respondToList() throws Exception {
        System.out.println("Here are the tasks in the list: ");
        s.printList();
    }

    /**
     * Prints out a string whenever the user inputs in an invalid task number to mark as done
     */
    public void respondToDoneFail() {
        System.out.println("The specified todo does not exist!");
    }

    /**
     * Prints out a string whenever the user forgets to input in the task number
     */
    public void respondToDoneWrongSyntax() {
        System.out.println("Provide a number of the todo that you want to mark as done!");
    }

    /**
     * Prints out the statement saying that task has been marked as done and updates the list
     */
    public void respondToDone(int taskNumber) throws Exception {
        ArrayList<Task> arr = tl.getArr();
        Task t = arr.get(taskNumber - 1);
        t.taskIsDone();
        printForDone(arr, t);
        s.listWriter(arr);
    }

    /**
     * Prints out an error message when the user inputs in the wrong syntax for todo
     */
    public void respondToTodoWrongSyntax() {
        TodoException te = new TodoException();
        System.out.println(te.errorMessage);
    }

    /**
     * Prints out the statement saying that todo has been added and updates the list
     */
    public void respondToTodo(String name) throws Exception {
        Todo t = new Todo(name);
        tl.addTask(t);
        print(tl.getArr(), t);
        s.listWriter(tl.getArr());
    }

    /**
     * Prints out an error message when the user forgets to input the deadline details
     */
    public void respondToEventFail() {
        EventException ee = new EventException();
        System.out.println(ee.errorMessage);
    }

    /**
     * Prints out a statement saying that an event has been added to the list and updates the list
     * @param name name of the event
     * @param time deadline of the event
     * @throws Exception
     */
    public void respondToEvent(String name, String time) throws Exception {
        ArrayList<Task> arr = tl.getArr();
        Event e = new Event(name, time);
        tl.addTask(e);
        print(arr, e);
        s.listWriter(arr);
    }

    /**
     * Prints out an error message when the user inputs in the wrong syntax for delete
     */
    public void respondToDeleteWrongSyntax() {
        System.out.println("Provide the number of the todo that you want to delete!");
    }

    /**
     * Prints out a statement saying that the specified task number does not exists
     */
    public void respondToDeleteFail() {
        System.out.println("The specified todo does not exist!");
    }

    /**
     * Prints out a statement saying that the task has been deleted and updates the list
     * @param taskNumber task number of the task that is being deleted
     * @throws Exception
     */
    public void respondToDelete(int taskNumber) throws Exception {
        ArrayList<Task> arr = tl.getArr();
        Task t = arr.get(taskNumber - 1);
        tl.removeTask(t);
        printForDelete(arr, t);
        s.listWriter(arr);
    }

    /**
     * Prints out an error message when the user forgets to input the deadline details
     */
    public void respondToDeadlineFail() {
        DeadlineException de = new DeadlineException();
        System.out.println(de.errorMessage);
    }

    /**
     * Prints out a statement saying that a deadline has been added to the list and updates the list
     * @param name name of the event
     * @param time deadline of the event
     * @throws Exception
     */
    public void respondToDeadline(String name, String time) throws Exception {
        ArrayList<Task> arr = tl.getArr();
        Deadline d = new Deadline(name, time);
        tl.addTask(d);
        print(arr, d);
        s.listWriter(arr);
    }

    /**
     * Prints out a statement saying that the command does not exist
     */
    public void respondToCommandDoesNotExist() {
        System.out.println("Command does not exist!" + "\n");
    }
}
