import java.util.Scanner;

public class Duke {
    public static String horizontal_line = "____________________________________________________________";

    public static void greeting() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println(horizontal_line);
        System.out.println("Hello from\n" + logo);
        System.out.println(horizontal_line);
    }

    public static void reponse(String input) {
        if (input.equals("bye")) {
            System.out.println(horizontal_line);
            System.out.println("Bye. Hope to see you again soon!");
            System.out.println(horizontal_line);
        } else {
            System.out.println(horizontal_line);
            System.out.println(input);
            System.out.println(horizontal_line);
        }
    }

    public static void main(String[] args) {
        greeting();

        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        while (!input.equals("bye")) {
            reponse(input);
            input = sc.nextLine();
        }

        reponse(input);
    }
}
