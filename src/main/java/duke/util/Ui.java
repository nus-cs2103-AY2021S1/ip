package duke.util;

import duke.exception.DukeException;
import duke.task.Task;

import java.util.Scanner;

public class Ui {

    private Scanner scanner;
    public Ui() {
        intro();
        this.scanner = new Scanner(System.in);
    }

    /**
     * Prints output enclosed in top and bottom horizontal lines
     * @param message message to be output
     */
    private static void println(String... message) {
        System.out.println("\t____________________________________________________________");
        for (String s : message) {
            System.out.println("\t" + s);
        }
        System.out.println("\t____________________________________________________________");
    }

    private void intro() {
        println("Hello! I'm KING!", "Let me load the stored file ~~ ");
    }

    public void showLoadSuccess(TaskList taskList) {
        printList(taskList);
    }

    public void showLoadingError() {
        println("I'm unable to retrieve the stored file.");
    }

    public void printList(TaskList taskList) {
        if (taskList.size() == 0) {
            println("I can't find any task in your list...",
                    "Try adding some task using \"todo\", \"deadline\" and \"event\" command");
            return;
        }

        String[] output = new String[taskList.size() + 1];
        output[0] = " Here are the tasks in your list:";

        for (int i = 1; i <= taskList.size(); i++) {
            output[i] = i + "." + taskList.get(i-1);
        }

        println(output);
    }

    public void showExit() {
        println("Bye. Hope to see you again soon!");
    }

    public void showAddTask(Task task, int size) {
        println(
                "Got it. I've added this task: ", task.toString(),
                "Now you have " + size + " tasks in the list."
        );
    }

    public void showRemoveTask(Task task, int size) {
        println( "Noted. I've removed this task: ", task.toString(),
                "Now you have " + size + " tasks in the list." );
    }

    public void showDone(Task task) {
        println("Nice! I've marked this task as done: ", task.toString());
    }

    public void printError(DukeException de) {
        println(de.getMessage());
    }

    public String readCommand() {
        return scanner.nextLine();
    }
}
