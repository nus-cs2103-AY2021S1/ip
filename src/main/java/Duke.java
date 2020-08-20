import org.w3c.dom.ls.LSOutput;

import java.sql.Array;
import java.sql.SQLOutput;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Duke {

    static String newLine = System.lineSeparator();
    static String tabOutput = "\t";

    public static void confused() {
        String str = "OOPS!!! I'm sorry, but I don't know what that means :-(";
        System.out.println(str);
    }

    public static void delete(String toPrint, ArrayList<Task> ls) {
        String command = toPrint.replaceAll("[^\\d.]", "");
        int i = Integer.parseInt(command.trim());
        Task deletedTask = ls.get(i-1);
        ls.remove(i-1);
        int numTask = ls.size();
        System.out.println("Noted. I've removed this task: ");
        System.out.println(deletedTask);
        System.out.println("Now you have " + numTask + " tasks in the list.");
    }

    public static void todo(String toPrint, ArrayList<Task> itemsLs) {
        try {
            toPrint = toPrint.substring(4);
            if (toPrint.isEmpty()) {
                throw new DukeException("");
            }
            Todo taskTodo = new Todo(toPrint);
            itemsLs.add(taskTodo);

            System.out.println("Got it. I've added this task:");
            System.out.println(taskTodo);
            System.out.println("Now you have " + itemsLs.size() + " tasks in the list.");

        } catch (DukeException e) {
            Todo.invalidInput();
        }
    }

    public static void event(String toPrint, ArrayList<Task> itemsLs) {
        try {
            toPrint = toPrint.substring(5);
            String[] arrtoPrint = toPrint.split("/at");
            Event taskEvent = new Event(arrtoPrint[0], arrtoPrint[1]);
            itemsLs.add(taskEvent);

            System.out.println("Got it. I've added this task:");
            System.out.println(taskEvent);
            System.out.println("Now you have " + itemsLs.size() + " tasks in the list.");

        } catch (ArrayIndexOutOfBoundsException e) {
            Event.invalidInput();

        }
    }

    public static void deadline(String toPrint, ArrayList<Task> itemsLs) {
        try {
            toPrint = toPrint.substring(8);
            String[] arrtoPrint = toPrint.split("/by");
            Deadline taskDeadline = new Deadline(arrtoPrint[0], arrtoPrint[1]);
            itemsLs.add(taskDeadline);

            System.out.println("Got it. I've added this task:");
            System.out.println(taskDeadline);
            System.out.println("Now you have " + itemsLs.size() + " tasks in the list.");

        } catch (ArrayIndexOutOfBoundsException e) {
            Deadline.invalidInput();

        }
    }

    public static void list(String toPrint, ArrayList<Task> itemsLs) {
        int i = 1;
        System.out.println("Here are the tasks in your list: ");
        itemsLs.forEach(n -> System.out.println(itemsLs.indexOf(n) + 1 + ". " + n));
    }

    public static void done(String toPrint, ArrayList<Task> itemsLs) {
        String command = toPrint.replaceAll("[^\\d.]", "");
        int indexCommand = Integer.parseInt(command.trim());
        System.out.println("Nice! I've marked this task as done: ");
        Task completedTask = itemsLs.get(indexCommand - 1);
        completedTask.markAsDone();
        System.out.println("[" + completedTask.getStatusIcon() + "] " + completedTask.description);

    }

    public static void intro() {
        // Creates a new scanner object
        Scanner myObj = new Scanner(System.in);
        ArrayList<Task> itemsLs = new ArrayList<>();

        //Intro
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        String toPrint = myObj.nextLine();
        Task toPrintTask = new Task(toPrint);
        while(!toPrint.startsWith("bye")) {
            if (toPrint.startsWith("list")) {
                list(toPrint, itemsLs);
            }
            else if (toPrint.startsWith("done")) {
                done(toPrint, itemsLs);
            }
            else if (toPrint.startsWith("delete")) {
                delete(toPrint, itemsLs);
            }
            else if (toPrint.startsWith("todo")) {
                todo(toPrint, itemsLs);
            }
            else if (toPrint.startsWith("event")) {
                event(toPrint, itemsLs);
            }
            else if (toPrint.startsWith("deadline")) {
                deadline(toPrint, itemsLs);
            }
            else {
                confused();
            }
            toPrint = myObj.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void main(String[] args) throws DukeException {
        intro();
    }
}
