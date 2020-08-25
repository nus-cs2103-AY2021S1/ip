package duke.ui;

import duke.task.Task;
import duke.tasklist.TaskList;

import java.util.Scanner;

public class Ui {

    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void printMsg(String msg) {
        String separator = "   --------------------------------------------------------------\n";
        System.out.print(separator);
        System.out.printf("    %s\n", msg);
        System.out.println(separator);
    }

    public void greetings() {
        System.out.println("Hello, I'm Duke!\nWhat can I do for you?");
    }

    public void goodBye() {
        printMsg("Bye! Hope to see you again soon! ☺");
        scanner.close();
    }

    public void fileCreationError() {
        System.out.println("Error in creating file.\n");
    }

    public void fileUpdateError() {
        System.out.println("Error in updating file\n");
    }

    public void fileReadingError() {
        System.out.println("Error in reading from csv file\n");
    }

    public void printBasic(String input) {
        System.out.printf("%s\n\n", input);
    }

    public void markTaskAsDone(Task current) {
        printMsg(String.format("Nice! I've marked this task as done:\n      %s", current));
    }

    public void deleteTask(Task current, int size) {
        printMsg(String.format("Noted. I've removed this task:\n      %s\n" +
                "    Now you have %d tasks in the list.", current, size));
    }

    public void addTask(Task newTask, int size) {
        printMsg(String.format("Got it. I've added this task:\n      %s\n" +
                "    Now you have %d tasks in the list.", newTask, size));
    }

    public void emptyTaskList() {
        printMsg("You currently have no tasks in the list.");
    }

    public void showTaskList(TaskList tasks, String extra) {
        StringBuilder str1 = new StringBuilder();
        str1.append(String.format("Here are the %s tasks in your list:\n", extra));
        int size = tasks.size();
        for (int i = 0; i < size - 1; i++) {
            str1.append(String.format("     %d.%s\n", i + 1, tasks.get(i)));
        }
        str1.append(String.format("     %d.%s", size, tasks.get(size - 1)));
        printMsg(str1.toString());
    }

    public void emptyFind(String queryWord) {
        printMsg(String.format("There are no matching tasks with the keyword %s.", queryWord));
    }
}
