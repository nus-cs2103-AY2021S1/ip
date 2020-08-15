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
        ArrayList<Task> todos = new ArrayList<>();

        while (sc.hasNextLine()) {
            String input = sc.nextLine();

            if (input.equals("bye")) {
                break;
            }

            if (input.startsWith("done")) {
                int idx = Integer.parseInt(input.substring(5)) - 1;
                Task toChange = todos.get(idx);
                toChange.markAsDone();
                String output = "    ____________________________________________________________\n" +
                        "     Nice! I've marked this task as done: \n" +
                        "       [" + toChange.getStatusIcon() + "] return book\n" +
                        "    ____________________________________________________________";

                System.out.println(output);
            } else if (input.equals("list")) {
                StringBuilder output =
                        new StringBuilder("    ____________________________________________________________\n")
                        .append("     Here are the tasks in your list:\n");

                for (int i = 1; i <= todos.size(); i++) {
                    Task theTask = todos.get(i - 1);
                    output.append("\t ").append(i).append(".[")
                            .append(theTask.getStatusIcon()).append("] ").append(theTask.description).append("\n");
                }

                output.append("    ____________________________________________________________");

                System.out.println(output);
            } else if (!input.equals("")) {
                Task newTask = new Task(input);
                todos.add(newTask);
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
