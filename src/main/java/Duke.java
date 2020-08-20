import src.main.java.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class Duke {
    int index;
    List<Task> ls;

    public Duke() {
        this.index = 0;
        this.ls = new ArrayList<>();
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
        System.out.println("Okie ! I've added this task for you : ");
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

    public void deleteTask(int index) {
        Task task = this.ls.get(index);
        String item = task.getItem();
        String status = task.getStatus();
        String taskType = task.getSign();
        int num = index + 1;

        // remove task from array
        this.ls.remove(index);
        this.index--;

        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
        System.out.println("I've removed this task for you : ");
        if (task instanceof Todo) { // if task is to-do
            System.out.println(num + ". " + taskType + " " + status + " " + item + "\n");
        } else if (task instanceof Deadline) { // if task is deadline
            Deadline actualTask = (Deadline) task;
            System.out.println(taskType + " " +  status + " " + item + " -> by :" + actualTask.getDeadline() + "\n");
        } else { // if task is event
            Event actualTask = (Event) task;
            System.out.println(taskType + " " + status + " " + item + " -> at :" + actualTask.getTime() + "\n");
        }
        System.out.println("You now have " + this.index + " task(s) in your list !\n");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
    }

    public static void main(String[] args) throws DukeException {

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

            try {
                if (input.equals("bye")) {
                    klaun.sayBye();
                    break;
                } else if (input.equals("list")) { // if user calls for list
                    klaun.getList();
                } else if (input.split(" ")[0].equals("done")) { // if its "done x"
                    try {
                        if (input.contains(" ") && parseInt(input.split(" ", 2)[1]) <= klaun.getIndex() && parseInt(input.split(" ", 2)[1]) > 0) {
                            klaun.markDone(parseInt(input.split(" ", 2)[1]) - 1);
                        } else {
                            throw new DukeException("invalid input: " + input);
                        }
                    } catch (Exception e) {
                        System.out.println("??????????????????????????????????????????????????????????????\n");
                        System.out.println("Oops ... you should provide a valid task number to complete ~ \n");
                        System.out.println("??????????????????????????????????????????????????????????????\n");
                    }
                } else if (input.split(" ")[0].equals("todo")) { // if task type is to-do
                    try {
                        if (input.contains(" ")) {
                            String[] arr = input.split(" ", 2); // split input to get task item

                            // add item to list
                            klaun.addToList(new Todo(arr[1], "T")); // add to-do task to ls
                        } else {
                            throw new DukeException("invalid input: " + input);
                        }
                    } catch (Exception e) {
                        System.out.println("??????????????????????????????????????????????????????????????\n");
                        System.out.println("Oh no !! I think you forgot to add your todo description :O\n");
                        System.out.println("??????????????????????????????????????????????????????????????\n");
                    }
                } else if (input.split(" ")[0].equals("deadline")) { // if task type is deadline
                    try {
                        if (input.contains(" ") && input.contains("/by")) {
                            String[] arr = input.split("/by", 2); // split to get deadline of task
                            String item = arr[0].split(" ", 2)[1]; // get item and remove "deadline" from string

                            // add item to list
                            klaun.addToList(new Deadline(item, "D", arr[1]));
                        } else { // if format of deadline task is wrong
                            throw new DukeException("invalid input: " + input);
                        }
                    } catch (Exception e) {
                        System.out.println("??????????????????????????????????????????????????????????????\n");
                        System.out.println("Oh no !! Your format should be \"deadline ____ /by ____\" \n");
                        System.out.println("??????????????????????????????????????????????????????????????\n");
                    }
                } else if (input.split(" ")[0].equals("event")) { // if task type is deadline
                    try {
                        if (input.contains(" ") && input.contains("/at")) {
                            String[] arr = input.split("/at", 2); // split to get time of task
                            String item = arr[0].split(" ", 2)[1]; // get item and remove "deadline" from string

                            // add item to list
                            klaun.addToList(new Event(item, "E", arr[1]));
                        } else { // if format of event task is wrong
                            throw new DukeException("invalid input: " + input);
                        }
                    } catch (Exception e) {
                        System.out.println("??????????????????????????????????????????????????????????????\n");
                        System.out.println("Oh no !! Your format should be \"event ____ /at ____\" \n");
                        System.out.println("??????????????????????????????????????????????????????????????\n");
                    }
                } else if (input.split(" ")[0].equals("delete")) {
                    try {
                        if (input.contains(" ") && parseInt(input.split(" ", 2)[1]) <= klaun.getIndex() && parseInt(input.split(" ", 2)[1]) > 0) {
                            klaun.deleteTask(parseInt(input.split(" ", 2)[1]) - 1);
                        } else {
                            throw new DukeException("invalid input: " + input);
                        }
                    } catch (Exception e) {
                        System.out.println("??????????????????????????????????????????????????????????????\n");
                        System.out.println("Oops ... you should provide a valid task number to delete ~ \n");
                        System.out.println("??????????????????????????????????????????????????????????????\n");
                    }
                } else { // if input is not a task
                    throw new DukeException(input);
                }
            } catch (Exception e) {
                System.out.println("??????????????????????????????????????????????????????????????\n");
                System.out.println("invalid input :(\n");
                System.out.println("??????????????????????????????????????????????????????????????\n");
            }
        }
    }
}
