package duke.parts;

import duke.task.Task;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    static String LINE = "    ____________________________________________________________";
    static String INDENT = "    ";
    Scanner sc = new Scanner(System.in);

    public void showWelcome() {
        System.out.println(LINE);
        System.out.println(INDENT + "Hello! What can I do for you?");
        System.out.println(LINE);
    }

    public void showLine() {
        System.out.println(LINE);
    }

    public String readCommand() {
        return sc.nextLine();
    }

    void printNumTask(int numTask) {
        System.out.println(String.format("%syou have %d tasks in the list.", INDENT, numTask));
    }

    public void showDelete(Task removed, int numLeft) {
        System.out.println(INDENT + "Tasked removed: ");
        System.out.println(INDENT + removed.getOutput());
        printNumTask(numLeft);
    }

    public void printList(Storage storage) throws IOException {
        ArrayList<Task> arrTask = storage.load();
        printNumTask(arrTask.size());
        System.out.println(INDENT + "Here are the tasks in your list:");
        for(int i = 0; i < arrTask.size(); i++) {
            System.out.println(String.format("%s%d. %s",INDENT,  i + 1, arrTask.get(i).getOutput()));
        }
    }

    public void printNew(Task task, String type, int numTask) {
        System.out.println(String.format("%sAdding %s to the list:",INDENT ,type));
        System.out.println(String.format("%s %s", INDENT, task.getOutput()));
        printNumTask(numTask);
    }

    public void bye() {
        System.out.println(INDENT + "Bye. Hope to see you again soon!");
    }
}
