import java.util.*;

public class Duke {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can i do for you?");
        while (true) {
            String input = sc.next();
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else {
                System.out.println(input);
            }
        }
    }
}
