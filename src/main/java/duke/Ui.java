package main.java.duke;

public class Ui {

    public Ui() {
    }

    /**
     * Print out the input according to default's style.
     * @param input The message to display to user.
     */
    public void show(String input) {
        String line = "____________________________________________________________";
        System.out.println(line);
        System.out.println(input);
        System.out.println(line);
    }

    /**
     * Print out Welcome Message according to default's style.
     */
    public void showWelcome() {
        show("Duke at your service. How may I help?");
    }

    /**
     * Print out the detail of the new Task and new count of tasks at hand according to default's style.
     */
    public void showTaskAdded(String taskDetail, int taskCount) {
        String first = "Got it. I've added this task:\n";
        String second = "    " + taskDetail + "\n";
        String third = String.format("Now you have %d tasks in the list", taskCount);
        show(first + second + third);
    }

    /**
     * Print out the detail of the task that is just set to done according to default's style.
     */
    public void showTaskDone(String taskDetail) {
        show("Nice! I have marked this task as done:\n" +
                taskDetail);
    }

    /**
     * Print out the tasks that the user has on hand.
     * @param taskDetails String of Task Descriptions that corresponds to the tasks user have on hand.
     */
    public void showTasks(String taskDetails) {
        show("Here are the tasks in your list\n" + taskDetails);
    }

    /**
     * Print out the tasks that the user has just deleted.
     * @param taskDetail String of Task that is just deleted.
     */
    public void showDeletedTasks(String taskDetail) {
        show("Alright! I have deleted this task:\n" + taskDetail);
    }

    /**
     * Print out error message with different style.
     * @param err Error Message to print out.
     */
    public void showError(String err) {
        String line = "************************************************************";
        System.out.println(line);
        System.out.println(err);
        System.out.println(line);
    }

    /**
     * Print out the bye with different style.
     */
    public void showBye() {
        show("Bye. See you again");
    }

}
