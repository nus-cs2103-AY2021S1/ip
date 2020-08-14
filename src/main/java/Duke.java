import java.util.Scanner;
public class Duke {

    private static String indent = "   ";
    public static void greet() {
        System.out.println(indent + "----------------------------");
        System.out.println(indent + "Hello! I'm Best2103/TBot\n"+ indent + "What can I do for you?");
        System.out.println(indent + "----------------------------");
    }

    public static void respond() {
        Scanner userInput = new Scanner(System.in);
        while(true) {
            String input = userInput.nextLine();
            System.out.println(indent + "----------------------------");
            if (input.trim().equals("bye")) {
                System.out.println(indent + "Bye. Hope to see you again soon!");
                System.out.println(indent + "----------------------------");
                break;
            }
            System.out.println(indent + input);
            System.out.println(indent + "----------------------------");
        }
    }

    public static void main(String[] args) {
        greet();
        respond();
    }
}
