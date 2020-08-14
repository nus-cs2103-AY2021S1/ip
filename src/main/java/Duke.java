import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(
                "____________________________________________________________\nHello from\n" +
                        logo + "\n What can I do for you?" +
                        "\n____________________________________________________________\n");

        Scanner scanner = new Scanner(System.in);
        DukeList list = new DukeList();

        boolean running = true;
        while (running) {
            String command = scanner.nextLine();
            if (command.equals("bye")) {
                running = false;
                System.out.println(
                        "____________________________________________________________\n" +
                                "Bye. Hope to see you again soon!" +
                                "\n____________________________________________________________\n");
            } else {
                System.out.println(
                        "____________________________________________________________\n" +
                                Duke.processCommand(command, list) +
                                "\n____________________________________________________________\n");
            }
        }

    }

    private static String processCommand(String command, DukeList list) {
        if (command.equals("list")) {
            return list.toString();
        } else {
            return list.addItem(command);
        }
    }
}
