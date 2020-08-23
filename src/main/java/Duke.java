import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _\n"
                + "|  _ \\ _   _| | _____\n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(
                "____________________________________________________________\nHello from\n" +
                        logo + "\nWhat can I do for you?" +
                        "\n____________________________________________________________");

        Scanner scanner = new Scanner(System.in);
        FileHandler fileHandler = new FileHandler();
        DukeList list = fileHandler.getList();
        Command system = new Command(list);

        boolean running = true;
        while (running && scanner.hasNextLine()) {
            String command = scanner.nextLine();
            if (command.equals("bye")) {
                running = false;

                fileHandler.updateFile(list);
                System.out.println(
                        "____________________________________________________________\n" +
                                "Bye! Hope to see you again soon!" +
                                "\n____________________________________________________________\n");
            } else {
                String result;
                try {
                    result = system.processCommand(command);
                } catch (Exception e) {
                    result = e.getMessage();
                }
                System.out.println(
                        "____________________________________________________________\n" +
                                result +
                                "\n____________________________________________________________\n");
            }
        }
        scanner.close();
    }
}


