import org.w3c.dom.ls.LSOutput;

import java.sql.SQLOutput;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Duke {

    static String newLine = System.lineSeparator();

    public static void main(String[] args) {

        Scanner myObj = new Scanner(System.in); // Creates a new scanner object
        ArrayList<Task> itemsLs = new ArrayList<>();

        //Intro
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        String toPrint = myObj.nextLine();
        Task toPrintTask = new Task(toPrint);

        while(!toPrint.equalsIgnoreCase("bye")) {
            if (toPrint.equalsIgnoreCase("list")) {
                int i = 1;
                System.out.println("Here are the tasks in your list: ");
                itemsLs.forEach(n -> System.out.println(itemsLs.indexOf(n) + 1 + ". " + n));
                toPrint = myObj.nextLine();

            } else if (toPrint.contains("done")) {
                String command = toPrint.replaceAll("[^\\d.]", "");
                int indexCommand = Integer.parseInt(command.trim());
                System.out.println("Nice! I've marked this task as done: ");
                Task completedTask = itemsLs.get(indexCommand - 1);
                completedTask.markAsDone();
                System.out.println("[" + completedTask.getStatusIcon() + "] " + completedTask.description);
                toPrint = myObj.nextLine();

            } else if (toPrint.contains("deadline")) {
                toPrint = toPrint.substring(8);
                String[] arrtoPrint = toPrint.split("/by");
                Deadline taskDeadline = new Deadline(arrtoPrint[0], arrtoPrint[1]);
                itemsLs.add(taskDeadline);

                System.out.println("Got it. I've added this task:");
                System.out.println(taskDeadline);
                System.out.println("Now you have " + itemsLs.size() + " tasks in the list.");
                toPrint = myObj.nextLine();

            } else if (toPrint.contains("todo")) {
                toPrint = toPrint.substring(4);
                Todo taskTodo = new Todo(toPrint);
                itemsLs.add(taskTodo);

                System.out.println("Got it. I've added this task:");
                System.out.println(taskTodo);
                System.out.println("Now you have " + itemsLs.size() + " tasks in the list.");
                toPrint = myObj.nextLine();

            } else if (toPrint.contains("event")) {
                toPrint = toPrint.substring(5);
                String[] arrtoPrint = toPrint.split("/at");
                Event taskEvent = new Event(arrtoPrint[0], arrtoPrint[1]);
                itemsLs.add(taskEvent);

                System.out.println("Got it. I've added this task:");
                System.out.println(taskEvent);
                System.out.println("Now you have " + itemsLs.size() + " tasks in the list.");
                toPrint = myObj.nextLine();

            } else {
                itemsLs.add(toPrintTask);
                System.out.println("added:" + toPrint);
                toPrint = myObj.nextLine();
                toPrintTask = new Task(toPrint);
            }
        }

        System.out.println("Bye. Hope to see you again soon!");

    }
}
