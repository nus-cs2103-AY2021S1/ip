import java.util.Scanner;

public class Duke {
    boolean running = false;
    String[] memory = new String[100];
    int currentMemoryIndex = 0;

    boolean isRunning() {
        return running;
    }

    void setRunningState(boolean bool) {
        running = bool;
    }

    void indent() {
        System.out.print("    ");
    }

    void greet() {
        indent();
        System.out.println("Hello! I'm Duke.");
        indent();
        System.out.println("How can I help you today?");
    }

    void printLine() {
        System.out.println("-------------------------------------------------------------");
    }

    void opener() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        indent();
        printLine();

        greet();

        indent();
        printLine();
    }

    void farewell() {
        indent();
        printLine();

        indent();
        System.out.println("Goodbye! Hope to see you again soon!");

        indent();
        printLine();
    }

    void list() {
        indent();
        printLine();

        for (int i = 0; i < currentMemoryIndex; i++) {
            indent();
            System.out.println((i + 1) + ". " + memory[i]);
        }

        indent();
        printLine();
    }

    void addToMemory(String string) {
        memory[currentMemoryIndex] = string;
        currentMemoryIndex++;
    }

    void printAddMessage(String string) {
        indent();
        printLine();

        indent();
        System.out.println("added " + string);

        indent();
        printLine();
    }

    void checkAndPrint(String string) {
        if (string.equals("bye")) {
            exit();
        } else if (string.equals("list")) {
            list();
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
