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
                scanner.close();
            } else {
                System.out.println(
                        "____________________________________________________________\n" +
                                Duke.processCommand(command, list) +
                                "\n____________________________________________________________\n");
            }
        }

    }

    private static String processCommand(String command, DukeList list) {
        String[] stringArray = command.split(" ");

        if (stringArray[0].equals("list")) {
            return list.toString();
        } else if (stringArray[0].equals("done")) {
            if (stringArray.length > 1) {
                return list.markDone(Integer.parseInt(stringArray[1]) - 1);
            } else {
                return "Please choose a task to mark as done, with \"done <task number>\"";
            }
        } else {
            return list.addItem(command);
        }
    }
}
