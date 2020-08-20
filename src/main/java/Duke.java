import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?\n");

        ArrayList<String> tasks = new ArrayList<String>();
        Scanner sc = new Scanner(System.in);
        while (true) {
            String input = sc.nextLine();

            if (input.equals("bye")) {
                String bye = "Bye. Hope to see you again soon!";
                System.out.println(bye);
                break;
            }
            else if (input.equals("list")) {
                for (int i = 0; i < tasks.size(); ++i) {
                    System.out.println(String.format("%d. ", i + 1) + tasks.get(i));
                }
            }
            else {
                tasks.add(input);
                System.out.println("added: " + input);
            }
        }
    }
}
