import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String message = "____________________________________________________________\n" +
                "  Hello! I'm Duke\n" +
                "  What can I do for you?\n" +
                "____________________________________________________________\n";
        System.out.println(message);

        Scanner scanner = new Scanner(System.in);
        String string1 = scanner.nextLine();
        String message1;

        List lists = new ArrayList<String>();

        while (!string1.equals("bye")) {
            if (string1.equals("list")) {
                System.out.println("____________________________________________________________\n");
                for (int i = 0; i < lists.size(); i++) {
                    System.out.println((i + 1) + ". " + lists.get(i));
                }
                System.out.println("____________________________________________________________\n");
            } else {
                lists.add(string1);
                message1 = "____________________________________________________________\n" +
                        "  added: " + string1 + "\n" +
                        "____________________________________________________________\n";
                System.out.println(message1);
            }
            string1 = scanner.nextLine();
        }

        message1 = "____________________________________________________________\n" +
                "  Bye. Hope to see you again soon!\n" +
                "____________________________________________________________";
        System.out.println(message1);
    }
}
