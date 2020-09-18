import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    // this field is used when output requires a horizontal line
    public static final String LINE = "    ____________________________________________________________";

    // this function greets the user when Duke is started
    public void printGreetingMessage(String createResult) {
        String logo = "      ____        _        \n"
                + "     |  _ \\ _   _| | _____ \n"
                + "     | | | | | | | |/ / _ \\\n"
                + "     | |_| | |_| |   <  __/\n"
                + "     |____/ \\__,_|_|\\_\\___|\n";
        System.out.println("     Hello from\n" + logo);
        System.out.println(LINE);
        System.out.println("     Hello! I'm Duke and I was designed by Xuan Ming!\n");
        System.out.println(createResult);
        System.out.println(LINE);
    }

    // this function says bye to the user when Duke receives the input "bye"
    public void printGoodbyeMessage() {
        System.out.println("     Bye. Hope to see you again soon!");
    }

    /*
     calling this function will cause Duke to echo what the user inputs to Duke
     this method is only used in Level-1 of iP
    */
    public void echo(String s) {
        System.out.println(s);
    }

    // this method prompts the user for a command
    public String readCommand() {
        Scanner sc = new Scanner(System.in);
        System.out.println("     Give me a command! ");
        return sc.nextLine();
    }

    public void showLine() {
        System.out.println(LINE);
    }

    public void printUnknownCommandMessage(String command) {
        System.out.println("     ☹ OOPS!!! I'm sorry, but I don't know what \"" + command + "\" means :-(");
    }

    public void createToDoSuccessMessage(ToDo t, int numOfTasks) {
        System.out.println("     Got it. I've added this task: ");
        System.out.println("     " + t.toString());
        System.out.println("     Now you have " + numOfTasks + " tasks in the list.");
    }

    public void printTasks(ArrayList<Task> tasks) {
        if (tasks.size() == 0) {
            System.out.println("     Currently, you have no tasks!");
        } else {
            assert tasks.size() > 0 : "Tasks should be greater than 0!";
            System.out.println("     These are your tasks:");
            int counter = 1;
            for (Task task : tasks) {
                if (task instanceof Deadline) {
                    System.out.println("     " + counter + ". " + ((Deadline) task).toString());
                    counter++;
                } else if (task instanceof Event) {
                    System.out.println("     " + counter + ". " + ((Event) task).toString());
                    counter++;
                } else if (task instanceof ToDo) {
                    System.out.println("     " + counter + ". " + ((ToDo) task).toString());
                    counter++;
                }
            }
        }
    }

    public void doneCommandSuccessMessage(int taskNumber, Task t) {
        System.out.println("     Nice! I've marked this task as done: ");

        if (t instanceof Event) {
            System.out.println("       " + taskNumber + ". " + ((Event) t).toString());
        } else if (t instanceof Deadline) {
            System.out.println("       " + taskNumber + ". " + ((Deadline) t).toString());
        } else if (t instanceof ToDo) {
            System.out.println("       " + taskNumber + ". " + ((ToDo) t).toString());
        }
    }

    public void createDeadlineSuccessMessage(Deadline d, int numOfTasks) {
        System.out.println("     Got it. I've added this task: ");
        System.out.println("     " + d.toString());
        System.out.println("     Now you have " + numOfTasks + " tasks in the list.");
    }

    public void createEventSuccessMessage(Event e, int numOfTasks) {
        System.out.println("     Got it. I've added this task: ");
        System.out.println("     " + e.toString());
        System.out.println("     Now you have " + numOfTasks + " tasks in the list.");
    }

    public void showErrorMessage(String message) {
        System.out.println("     " + message);
    }

    public void deleteCommandSuccessMessage(int taskNumber, Task t) {
        System.out.println("     Noted. I've removed this task: ");

        if (t instanceof Event) {
            System.out.println("       " + taskNumber + ". " + ((Event) t).toString());
        } else if (t instanceof Deadline) {
            System.out.println("       " + taskNumber + ". " + ((Deadline) t).toString());
        } else if (t instanceof ToDo) {
            System.out.println("       " + taskNumber + ". " + ((ToDo) t).toString());
        }
    }

    public void findResultMessage(ArrayList<Task> results, String query) {
        if (results.size() == 0) {
            System.out.println("     ☹ OOPS!!! I'm sorry, but I didn't find anything that matches \"" + query
                    + "\" :-(");
        } else {
            System.out.println("     Here's what I found: ");
            int counter = 1;
            for (Task t : results) {
                System.out.println(counter + "." + t.toString());
                counter++;
            }
        }
    }
}
