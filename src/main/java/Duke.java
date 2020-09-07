import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    public static String HORIZONTAL_LINE =
            "_________________________________________________________________________________________";
    public static ArrayList<Task> LIST = new ArrayList<>();

    public static void main(String[] args) {
        startUpMessage();
        programLoop();

    }

    // Prints start up message upon running
    private static void startUpMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println(Duke.HORIZONTAL_LINE);
        System.out.println("Hello I'm Duke\nWhat can I do for you?");
        System.out.println(Duke.HORIZONTAL_LINE);
    }

    // Main program functionality
    private static void programLoop() {
        Scanner sc = new Scanner(System.in);

        while (true) {
            String input = sc.nextLine();
            System.out.println(Duke.HORIZONTAL_LINE);

            if (input.toLowerCase().equals("bye")) {
                // exit program if user inputs "bye"
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (input.toLowerCase().equals("list")) {
                // prints all elements of LIST if not empty
                if (Duke.LIST.size() == 0) {
                    continue;
                } else {
                    printList();
                }

            } else if (input.length() > 4 && input.substring(0,4).toLowerCase().equals("done")) {
                // marks task at specified index as done
                try {
                    String[] command = input.split(" ");
                    int index = Integer.valueOf(command[1]);
                    Task current = Duke.LIST.get(index - 1);
                    current.completeTask();
                    System.out.println("Nice! I have marked this task as done:\n\t" + current.printTask());
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Please input a valid index");
                }
            } else {
                // add Task to LIST
                Task task = new Task(input);
                Duke.LIST.add(task);
                System.out.println("added: " + input);
            }
            System.out.println(Duke.HORIZONTAL_LINE);
        } // end while loop
    }

    public static void printList() {
        System.out.println("Here are your tasks: ");
        for (int i = 0; i < Duke.LIST.size(); i++) {
            System.out.println(i + 1 + ". " + Duke.LIST.get(i).printTask());
        }
    }
}