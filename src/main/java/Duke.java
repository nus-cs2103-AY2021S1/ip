import java.util.Scanner;

public class Duke {
    public static void greetEchoExit() {
        System.out.println("-----------------------------------------------------");
        System.out.println("    " + "Hello! I'm Duke\n    What can I do for you?");
        System.out.println("-----------------------------------------------------");

        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            if (line.equals("bye")) {
                System.out.println("-----------------------------------------------------");
                System.out.println("    " + "Bye. Hope to see you again soon!");
                System.out.println("-----------------------------------------------------");
                break;
            } else {
                System.out.println("-----------------------------------------------------");
                System.out.println("    " + line);
                System.out.println("-----------------------------------------------------");
            }
        }
    }

    public static void main(String[] args) {
        greetEchoExit();
    }
}
