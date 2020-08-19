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
    int introVersion;
    int index;
    List<Task> ls;

    public Duke(int num) {
        this.introVersion = num;
        this.index = 0;
        this.ls = new ArrayList<Task>();
    }

    // prints introduction of klaun
    public void introDuke() {
        switch(this.introVersion) {
            case 0 :
                System.out.println("I'm Klaun (=^.^=) How are you doing today ?\n");
                System.out.println("Is there anything I can help you with ?\n");
                System.out.println("<------------------------------------------------------------>\n");
                break;
            case 1 :
                System.out.println("I'm Klaun (>_<) Hope you are feeling great today <3\n");
                System.out.println("Is there anything you need ?\n");
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
                break;
            case 2 :
                System.out.println("I'm Klaun (*_*) I hope you are having a wonderful day today :)\n");
                System.out.println("What can I do to make it better?\n");
                System.out.println("><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><\n");
                break;
        }

    }

    public int getIndex() {
        return this.index;
    }

    // prints output based on command
    public void sayBye(String command) {
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
        System.out.println(num + " " + taskType + " " + status + " " + item + "\n");
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
            System.out.println(taskType + " " +  status + " " + item + " -> by :" + actualTask.getDeadline());
        } else { // if task is event
            Event actualTask = (Event) task;
            System.out.println(taskType + " " + status + " " + item + " -> at :" + actualTask.getTime());
        }

        System.out.println("You have a total of " + this.index + " task(s) in your list !\n");
        System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<\n");
    }

    public static void main(String[] args) {

        String logo = "⊂_ヽ\n" +
                "　 ＼＼ ＿\n" +
                "　　 ＼(　•_•) F\n" +
                "　　　 <　⌒ヽ A\n" +
                "　　　/ 　 へ＼ B\n" +
                "　　 /　　/　＼＼ U\n" +
                "　　 ﾚ　ノ　　 ヽ_つ L\n" +
                "　　/　/ O\n" +
                "　 /　/| U\n" +
                "　(　(ヽ S\n" +
                "　|　|、＼\n" +
                "　| 丿 ＼ ⌒)\n" +
                "　| |　　) /\n" +
                "`ノ )　 Lﾉ";
        System.out.println("HIIIIII\n" + logo + "\n");

        Scanner sc = new Scanner(System.in);

        // generate random number for duke's introduction version
        Random rand = new Random();
        int num = rand.nextInt(3);

        // instantiate duke object
        Duke klaun = new Duke(num);

        // introduce duke
        klaun.introDuke();

        // get input
        String command = sc.nextLine();

        while (!command.equals("bye")) {
            if (command.equals("list")) { // if user calls for list
                klaun.getList();
            } else if (command.contains(" ") && command.split(" ")[0].equals("done") && parseInt(command.split(" ", 2)[1]) <= klaun.getIndex() && parseInt(command.split(" ", 2)[1]) > 0) { // if its "done x"
                klaun.markDone(parseInt(command.split(" ", 2)[1]) - 1);
            } else if (command.contains(" ") && command.split(" ")[0].equals("todo")) { // if task type is to-do
                String[] arr = command.split(" ", 2);

                // add item to list
                klaun.addToList(new Todo(arr[1], "T"));
            } else if (command.contains(" ") && command.split(" ")[0].equals("deadline") && command.contains("/")) { // if task type is deadline
                String[] arr = command.split("/by", 2);
                String item = arr[0].split(" ", 2)[1]; // get item and remove "deadline" from string

                // add item to list
                klaun.addToList(new Deadline(item, "D", arr[1]));
            } else if (command.contains(" ") &&command.split(" ")[0].equals("event") && command.contains("/")) { // if task type is deadline
                String[] arr = command.split("/at", 2);
                String item = arr[0].split(" ", 2)[1]; // get item and remove "deadline" from string

                // add item to list
                klaun.addToList(new Event(item, "E", arr[1]));
            } else {
                System.out.println("??????????????????????????????????????????????????????????????\n");
                System.out.println("invalid input :(\n");
                System.out.println("??????????????????????????????????????????????????????????????\n");
            }

            command = sc.nextLine();
        }

        klaun.sayBye(command);
    }
}
