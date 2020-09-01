import java.util.ArrayList;

public class Ui {
    String message;
    String divider;

    public Ui() {
        divider = "____________________________________________________\n";
    }

    public Ui(String message) {
        this.message = message;
        divider = "____________________________________________________\n";
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void printMessage() {
        System.out.println(message);
    }

    public void printMessageWithBorders() {
        System.out.println(divider);
        System.out.println(message);
        System.out.println(divider);
    }

    public void printLogo() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
    }

    public void printWelcome(ArrayList<Task> taskList) {
        message = "Hello, I'm Duke!\n\n";
        if (taskList.size() < 1) {
            message += "You currently have no tasks. ";
        } else {
            message +=  "Here are your existing tasks:\n";
            for (int i = 0; i < taskList.size(); i++) {
                Task task = taskList.get(i);
                message += i + 1 + "." + task.toString() + "\n";
            }
            message += "\n";
        }
        message += "What can I do for you?\n";
        setMessage(message);
        printMessageWithBorders();
    }

    public void printLoadingError() {
        message = "An error occurred while loading the data...";
        printMessage();
    }

    public void printTasks(ArrayList<Task> taskList) {
        if (taskList.size() < 1) {
            message = "You currently have no tasks.\n";
        } else {
            message = "Here are the tasks in your list:\n";
            for (int i = 0; i < taskList.size(); i++) {
                Task task = taskList.get(i);
                String taskString = i + 1 + "." + task.toString() + "\n";
                message += taskString;
            }
        }
        printMessageWithBorders();
    }

    public void printGoodbye() {
        message = "Bye. Hope to see you again soon!\n";
        printMessageWithBorders();
    }
}
