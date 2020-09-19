import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    // this field is used when output requires a horizontal line
    public static final String LINE = "    ________________________________________________________";

    // this function greets the user when Duke is started
    public String printGreetingMessage(String createResult) {
        String logo = "      ____        _        \n"
                + "     |  _ \\ _   _| | _____ \n"
                + "     | | | | | | | |/ / _ \\\n"
                + "     | |_| | |_| |   <  __/\n"
                + "     |____/ \\__,_|_|\\_\\___|\n";
        String result = "     Hello from\n" + logo;
        result += "\n" + LINE;
        result += "\n" + "     Hello! I'm Duke and I was designed by Xuan Ming!\n";
        result += "\n" + createResult;
        result += "\n" + LINE;
        return result;
    }

    // this function says bye to the user when Duke receives the input "bye"
    public String printGoodbyeMessage() {
        return "     Bye. Hope to see you again soon!";
    }

    /*
     calling this function will cause Duke to echo what the user inputs to Duke
     this method is only used in Level-1 of iP
    */
    public String echo(String s) {
        return s;
    }

    // this method prompts the user for a command
    public String readCommand() {
        Scanner sc = new Scanner(System.in);
        System.out.println("     Give me a command! ");
        return sc.nextLine();
    }

    public String showLine() {
        return LINE;
    }

    public String printUnknownCommandMessage(String command) {
        return "     OOPS!!! I'm sorry, but I don't know what \n    \"" + command + "\" means :-(";
    }

    public String createToDoSuccessMessage(ToDo t, int numOfTasks) {
        return "     Got it. I've added this task: " + "\n" + "     " + t.toString() + "\n"
                + "     Now you have " + numOfTasks + " tasks in the list.";
    }

    public String printTasks(ArrayList<Task> tasks) {
        if (tasks.size() == 0) {
            return "     Currently, you have no tasks!";
        } else {
            assert tasks.size() > 0 : "Tasks should be greater than 0!";
            String result = "     These are your tasks:";
            int counter = 1;
            for (Task task : tasks) {
                if (task instanceof Deadline) {
                    result += "\n     " + counter + ". " + ((Deadline) task).toString();
                    counter++;
                } else if (task instanceof Event) {
                    result += "\n     " + counter + ". " + ((Event) task).toString();
                    counter++;
                } else if (task instanceof ToDo) {
                    result += "\n     " + counter + ". " + ((ToDo) task).toString();
                    counter++;
                }
            }
            return result;
        }
    }

    public String doneCommandSuccessMessage(int taskNumber, Task t) {
        String result = "     Nice! I've marked this task as done: ";
        if (t instanceof Event) {
            result += "\n       " + taskNumber + ". " + ((Event) t).toString();
        } else if (t instanceof Deadline) {
            result += "\n       " + taskNumber + ". " + ((Deadline) t).toString();
        } else if (t instanceof ToDo) {
            result += "\n       " + taskNumber + ". " + ((ToDo) t).toString();
        }
        return result;
    }

    public String createDeadlineSuccessMessage(Deadline d, int numOfTasks) {
        String result = "     Got it. I've added this task: ";
        result += "\n     " + d.toString();
        result += "\n     Now you have " + numOfTasks + " tasks in the list.";
        return result;
    }

    public String createEventSuccessMessage(Event e, int numOfTasks) {
        String result = "     Got it. I've added this task: ";
        result += "\n     " + e.toString();
        result += "\n     Now you have " + numOfTasks + " tasks in the list.";
        return result;
    }

    public String showErrorMessage(String message) {
        return "     " + message;
    }

    public String deleteCommandSuccessMessage(int taskNumber, Task t) {
        String result = "     Noted. I've removed this task: ";

        if (t instanceof Event) {
            result += "\n       " + taskNumber + ". " + ((Event) t).toString();
        } else if (t instanceof Deadline) {
            result += "\n       " + taskNumber + ". " + ((Deadline) t).toString();
        } else if (t instanceof ToDo) {
            result += "\n       " + taskNumber + ". " + ((ToDo) t).toString();
        }
        return result;
    }

    public String findResultMessage(ArrayList<Task> results, String query) {
        if (results.size() == 0) {
            return "     OOPS!!! I'm sorry, but I didn't find anything that matches \"" + query
                    + "\" :-(";
        } else {
            String result = "     Here's what I found: ";
            int counter = 1;
            for (Task t : results) {
                result += "\n     " + counter + "." + t.toString();
                counter++;
            }
            return result;
        }
    }
}
