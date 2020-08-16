import java.util.Scanner;


public class Gel {

    public static void parrot() {

        Scanner sc = new Scanner(System.in);

        System.out.println("    Hello! I'm Gel\n    What can I do for you?");

        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            if (line.equals("bye")) {
                System.out.println("\n    Bye. Hope to see you again soon!\n");
                break;
            } else {
                System.out.println("\n    " + line + "\n");
            }

        }
    }
}
