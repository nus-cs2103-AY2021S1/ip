import java.util.ArrayList;

public class Ui {
    String message;

    public Ui() {}

    public Ui(String message) {
        this.message = message;
    }

    public void printMessage() {
        System.out.println(message);
    }

    public void printLogo() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
    }

    public void printWelcome() {
        String welcomeMessage = "____________________________________________________\n" +
                "Hello, I'm Duke!\n\n";
        System.out.println(welcomeMessage);
    }

    public void printWhatCanIDo() {
        String whatCanIDoMessage = "What can I do for you?\n" +
                "____________________________________________________\n";
        System.out.println(whatCanIDoMessage);
    }

    public void printExistingTasks(ArrayList<Task> taskList) {
        String currentTasks = "";
        if (taskList.size() < 1) {
            currentTasks += "You currently have no tasks. ";
        } else {
            currentTasks +=  "Here are your existing tasks:\n";
            for (int i = 0; i < taskList.size(); i++) {
                Task task = taskList.get(i);
                String taskString = String.valueOf(i + 1) + "." + task.toString() + "\n";
                currentTasks += taskString;
            }
            currentTasks += "\n";
        }
        System.out.println(currentTasks);
    }

    public void printLoadingError() {
        String loadingError = "An error occured while loading the data...";
        System.out.println(loadingError);
    }

    public void printTasks(ArrayList<Task> taskList) {
        String tasks = "____________________________________________________\n";
        if (taskList.size() < 1) {
            tasks += "You currently have no tasks.\n";
        } else {
            tasks += "Here are the tasks in your list:\n";
            for (int i = 0; i < taskList.size(); i++) {
                Task task = taskList.get(i);
                String taskString = String.valueOf(i + 1) + "." + task.toString() + "\n";
                tasks += taskString;
            }
        }
        tasks += "____________________________________________________\n";
        System.out.println(tasks);
    }

    public void printGoodbye() {
        String goodbyeMessage = "____________________________________________________\n" +
                "Bye. Hope to see you again soon!\n" +
                "____________________________________________________\n";
        System.out.println(goodbyeMessage);
    }
}