import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String divider = "______________________________";
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println(divider);
        System.out.println("Beep Boop! Hello there!\n" + "What can I do for you?");
        System.out.println(divider);

        // Takes in user inputs. Program terminates when the String "bye" is entered.
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        while (!input.equals("bye")) {
            System.out.println(divider);
            System.out.println(input);
            System.out.println(divider);
            input = sc.nextLine();
        }

        System.out.println(divider);
        System.out.println("Goodbye, have a nice day :D");
        System.out.println(divider);
    }
}
