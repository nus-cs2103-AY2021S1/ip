
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke\n" +
                "What can I do for you?");
        Scanner sc = new Scanner(System.in);

        while (sc.hasNext()) {

            String echo = sc.nextLine();
            String[] s = echo.split("\\s");
            String first = s[0];

            System.out.println(first);
            if (first.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }
        }
    }
}
