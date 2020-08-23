import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    int taskSize;
    ArrayList<Task> taskList;

    public Duke() {
        this.taskSize = 0;
        this.taskList = new ArrayList<>();
    }

    public Duke(int taskSize, ArrayList<Task> taskList) {
        this.taskSize = taskSize;
        this.taskList = taskList;
    }

    public static void greeting() {
        String logo = " ____   ____\n"
                + "|  _ \\ |  _ \\\n"
                + "| | | || | | |\n"
                + "| |_| || |_| |\n"
                + "|____/ |____/\n";
        System.out.println("Hi! I'm\n" + logo + "How can I help you? :)\n"
                + "_________________________________________");
    }

    public static void exit() {
        System.out.println("You're leaving? Bye :( Come back soon!"
                + "\n_________________________________________");
    }

    public static void list(Duke dd) {
        System.out.println("Here is your current list of task(s)!");
        int curr = 0;
        while (curr < dd.taskSize) {
            System.out.println((curr + 1) + ". " + dd.taskList.get(curr));
            curr += 1;
        }
    }

    public static void addTodo(Duke dd, String input) {
        input = input.substring(5);
        dd.taskList.add(new Todo(input));
        System.out.println("Ok, To-do added:\n  " + dd.taskList.get(dd.taskSize));
        dd.taskSize += 1;
        System.out.println("You now have " + dd.taskSize + " task(s) in your list!");
    }

    public static void addDeadline(Duke dd, String input) {
        input = input.substring(9);
        String[] temp = input.split(" /by "); // create array of [task desc, task date]

        if (temp.length == 2) {
            // valid
            dd.taskList.add(new Deadline(temp[0], temp[1]));
            System.out.println("Ok, Deadline added:\n  " + dd.taskList.get(dd.taskSize));
            dd.taskSize += 1;
            System.out.println("You now have " + dd.taskSize + " task(s) in your list!");
        } else {
            // no valid date
            System.out.println("Due date not detected, try again!");
        }
    }

    public static void addEvent(Duke dd, String input) {
        input = input.substring(6);
        String[] temp = input.split(" /at "); // create array of [task desc, task date]

        if (temp.length == 2) {
            // valid
            dd.taskList.add(new Event(temp[0], temp[1]));
            System.out.println("Ok, Event added:\n  " + dd.taskList.get(dd.taskSize));
            dd.taskSize += 1;
            System.out.println("You now have " + dd.taskSize + " task(s) in your list!");
        } else {
            // no valid date
            System.out.println("Event date not detected, try again!");
        }
    }

    public static void done(Duke dd, String input) {
        String taskStr = input.substring(5);
        int taskNum = 0;

        try {
            taskNum = Integer.parseInt(taskStr);
        }
        catch (NumberFormatException ignored) {
        }

        if (taskNum > 0 && taskNum <= dd.taskSize) {
            dd.taskList.get(taskNum - 1).markAsDone();
            System.out.println("Wow!! Good job!!\n  " + dd.taskList.get(taskNum - 1));
        }
        else {
            System.out.println("hmm.. I don't think thats a valid task, try again?");
        }
    }

    public static void delete(Duke dd, String input) {
        String delStr = input.substring(7);
        int delNum = 0;

        try {
            delNum = Integer.parseInt(delStr);
        }
        catch (NumberFormatException ignored) {
        }

        if (delNum > 0 && delNum <= dd.taskSize) {
            System.out.println("Alright! I've deleted the task:\n  " + dd.taskList.get(delNum - 1));
            dd.taskList.remove(delNum-1);
            dd.taskSize -= 1;
            System.out.println("You now have " + dd.taskSize + " task(s) in your list!");
        }
        else {
            System.out.println("hmm.. I don't think thats a valid task, try again?");
        }
    }

    public static void main(String[] args) {
        greeting();

        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        String bye = "bye";
        String list = "list";

        Duke dd = new Duke();

        if (input.equals(bye)) {
            exit();
        }
        else {
            // does not exit
            while (!input.equals(bye)) {
                if (input.equals(list)) {
                    list(dd);
                }
                // input tasks
                else if (input.startsWith("todo")) {
                    addTodo(dd, input);
                }
                else if (input.startsWith("deadline")) {
                    addDeadline(dd, input);
                }
                else if (input.startsWith("event")) {
                    addEvent(dd, input);
                }
                else if (input.startsWith("done")) {
                    done(dd, input);
                }
                else if (input.startsWith("delete")) {
                    delete(dd, input);
                }
                else {
                    // not valid task
                    System.out.println("Sorry what?");
                }
                System.out.println("_________________________________________");
                input = sc.nextLine();
            }
            exit();
        }
    }
}
