package duke;
import java.io.File;
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

    public void stop() {
        exited = true;
    }

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

    public static void printForFind(ArrayList<Task> arr) {
        if (arr.size() == 0) {
            System.out.println("There is no task that matches your searchword");
        } else {
            System.out.println("Here are the matching tasks in your list:");
            int counter = 1;
            for (Task t: arr) {
                String keyword = "";
                String toPrint = "";
                if (t instanceof Deadline) {
                    keyword = "by";
                    toPrint = " (" + keyword + ": " + t.time + ")";
                } else if (t instanceof Event) {
                    keyword = "at";
                    toPrint = " (" + keyword + ": " + t.time + ")";
                }
                System.out.println(counter + ". " + t.getIndicator() + t.getIcon() + t.name + toPrint);
                counter++;
            }
        }
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

    public void respondToBye() {
        System.out.println("Bye. Please come back soon :(");
        this.stop();
    }

    public void respondToEmptyList() {
        System.out.println("You currently do not have any todos.");
    }

    public void respondToList() throws Exception {
        System.out.println("Here are the tasks in the list: ");
        s.printList();
    }

    public void respondToFindFail() {
        System.out.println("The specified todo does not exist!");
    }

    public void respondToFindWrongSyntax() {
        System.out.println("Provide a number of the todo that you want to mark as done!");
    }

    public void respondToFind(String searchWord) throws Exception {
        ArrayList<Task> temp = new ArrayList<>();
        for (Task t: tl.arr) {
            if (t.name.contains(searchWord)) {
                temp.add(t);
            }
        }
        printForFind(temp);
    }

    public void respondToDoneFail() {
        System.out.println("The specified todo does not exist!");
    }

    public void respondToDoneWrongSyntax() {
        System.out.println("Provide a number of the todo that you want to mark as done!");
    }

    public void respondToDone(int taskNumber) throws Exception {
        Task t = tl.arr.get(taskNumber - 1);
        t.taskIsDone();
        printForDone(tl.arr, t);
        s.listWriter(tl.arr);
    }

    public void respondToTodoWrongSyntax() {
        TodoException te = new TodoException();
        System.out.println(te.errorMessage);
    }

    public void respondToTodo(String name) throws Exception {
        Todo t = new Todo(name);
        tl.addTask(t);
        print(tl.arr, t);
        s.listWriter(tl.arr);
    }

    public void respondToEventFail() {
        EventException ee = new EventException();
        System.out.println(ee.errorMessage);
    }

    public void respondToEvent(String name, String time) throws Exception {
        Event e = new Event(name, time);
        tl.addTask(e);
        print(tl.arr, e);
        s.listWriter(tl.arr);
    }

    public void respondToDeleteWrongSyntax() {
        System.out.println("Provide the number of the todo that you want to delete!");
    }

    public void respondToDeleteFail() {
        System.out.println("The specified todo does not exist!");
    }

    public void respondToDelete(int taskNumber) throws Exception {
        Task t = tl.arr.get(taskNumber - 1);
        tl.removeTask(t);
        printForDelete(tl.arr, t);
        s.listWriter(tl.arr);
    }

    public void respondToDeadlineFail() {
        DeadlineException de = new DeadlineException();
        System.out.println(de.errorMessage);
    }

    public void respondToDeadline(String name, String time) throws Exception {
        Deadline d = new Deadline(name, time);
        tl.addTask(d);
        print(tl.arr, d);
        s.listWriter(tl.arr);
    }

    public void respondToCommandDoesNotExist() {
        System.out.println("Command does not exist!" + "\n");
    }
}
