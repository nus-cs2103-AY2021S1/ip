import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        Scanner sc = new Scanner(System.in);
        System.out.println(logo + "\nHello! I'm Duke\nWhat can I do for you?" +
                "\nAwaiting input...");
        var list = new ArrayList<String>();
        while (true) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                return;
            } else if (input.equals("list")) {
                int i = 0;
                while (list.size() > i) {
                    System.out.println(++i + ". " + list.get(i - 1));
                }
            } else {
                list.add(input);
                System.out.println("added: " + input);
            }
        }
    }
}
