import java.util.Scanner;

public class Ui {

    private final Scanner scanner;

    protected Ui() {
        this.scanner = new Scanner(System.in);
    }

    protected String readCommand() {
        return scanner.nextLine();
    }

    protected void printMsg(String msg) {
        String separator = "   --------------------------------------------------------------\n";
        System.out.print(separator);
        System.out.printf("    %s\n", msg);
        System.out.println(separator);
    }

    protected void greetings() {
        System.out.println("Hello, I'm Duke!\nWhat can I do for you?");
    }

    protected void goodBye() {
        printMsg("Bye! Hope to see you again soon! ☺");
        scanner.close();
    }

    protected void fileCreationError() {
        System.out.println("Error in creating file.\n");
    }

    protected void fileUpdateError() {
        System.out.println("Error in updating file\n");
    }

    protected void fileReadingError() {
        System.out.println("Error in reading from csv file\n");
    }

    protected void printBasic(String input) {
        System.out.printf("%s\n\n", input);
    }

    protected void markTaskAsDone(Task current) {
        printMsg(String.format("Nice! I've marked this task as done:\n      %s", current));
    }

    protected void deleteTask(Task current, int size) {
        printMsg(String.format("Noted. I've removed this task:\n      %s\n" +
                "    Now you have %d tasks in the list.", current, size));
    }

    protected void addTask(Task newTask, int size) {
        printMsg(String.format("Got it. I've added this task:\n      %s\n" +
                "    Now you have %d tasks in the list.", newTask, size));
    }

    protected void emptyTaskList() {
        printMsg("You currently have no tasks in the list.");
    }

    protected void showTaskList(TaskList tasks) {
        StringBuilder str1 = new StringBuilder();
        str1.append("Here are the tasks in your list:\n");
        int size = tasks.size();
        for (int i = 0; i < size - 1; i++) {
            str1.append(String.format("     %d.%s\n", i + 1, tasks.get(i)));
        }
        str1.append(String.format("     %d.%s", size, tasks.get(size - 1)));
        printMsg(str1.toString());
    }
}
