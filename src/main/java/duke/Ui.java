package main.java.duke;

public class Ui {

    public Ui() {
    }

    public void show(String input) {
        String line = "____________________________________________________________";
        System.out.println(line);
        System.out.println(input);
        System.out.println(line);
    }

    public void showWelcome() {
        show("Duke at your service. How may I help?");
    }

    public void showLoadingError() {
        showError("Can't load as file is missing\n" +
                "If it is your first time opening, ignore this message.");
    }

    public void showTaskAdded(String taskDetail, int taskCount) {
        String first = "Got it. I've added this task:\n";
        String second = "    " + taskDetail + "\n";
        String third = String.format("Now you have %d tasks in the list", taskCount);
        show(first + second + third);
    }

    public void showTaskDone(String taskDetail) {
        show("Nice! I have marked this task as done:\n" +
                taskDetail);
    }

    public void showTasks(String taskDetails) {
        show("Here are the tasks in your list\n" + taskDetails);
    }

    public void showDeletedTasks(String taskDetail) {
        show("Alright! I have deleted this task:\n" + taskDetail);
    }

    public void showError(String err) {
        String line = "************************************************************";
        System.out.println(line);
        System.out.println(err);
        System.out.println(line);
    }

    public void showBye() {
        show("Bye. See you again");
    }

}
