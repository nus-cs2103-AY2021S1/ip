import java.util.Scanner;

public class Duke {

    public static void start(Scanner scanner) {
        System.out.println("Hello! I'm Duke\n" +
                "What can I do for you?");
        String line = scanner.nextLine();
        while(!line.equals("bye")) {
            System.out.println(line);
            line = scanner.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        Scanner scanner = new Scanner(System.in);
        Duke.start(scanner);
    }
}
