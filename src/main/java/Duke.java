import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        welcomeMessage();
        String inputLine = sc.nextLine();
        while (!inputLine.equals("bye")) {
            System.out.println(inputLine);
            inputLine = sc.nextLine();
        }
        goodbyeMessage();
    }

    private static void welcomeMessage() {
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm your mother!");
        System.out.println("What can I do for you son!");
        System.out.println("____________________________________________________________");
    }

    private static void goodbyeMessage() {
        System.out.println("____________________________________________________________");
        System.out.println("Goodbye my child!");
        System.out.println("____________________________________________________________");
    }
}
