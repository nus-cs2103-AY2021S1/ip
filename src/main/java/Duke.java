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

        String startLine = "    ____________________________________________________________\n";
        String endLine = "    ____________________________________________________________";

        String greeting =  startLine +
                "    Hello! I'm Duke\n" +
                "    What can I do for you?\n" +
                endLine;
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
                String output = startLine +
                        "     Nice! I've marked this task as done: \n" +
                        "       " + toChange + "\n" +
                        endLine;

                System.out.println(output);
            } else if (input.equals("list")) {
                StringBuilder output = new StringBuilder(startLine).append("     Here are the tasks in your list:\n");

                for (int i = 1; i <= todos.size(); i++) {
                    Task theTask = todos.get(i - 1);
                    output.append("\t ").append(i).append(".").append(theTask).append("\n");
                }

                output.append(endLine);

                System.out.println(output);
            } else if (!input.equals("")) {
                Task newTask = null;
                String desc;

                if (input.startsWith("todo")) {
                    desc = input.substring(5);
                    newTask = new Todo(desc);
                } else if (input.startsWith("deadline")) {
                    String[] components = input.split(" /by ");
                    desc = components[0].substring(9);
                    newTask = new Deadline(desc, components[1]);
                } else if (input.startsWith("event")) {
                    String[] components = input.split(" /at ");
                    desc = components[0].substring(6);
                    newTask = new Event(desc, components[1]);
                }

                todos.add(newTask);

                String output = startLine +
                        "     Got it. I've added this task: \n" +
                        "       " + newTask + "\n" +
                        "     Now you have " + todos.size() + " tasks in the list.\n" +
                        endLine;

                System.out.println(output);
            }
        }

        String byeMessage = startLine +
                "     Bye. Hope to see you again soon!\n" +
                endLine;
        System.out.println(byeMessage);
    }
}
