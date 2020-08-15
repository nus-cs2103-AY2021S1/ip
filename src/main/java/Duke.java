import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello! I'm\n" + logo + "How can I help you, Boss?");

        Scanner sc = new Scanner(System.in);
        String toEcho = sc.nextLine();
        while (!toEcho.equals("bye")) {
            System.out.println("--------------------------------------\n" +
                toEcho + "\n" + "--------------------------------------");
            toEcho = sc.nextLine();
        }
        System.out.println("--------------------------------------\n" +
            "Bye Boss! Hope to see you again!" + "\n" + "--------------------------------------");

    }
}
