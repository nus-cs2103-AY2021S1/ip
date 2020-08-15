import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        String greeting = "    ____________________________________________________________\n" +
                "    Hello! I'm Duke\n" +
                "    What can I do for you?\n" +
                "    ____________________________________________________________";
        System.out.println(greeting);

        Scanner sc = new Scanner(System.in);
        ArrayList<String> todos = new ArrayList<>();

        while (sc.hasNextLine()) {
            String input = sc.nextLine();

            if (input.equals("bye")) {
                break;
            }

            if (input.equals("list")) {
                StringBuilder output = new StringBuilder("    ____________________________________________________________\n");

                for (int i = 1; i <= todos.size(); i++) {
                    output.append("\t").append(i).append(". ").append(todos.get(i - 1)).append("\n");
                }

                output.append("    ____________________________________________________________");

                System.out.println(output);
            } else if (!input.equals("")) {
                todos.add(input);
                String output = "    ____________________________________________________________\n" +
                        "     added: " + input + "\n" +
                        "    ____________________________________________________________";
                System.out.println(output);
            }
        }

        String byeMessage = "    ____________________________________________________________\n" +
                "     Bye. Hope to see you again soon!\n" +
                "    ____________________________________________________________";
        System.out.println(byeMessage);
    }
}
