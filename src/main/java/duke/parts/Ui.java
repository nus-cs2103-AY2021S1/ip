package duke.parts;

import duke.task.Task;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    static final String LINE = "    ____________________________________________________________";
    static final String INDENT = "    ";
    private Scanner sc = new Scanner(System.in);

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

    public String printNumTask(int numTask) {
        return String.format("%syou have %d tasks in the list.", INDENT, numTask);
    }

    public String showDelete(Task removed, int numLeft) {
        String taskLeft = printNumTask(numLeft);
        String output = INDENT + "Tasked removed: " + "\n"
                                + INDENT + removed.getOutput() + "\n"
                                + taskLeft;
        System.out.println(output);
        return output;
    }

    public String printList(Storage storage) throws IOException {
        ArrayList<Task> arrTask = storage.load();
        String output = printNumTask(arrTask.size()) + "\n";
        output += INDENT + "Here are the tasks in your list:\n";
        for (int i = 0; i < arrTask.size(); i++) {
            output += String.format("%s%d. %s", INDENT,  i + 1, arrTask.get(i).getOutput());
            if (i < arrTask.size() - 1) {
                output += "\n";
            }
        }
        System.out.println(output);
        return output;
    }

    public String printNew(Task task, String type, int numTask) {
        String output = String.format("%sAdding %s to the list:\n", INDENT, type)
                        + String.format("%s %s\n", INDENT, task.getOutput())
                                + printNumTask(numTask);
        System.out.println(output);
        return output;
    }

    public String printFind(ArrayList<Task> arr) {
        if (arr.isEmpty()) {
            System.out.println(INDENT + "There are no items");
            return INDENT + "There are no items";
        } else {
            String output = INDENT + "Here are the items that match the search request\n";
            for (int i = 0; i < arr.size(); i++) {
                output += String.format("%s%d) %s", INDENT, i + 1,
                        arr.get(i).getOutput()) + "\n";
            }
            System.out.println(output);
            return output;
        }
    }

    public String bye() {
        System.out.println(INDENT + "Bye. Hope to see you again soon");
        return INDENT + "Bye. Hope to see you again soon";
    }
}
