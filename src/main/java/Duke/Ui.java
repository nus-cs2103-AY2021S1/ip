package duke;

import task.Task;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private Scanner sc;
    private final static String GREETING_MESSAGE = "Buenos Dias! Soy Duke.Duke, como estas mi amigo?";

    public Ui() {
        sc = new Scanner(System.in);
    }

    public void showWelcome() {
        System.out.println(GREETING_MESSAGE);
    }

    public String readCommand() {
        return sc.nextLine();
    }

    public void showError(String error) {
        System.out.println(error);
    }

    public void showList() {
        System.out.println("Estas son las tareas de su lista:");
    }

    public void showAdded(Task task) {
        System.out.println("Entendido. He agregado esta tarea:\n" + task);
    }

    public void showDone(Task task) {
        System.out.println("Agradable! He marcado esta tarea como hecha:");
        System.out.println(task);
    }

    public void showDeleted(Task task) {
        System.out.println("Célebre. He eliminado esta tarea:");
        System.out.println(task);
    }

    public void showExit() {
        System.out.println("Adios, amigos!");
    }

    public void showNumberInList(TaskList taskList) {
        System.out.println("Ahora tienes "  + taskList.getTaskList().size() +  " tareas en la lista.");
    }

    public void showFoundList(ArrayList foundTaskList) {
        System.out.println("Aquí están las tareas coincidentes en su lista:");
        for (int i = 0; i < foundTaskList.size(); i++) {
            System.out.println((i + 1) + ". " + foundTaskList.get(i).toString());
        }
    }
}
