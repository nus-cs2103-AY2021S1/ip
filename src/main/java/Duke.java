import java.util.Scanner;
import java.util.ArrayList;

import static java.lang.Integer.parseInt;

public class Duke {

    boolean running = false;
    ArrayList<Task> memory = new ArrayList<>();

    boolean isRunning() {
        return running;
    }

    void setRunningState(boolean bool) {
        running = bool;
    }

    void indent() {
        System.out.print("    ");
    }

    boolean stringIsInt(String string) {
        try {
            parseInt(string);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }


    void greet() {
        indent();
        System.out.println("Hello! I'm Duke.");
        indent();
        System.out.println("How can I help you today?");
    }

    void printLine() {
        indent();
        System.out.println("-------------------------------------------------------------");
    }

    void opener() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        printLine();

        greet();

        printLine();
    }

    void farewell() {
        printLine();

        indent();
        System.out.println("Goodbye! Hope to see you again soon!");

        printLine();
    }

    void list() {
        printLine();

        indent();
        System.out.println("Here are the tasks in your list:");

        for (int i = 0; i < memory.size(); i++) {
            indent();
            Task currentTask = memory.get(i);
            System.out.println((i + 1) + ". " + "[" +
                    currentTask.getStatusIcon() + "] " + currentTask.getDescription());
        }

        printLine();
    }

    void done(int index) {
        try {
            Task task = memory.get(index - 1);
            task.markAsDone();
            printDoneMessage(task);
        } catch (IndexOutOfBoundsException e) {
            printLine();

            indent();
            System.out.println("No such task!");

            printLine();
        }
    }


    void addToMemory(String string) {
        memory.add(new Task(string));
    }

    void printAddMessage(String string) {
        printLine();

        indent();
        System.out.println("added: " + string);

        printLine();
    }

    void printDoneMessage(Task task) {
        printLine();

        indent();
        System.out.println("Nice! I've marked this task as done:");

        indent();
        System.out.println("[" +
                task.getStatusIcon() + "] " + task.getDescription());

        printLine();
    }

    void checkAndPrint(String string) {
        String[] splitString = string.split("\\s+");
        if (string.equals("bye")) {
            exit();
        } else if (string.equals("list")) {
            list();
        } else if (splitString.length == 2 &&
                splitString[0].equals("done") && stringIsInt(splitString[1])) {
            int index = parseInt(splitString[1]);
            // out of bounds index??
            done(index);

        } else {
            addToMemory(string);
            printAddMessage(string);
        }
    }

    void exit() {
        running = false;
        farewell();
    }



    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.setRunningState(true);
        Scanner sc = new Scanner(System.in);

        duke.opener();

        while (duke.isRunning()) {
            String str = sc.nextLine();
            duke.checkAndPrint(str);
        }
    }
}
