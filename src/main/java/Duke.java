import storage.DataStorage;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;

import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private int taskSize;
    private ArrayList<Task> taskList;
    private DataStorage ds;
    private DateTimeHandler dth;

    public Duke() {
        this.taskSize = 0;
        this.taskList = new ArrayList<>();
    }

    public Duke(int taskSize, ArrayList<Task> taskList) {
        this.taskSize = taskSize;
        this.taskList = taskList;
        this.ds = new DataStorage();
        this.dth = new DateTimeHandler();
    }

    public void greeting() {
        String logo = " ____   ____\n"
                + "|  _ \\ |  _ \\\n"
                + "| | | || | | |\n"
                + "| |_| || |_| |\n"
                + "|____/ |____/\n";
        System.out.println("Hi! I'm\n" + logo + "How can I help you? :)\n"
                + "_________________________________________");
    }

    public void exit() {
        System.out.println("You're leaving? Bye :( Come back soon!"
                + "\n_________________________________________");
        ds.writeData(taskList);
    }

    public void list(ArrayList<Task> taskList) {
        int curr = 0;
        while (curr < taskList.size()) {
            System.out.println((curr + 1) + ". " + taskList.get(curr));
            curr += 1;
        }
    }

    public void addTodo(String input) {
        input = input.substring(5);
        taskList.add(new Todo(input));
        System.out.println("Ok, To-do added:\n  " + taskList.get(taskSize));
        taskSize += 1;
        System.out.println("You now have " + taskSize + " task(s) in your list!");
    }

    public void addDeadline(String input) {
        input = input.substring(9);
        String[] temp = input.split(" /by "); // create array of [task desc, task date]

        if (temp.length == 2) {
            boolean validInput = dth.checkInput(temp[1]);

            if (validInput) {
                // valid
                String formattedDate = dth.categorizeInput(temp[1]);
                taskList.add(new Deadline(temp[0], formattedDate));
                System.out.println("Ok, tasks.Deadline added:\n  " + taskList.get(taskSize));
                taskSize += 1;
                System.out.println("You now have " + taskSize + " task(s) in your list!");
            }
            else {
                // not valid date
                System.out.println("I don't understand :( Please input date as DD-MM-YYYY or DD-MM-YYYY HHmm\n"
                        + "Example: 31-12-2020 or 31-12-2020 2359");
            }
        }
        else {
            // no date input
            System.out.println("Due date not detected, try again!\n"
                    + "Please input deadline as 'deadline (title) /by (date)'\n"
                    + "Example: deadline return book /by 31-12-2020");
        }
    }

    public void addEvent(String input) {
        input = input.substring(6);
        String[] temp = input.split(" /at "); // create array of [task desc, task date]

        if (temp.length == 2) {
            boolean validInput = dth.checkInput(temp[1]);

            if (validInput) {
                // valid
                String formattedDate = dth.categorizeInput(temp[1]);
                taskList.add(new Event(temp[0], formattedDate));
                System.out.println("Ok, tasks.Event added:\n  " + taskList.get(taskSize));
                taskSize += 1;
                System.out.println("You now have " + taskSize + " task(s) in your list!");
            }
            else {
                // not valid date
                System.out.println("I don't understand :( Please input date as DD-MM-YYYY or DD-MM-YYYY HHmm\n"
                        + "Example: 31-12-2020 or 31-12-2020 2359");
            }
        }
        else {
            // no date input
            System.out.println("tasks.Event date not detected, try again!\n"
                    + "Please input event as 'event (title) /at (date)'\n"
                    + "Example: event group meeting /at 31-12-2020");
        }
    }

    public void done(String input) {
        String taskStr = input.substring(5);
        int taskNum = 0;

        try {
            taskNum = Integer.parseInt(taskStr);
        }
        catch (NumberFormatException ignored) {
        }

        if (taskNum > 0 && taskNum <= taskSize) {
            taskList.get(taskNum - 1).markAsDone();
            System.out.println("Wow!! Good job!!\n  " + taskList.get(taskNum - 1));
        }
        else {
            System.out.println("hmm.. I don't think thats a valid task, try again?");
        }
    }

    public void delete(String input) {
        String delStr = input.substring(7);
        int delNum = 0;

        try {
            delNum = Integer.parseInt(delStr);
        }
        catch (NumberFormatException ignored) {
        }

        if (delNum > 0 && delNum <= taskSize) {
            System.out.println("Alright! I've deleted the task:\n  " + taskList.get(delNum - 1));
            taskList.remove(delNum-1);
            taskSize -= 1;
            System.out.println("You now have " + taskSize + " task(s) in your list!");
        }
        else {
            System.out.println("hmm.. I don't think thats a valid task, try again?");
        }
    }

    public void checkDate(String input) {
        String date = input.substring(6);
        boolean validInput = dth.checkInput(date);

        if (validInput && date.length() == 10) {
            // valid
            ArrayList<Task> tasksOnDate = dth.filterDate(date, taskList);

            if (tasksOnDate.isEmpty()) {
                System.out.println("No tasks found on " + date + "!");
            }
            else {
                System.out.println("Here is your list of task(s) on " + date + ":");
                list(tasksOnDate);
            }
        }
        else {
            // not valid date
            System.out.println("I don't understand :( Please input date as DD-MM-YYYY\n"
                    + "Example: 31-12-2020");
        }
    }

    public void run() {

        greeting();

        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        String bye = "bye";
        String list = "list";

        if (input.equals(bye)) {
            exit();
        }
        else {
            // does not exit
            while (!input.equals(bye)) {
                if (input.equals(list)) {
                    System.out.println("Here is your current list of task(s)!");
                    list(taskList);
                }
                // input tasks
                else if (input.startsWith("todo")) {
                    addTodo(input);
                }
                else if (input.startsWith("deadline")) {
                    addDeadline(input);
                }
                else if (input.startsWith("event")) {
                    addEvent(input);
                }
                else if (input.startsWith("done")) {
                    done(input);
                }
                else if (input.startsWith("delete")) {
                    delete(input);
                }
                else if (input.startsWith("check")) {
                    checkDate(input);
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
    public static void main(String[] args) {
        DataStorage ds = new DataStorage();
        ArrayList<Task> tasks = ds.takeData();

        new Duke(tasks.size(), tasks).run();
    }
}
