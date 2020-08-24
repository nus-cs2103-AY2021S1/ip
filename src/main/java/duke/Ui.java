package duke;

import java.util.List;
import java.util.Scanner;

public class Ui {

    private Scanner sc;
    private String tab = "     ";
    private String line = "____________________________________________________________";

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    public String getInput() {
        return sc.nextLine();
    }

    public void greet() {
        String tab = "     ";
        chatPrint("Hello! I'm Duke\n" +
                tab + "What can I do for you?");
    }

    public void farewell() {
        chatPrint("Bye. Hope to see you again soon!");
    }

    public void onList(List<Task> tasks) {
        int id = 1;
        StringBuilder output = new StringBuilder("Here are the tasks in your list:");
        for (Task task : tasks) {
            output.append("\n").append(tab).append(id).append(". ").append(task);
            id++;
        }
        chatPrint(output.toString());
    }

    public void onDone(Task task) {
        chatPrint("Nice! I've marked this task as done:\n" +
                tab + "   " + task);
    }

    public void onDelete(Task task, int size) {
        chatPrint("Noted. I've removed this task:\n" +
                tab + "   " + task + "\n" +
                tab + "Now you have " + size + " tasks in the list.");
    }

    public void onAdd(Task task, int size) {
        chatPrint("Got it. I've added this task:\n" +
                tab + "   " + task + "\n" +
                tab + "Now you have " + size + " tasks in the list.");    }

    public void chatPrint(String toPrint) {
        System.out.println(tab + line);
        System.out.println(tab + toPrint);
        System.out.println(tab + line + "\n");
    }
}
