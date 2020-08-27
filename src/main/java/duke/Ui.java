package duke;

import duke.tasks.Task;

import java.util.Scanner;

public class Ui {

    Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }
    public String readInput() {
        return scanner.nextLine();
    }

    public void welcome() {
        writeOutput("Hello! I'm duke.Duke", "What can I do for you?");
    }

    public void writeAdd(Task task, int size) {
        writeOutput("Got it. I've added this task:", task.toString(),
                String.format("Now you have %d tasks in the list.", size));
    }

    public void writeDone(Task task) {
        writeOutput("Nice! I've marked this task as done:", "\t" + task.toString());
    }

    public void writeDelete(Task task, int size) {
        writeOutput("Noted. I've removed this task:", "\t" + task.toString(),
                String.format("Now you have %d tasks in the list.", size));
    }

    public void writeOutput(String... messages) {
        System.out.println("\t-----------------------------------------");
        for (String message : messages) {
            System.out.println("\t" + message);
        }
        System.out.println("\t-----------------------------------------");
    }

    public void exit() {
        writeOutput("Bye. Hope to see you again soon!");
        scanner.close();
    }
}
