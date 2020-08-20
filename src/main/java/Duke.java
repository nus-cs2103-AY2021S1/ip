import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        greeting();
        Scanner scanner = new Scanner(System.in);  // Create a Scanner object
        String input = scanner.nextLine();  // Read user input
        while(!input.equals("bye")) {
            System.out.println(input);  // Output user input
            input = scanner.nextLine();
        }

        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void greeting() {
        System.out.println("Hello! I'm KK\n" +
                " What can I do for you?");
    }

}
