package duke;

import task.Task;
import java.util.Scanner;

/**
 * Ui is the user interface interacts with the user. The input from the user is first received by the ui
 * and the output from Duke is given by the Ui.
 *
 * @author Joshua
 */
public class Ui {
    /**
     * sc is the Scanner that parses the user's input.
     */
    private Scanner sc;
    private final static String GREETING_MESSAGE = "Buenos Dias! Soy Duke.Duke, como estas mi amigo?";

    /**
     * Creates the Ui object.
     */
    public Ui() {
        sc = new Scanner(System.in);
    }

    /**
     * Prints the initialization message for the user.
     */
    public void showWelcome() {
        System.out.println(GREETING_MESSAGE);
    }

    /**
     * Parses the input from the user.
     *
     * @return the input from the user as a String.
     */
    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * Prints the error message given.
     *
     * @param error the error message to be printed.
     */
    public void showError(String error) {
        System.out.println(error);
    }

    /**
     * Prints the tasks in the TaskList.
     *
     * @param taskList the TaskList that contains the tasks.
     */
    public void showList(TaskList taskList) {
        System.out.println("Estas son las tareas de su lista:");
        for (int i = 0; i < taskList.getTaskList().size(); i++) {
            System.out.println((i + 1) + ". " + taskList.getTaskList().get(i).toString());
        }
    }

    /**
     * Prints the task that has been added to the Task List.
     *
     * @param task the task that was added.
     */
    public void showAdded(Task task) {
        System.out.println("Entendido. He agregado esta tarea:\n" + task);
    }

    /**
     * Prints the task that has been completed.
     *
     * @param task the task that was completed.
     */
    public void showDone(Task task) {
        System.out.println("Agradable! He marcado esta tarea como hecha:");
        System.out.println(task);
    }

    /**
     * Prints the task that has been deleted.
     *
     * @param task the task that was deleted.
     */
    public void showDeleted(Task task) {
        System.out.println("Célebre. He eliminado esta tarea:");
        System.out.println(task);
    }

    /**
     * Prints the exit message.
     */
    public void showExit() {
        System.out.println("Adios, amigos!");
    }

    /**
     * Prints the number of tasks in the TaskList.
     *
     * @param taskList the tasklist to be accessed.
     */
    public void showNumberInList(TaskList taskList) {
        System.out.println("Ahora tienes "  + taskList.getTaskList().size() +  " tareas en la lista.");
    }
}
