import src.main.java.Deadline;
import src.main.java.Event;
import src.main.java.Task;
import src.main.java.Todo;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Random;

import static java.lang.Integer.parseInt;

public class Duke {
    int index;
    List<Task> ls;

    public Duke() {
        this.index = 0;
        this.ls = new ArrayList<Task>();
    }

    // prints introduction of klaun
    public void introDuke() {
        System.out.println("I'm Klaun (=^.^=) How are you doing today ?\n");
        System.out.println("Is there anything I can help you with ?\n");
        System.out.println("<------------------------------------------------------------>\n");
    }

    public int getIndex() {
        return this.index;
    }

    // prints output based on command
    public void sayBye() {
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n");
        System.out.println("oh man ... bye ~~ o.o \n");
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n");
    }

    // prints list of tasks
    public void getList() {
        System.out.println("##############################################################\n");
        System.out.println("Here's your amazing task list :");
        for (int i = 0; i < this.index; i++) {
            Task task = this.ls.get(i);
            String item = task.getItem();
            String status = task.getStatus();
            String taskType = task.getSign();
            int num = i + 1;

            if (task instanceof Todo) { // if task is to-do
                System.out.println(num + ". " + taskType + " " + status + " " + item);
            } else if (task instanceof Deadline) { // if task is deadline
                Deadline actualTask = (Deadline) task;
                System.out.println(num + ". " + taskType + " " +  status + " " + item + " -> by :" + actualTask.getDeadline());
            } else { // if task type if event
                Event actualTask = (Event) task;
                System.out.println(num + ". " + taskType + " " + status + " " + item + " -> at :" + actualTask.getTime());
            }

        }

        System.out.println(" ");
        System.out.println("##############################################################\n");
    }

    // adds task to list
    public void addToList(Task task) {
        this.ls.add(task);
        this.index++; // increment index

        // print confirmation of adding task
        this.confirmAddTask(task);
    }

    // mark task as done
    public void markDone(int index) {
        this.ls.get(index).markAsDone();

        Task task = this.ls.get(index);
        String item = task.getItem();
        String status = task.getStatus();
        String taskType = task.getSign();
        int num = index + 1;

        System.out.println("==============================================================\n");
        System.out.println("Yayyyy !! Letsgedditt");
        if (task instanceof Todo) { // if task is to-do
            System.out.println(num + ". " + taskType + " " + status + " " + item + "\n");
        } else if (task instanceof Deadline) { // if task is deadline
            Deadline actualTask = (Deadline) task;
            System.out.println(num + ". " + taskType + " " +  status + " " + item + " -> by :" + actualTask.getDeadline() + "\n");
        } else { // if task is event
            Event actualTask = (Event) task;
            System.out.println(num + ". " + taskType + " " + status + " " + item + " -> at :" + actualTask.getTime() + "\n");
        }
        System.out.println("==============================================================\n");
    }

    // print task that has just been added
    public void confirmAddTask(Task task) {
        String item = task.getItem();
        String status = task.getStatus();
        String taskType = task.getSign();

        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>\n");
        System.out.println("added: ");
        if (task instanceof Todo) { // if task is to-do
            System.out.println(taskType + " " + status + " " + item + "\n");
        } else if (task instanceof Deadline) { // if task is deadline
            Deadline actualTask = (Deadline) task;
            System.out.println(taskType + " " +  status + " " + item + " -> by :" + actualTask.getDeadline() + "\n");
        } else { // if task is event
            Event actualTask = (Event) task;
            System.out.println(taskType + " " + status + " " + item + " -> at :" + actualTask.getTime() + "\n");
        }

        System.out.println("You have a total of " + this.index + " task(s) in your list !\n");
        System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<\n");
    }

    public static void main(String[] args) {

//        String logo = "(>'-')> <('-'<) ^('-')^ v('-')v(>'-')> (^-^)";
        String logo = "     .\"\".    .\"\",\n" +
                "     |  |   /  /\n" +
                "     |  |  /  /\n" +
                "     |  | /  /\n" +
                "     |  |/  ;-._\n" +
                "     }  ` _/  / ;\n" +
                "     |  /` ) /  /\n" +
                "     | /  /_/\\_/\\\n" +
                "     |/  /      |\n" +
                "     (  ' \\ '-  |\n" +
                "      \\    `.  /\n" +
                "       |      |\n" +
                "       |      |";

        System.out.println("HIIIIII\n" + logo + "\n");

        Scanner sc = new Scanner(System.in);

        // instantiate duke object
        Duke klaun = new Duke();

        // introduce duke
        klaun.introDuke();

        // get input
        String input = "";

        // checks for next line of input
        while (sc.hasNextLine()) {
            input = sc.nextLine();

            if (input.equals("bye")) {
                klaun.sayBye();
                break;
            } else if (input.equals("list")) { // if user calls for list
                klaun.getList();
            } else if (input.contains(" ") && input.split(" ")[0].equals("done") && parseInt(input.split(" ", 2)[1]) <= klaun.getIndex() && parseInt(input.split(" ", 2)[1]) > 0) { // if its "done x"
                klaun.markDone(parseInt(input.split(" ", 2)[1]) - 1);
            } else if (input.contains(" ") && input.split(" ")[0].equals("todo")) { // if task type is to-do
                String[] arr = input.split(" ", 2);

                // add item to list
                klaun.addToList(new Todo(arr[1], "T"));
            } else if (input.contains(" ") && input.split(" ")[0].equals("deadline") && input.contains("/")) { // if task type is deadline
                String[] arr = input.split("/by", 2);
                String item = arr[0].split(" ", 2)[1]; // get item and remove "deadline" from string

                // add item to list
                klaun.addToList(new Deadline(item, "D", arr[1]));
            } else if (input.contains(" ") && input.split(" ")[0].equals("event") && input.contains("/")) { // if task type is deadline
                String[] arr = input.split("/at", 2);
                String item = arr[0].split(" ", 2)[1]; // get item and remove "deadline" from string

                // add item to list
                klaun.addToList(new Event(item, "E", arr[1]));
            } else {
                System.out.println("??????????????????????????????????????????????????????????????\n");
                System.out.println("invalid input :(\n");
                System.out.println("??????????????????????????????????????????????????????????????\n");
            }
        }

    }
}
