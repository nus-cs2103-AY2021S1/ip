/**
 * Represents the Ui that interacts with the user.
 */
public class Ui {
    private final String DIVIDER = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";
    private final String INDENT = "        ";

    public void showWelcomeMessage() {
        System.out.println(INDENT + "What can I do for you delightful human?\n" + DIVIDER);
    }

    public void showGoodbyeMessage() {
        System.out.println(INDENT + "Guess its time for us to part ways\n"
                + INDENT + "Thanks for the memories\n" + INDENT + ":`(");
    }

    public void showAllTasks(TaskList tasks) {
        if (tasks.getSize() == 0) {
            System.out.println(INDENT + "I have no tasks to show you sir");
        } else {
            System.out.println(INDENT + "Here are your tasks for today:");
            for (int i = 0; i < tasks.getSize(); i++) {
                Task curr = tasks.getTask(i);
                System.out.println(INDENT + (i + 1) + "." + curr);
            }
        }
    }

    public void showDoneTask(Task task) {
        System.out.println(INDENT + "Good job! I've marked this task as done:");
        System.out.println(INDENT + "  " + task);
    }

    public void showDeleteTask(Task task, TaskList tasks) {
        System.out.println(INDENT + "Of course sir. I have removed this task:\n" +
                INDENT + "  " + task);
        System.out.println(INDENT + "Now you have " + tasks.getSize() + " tasks in the list.");
    }

    public void showAddTask(Task task, TaskList tasks) {
        System.out.println(INDENT + "Affirmative. I've added this task:\n  " +
                INDENT + task);
        System.out.println(INDENT + "Now you have " + tasks.getSize() + " tasks in the list.");
    }

    public void showDivider() {
        System.out.println(DIVIDER);
    }

    public void showError(String message) {
        System.out.println(INDENT + message);
        showDivider();
    }

    public void showInvalidCommandMessage() {
        System.out.println(INDENT + "Sorry sir but I don't know what you mean :(");
    }
}
