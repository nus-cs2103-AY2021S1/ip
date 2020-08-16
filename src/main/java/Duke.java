import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        greet();
        echo();
    }

    private static void greet() {
        String logo =
                " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.print("Hello, I am\n" + logo);
        System.out.println("___________________________________________________");

        System.out.println("Duke: What can i do for you?");
    }

    private static void echo() {
        Scanner sc = new Scanner(System.in);
        String reply = "Duke: " + "%s" + "\n--------------------------" +
                "-------------------------";
        while (sc.hasNext()) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                System.out.println(String.format(reply, "Bye. Hope to see you again soon!"));
                sc.close();
                break;
            } else {
                System.out.println(String.format(reply, input));
            }
        }
    }
}
