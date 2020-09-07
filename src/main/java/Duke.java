import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    public static String HORIZONTAL_LINE =
            "_________________________________________________________________________________________";
    public static ArrayList<String> LIST = new ArrayList<>();

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
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (input.toLowerCase().equals("list")) {
                if (Duke.LIST.size() == 0) {
                    continue;
                } else {
                    for (int i = 0; i < Duke.LIST.size(); i++) {
                        System.out.println((i + 1) + ". " + Duke.LIST.get(i));
                    }
                }

            } else {
                Duke.LIST.add(input);
                System.out.println("added: " + input);
            }
            System.out.println(Duke.HORIZONTAL_LINE);
        }
    }
}