import org.w3c.dom.ls.LSOutput;

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
                itemsLs.forEach(n -> System.out.println(itemsLs.indexOf(n) + 1 + ". " + "[" + n.isDone + "]" + n.description));
                toPrint = myObj.nextLine();
                toPrintTask = new Task(toPrint);
            } else if (toPrint.contains("done")) {
                String command = toPrint.replaceAll("[^\\d.]", "");
                int indexCommand = Integer.parseInt(command.trim());
                System.out.println("Nice! I've marked this task as done: ");
                Task completedTask = itemsLs.get(indexCommand - 1);
                completedTask.markAsDone();
                System.out.println("[" + completedTask.getStatusIcon() + "] " + completedTask.description);
                toPrint = myObj.nextLine();
                toPrintTask = new Task(toPrint);
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
