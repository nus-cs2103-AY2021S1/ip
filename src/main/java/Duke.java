import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("Hello! I'm Duke \n"
                            + "What can I do for you?");
        Scanner sc = new Scanner(System.in);
        List<String> inputs = new ArrayList<>();
        String input = "";
        while (true) {
            input = sc.nextLine();
            if (!input.equals("bye") && !input.equals("list")) {
                inputs.add(input);
                System.out.println("added: " + input);
            } else if (input.equals("list")) {
                int len = inputs.size();
                for (int i = 1; i <= len; i++) {
                    System.out.println(i + ". " + inputs.get(i-1));
                }
            } else {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }
        }
    }
}
