package duke;

import java.util.ArrayList;
import java.util.Scanner;

import task.Task;

/**
 * Ui is the user interface interacts with the user. The input from the user is first received by the ui
 * and the output from Duke is given by the Ui.
 *
 * @author Joshua
 */
public class Ui {
    /**
     * sc is the Scanner that parses the lines in storage.
     */
    private static final String GREETING_MESSAGE = "Buenos Dias! Soy Duke.Duke, como estas mi amigo?";
    private Scanner sc;
    /**
     * Creates the Ui object.
     */
    public Ui() {
        sc = new Scanner(System.in);
    }

    /**
     * Prints the initialization message for the user.
     *
     * @return the welcome message.
     */
    public String showWelcome() {
        return GREETING_MESSAGE;
    }

    /**
     * Returns the error message given.
     *
     * @param error the error message to be printed.
     * @return the output to be returned to the user.
     */
    public String showError(String error) throws DukeException {
        throw new DukeException(error);
    }

    /**
     * Returns the tasks in the TaskList.
     *
     * @param taskList the TaskList that contains the tasks.
     * @return the output to be returned to the user.
     */
    public String showList(TaskList taskList) {
        StringBuilder outputMessage = new StringBuilder("Estas son las tareas de su lista:");
        assert taskList.getTaskList().size() > 0;
        for (int i = 0; i < taskList.getTaskList().size(); i++) {
            outputMessage.append("\n").append(i + 1).append(". ").append(taskList.getTaskList().get(i).toString());
        }
        return outputMessage.toString();
    }

    /**
     * Returns the task that has been added to the Task List.
     *
     * @param task the task that was added.
     * @return the output to be returned to the user.
     */
    public String showAdded(Task task) {
        return "Entendido. He agregado esta tarea:\n" + task;
    }

    /**
     * Returns the task that has been completed.
     *
     * @param task the task that was completed.
     * @return the output to be returned to the user.
     */
    public String showDone(Task task) {
        return "Agradable! He marcado esta tarea como hecha:" + "\n" + task;
    }

    /**
     * Returns the task that has been deleted.
     *
     * @param task the task that was deleted.
     * @return the output to be returned to the user.
     */
    public String showDeleted(Task task) {
        return "Celebre. He eliminado esta tarea:" + "\n" + task;
    }

    /**
     * Returns the exit message.
     *
     * @return the output to be returned to the user.
     */
    public String showExit() {
        return "Adios, amigos!";
    }

    /**
     * Returns the number of tasks in the TaskList.
     *
     * @param taskList the tasklist to be accessed.
     * @return the output to be returned to the user.
     */
    public String showNumberInList(TaskList taskList) {
        return "Ahora tienes " + taskList.getTaskList().size() + " tareas en la lista.";
    }

    /**
     * Returns the number of found tasks with the keyword.
     *
     * @param foundTaskList the list of tasks found.
     * @return the output to be returned to the user.
     */
    public String showFoundList(ArrayList<Task> foundTaskList) {
        StringBuilder outputMessage = new StringBuilder("Aqui estan las tareas coincidentes en su lista:");
        assert foundTaskList.size() > 0;
        for (int i = 0; i < foundTaskList.size(); i++) {
            outputMessage.append("\n").append(i + 1).append(". ").append(foundTaskList.get(i).toString());
        }
        return outputMessage.toString();
    }
}
