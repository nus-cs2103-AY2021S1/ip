import java.util.*;

public class Duke {

    public static void main(String[] args) {
        String greet = "   ____________________________________________________________\n" +
                "    Hello! I'm Duke\n" +
                "    What can I do for you?\n" +
                "   ____________________________________________________________\n";

        String exit = "    ____________________________________________________________\n" +
                "     Bye. Hope to see you again soon!\n" +
                "    ____________________________________________________________";

        String line = "    ____________________________________________________________";

        System.out.println(greet);

        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                System.out.println(exit);
                break;
            } else {
                System.out.println(line + "\n" + "     " + input + "\n" + line + "\n");
            }
        }
    }
}
