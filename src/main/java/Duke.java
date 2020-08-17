import java.util.Scanner;

public class Duke {
    private static final String line = "----------------------";

    // for the list
    private static final String[] tasks = new String[100];
    private static int index = 0;

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        greet();
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                exit();
                break;
            } else if (input.equals("list")) {
                displayList();
            } else {
                addOnToList(input);
            }
        }
    }

    private static void greet() {
        System.out.println("Hello! I'm Duke" + "\n" +
                "What can I do for you?");
    }

    private static void echo(String input) {
        System.out.println(line + "\n\t" + input + "\n" + line);
    }

    private static void exit() {
        echo("Bye. Hope to see you again soon!");
    }

    private static void addOnToList(String task) {
        tasks[index++] = task;
        System.out.println("added: " + task);
    }

    private static void displayList() {
        if (index == 0) {
            System.out.println("No tasks!");
            return;
        }

        for (int i = 0; i < index; i++) {
            System.out.println(i + 1 + ". " + tasks[i]);
        }
    }

}
