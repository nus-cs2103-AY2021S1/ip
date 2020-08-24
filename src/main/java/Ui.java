import java.util.Scanner;

public class Ui {
    public TaskListHandler handler;
    public Storage storage;

    public Ui(TaskListHandler handler, Storage storage) {
        this.handler = handler;
        this.storage = storage;
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            // Listens for input
            String input = scanner.nextLine();
            try {
                Command c = Parser.parse(input, handler);
                c.execute(handler, storage);
                System.out.println();
            } catch (DukeException e) {
                e.printStackTrace(System.out);
                DukeException.tryAgain();
            }
        }
    }

    public static void indent(int times) {
        for (int i=0; i<times; i++) {
            System.out.print("    ");
        }
    }

    public static void printSuccess(String operation, Task currentTask, int listSize) {
        // Prints success message and list size after task added/deleted
        indent(1);
        if (operation.equals("add")) {
            System.out.print("Successfully added:\n");
        } else if (operation.equals("delete")) {
            System.out.print("Noted. I've removed this task:\n");
        } else {
            System.out.println("Good job! You completed:");
            indent(2);
            System.out.println(currentTask);
            return;
        }
        indent(2);
        System.out.println(currentTask);
        indent(1);
        System.out.println("You have " + listSize + " task(s) in the list.");
    }

    public static void greet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        String greeting = "Hello! I'm Duke the chatbot! \n" +
                "What can I do for you?\n";
        System.out.println(greeting);
    }
}
