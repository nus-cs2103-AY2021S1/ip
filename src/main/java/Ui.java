import java.util.Scanner;

// Class deals with interactions with the user
public class Ui {
    Scanner sc;

    public Ui() {
        sc = new Scanner(System.in);
    }

    public void showWelcome() {
        String greetings = "Welcome! I am your personal manager, Butler.\n"
                + "How may I help you today?\n";
        System.out.println(greetings);
    }

    public String readCommand() {
        return sc.nextLine();
    }

    public void showLine() {
        System.out.println("------------------------------");
    }

    public void showError(String errorMessage) {
        System.out.println("ERROR: " + errorMessage);
    }

    public void showExit() {
        String farewells = "It is my honor to have served you.\n"
                + "Please come back again.";
        System.out.println(farewells);
    }

    public void showLoadingError() {
        String loadingErrorMessage = "taskList.txt file was not detected.\n"
                + "A new task list will be created.";
        System.out.println(loadingErrorMessage);
    }

    public void showTaskCompleted(int i) {
        String msg = "Task " + i + " has been marked as complete.";
        System.out.println(msg);
    }

    public void showTaskAdded(Task task) {
        String msg = "The following task has been added to task list.\n" + task;
        System.out.println(msg);
    }

    public void printTaskList(TaskList taskList) {
        String msg = "Here are your list of tasks.\n"
                + "You have " + taskList.getSize()
                + (taskList.getSize() > 1 ? " tasks" : " task")
                + " in total.\n";
        for (int i = 0; i < taskList.getSize(); i++) {
            msg += "\n" + (i+1) + ": " + taskList.getTask(i);
        }
        System.out.println(msg);
    }

    public void showTaskDeleted(int taskIndex) {
        String msg = "Task " + taskIndex + " has been deleted.\n";
        System.out.println(msg);
    }
}
