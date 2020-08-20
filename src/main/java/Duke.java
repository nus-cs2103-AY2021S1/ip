import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        String logo = "      ____        _        \n"
                + "     |  _ \\ _   _| | _____ \n"
                + "     | | | | | | | |/ / _ \\\n"
                + "     | |_| | |_| |   <  __/\n"
                + "     |____/ \\__,_|_|\\_\\___|\n";

        String hello = "    ____________________________________________________________\n" +
                "     Hello! I'm Duke\n" + logo +
                "     What can I do for you?\n" +
                "    ____________________________________________________________\n";

        System.out.println(hello);

        Scanner sc = new Scanner(System.in);
        ArrayList<Task> task_list = new ArrayList<Task>(); //List to keep track of tasks
        int task_num = 0;

        while (sc.hasNextLine()) {
            String command = sc.nextLine();

            //Case: List -> Print out all the tasks in the list
            if (command.equals("list")) {
                String tasks = "";
                for (int i = 0; i < task_list.size(); i++) {
                    tasks += "     " + task_list.get(i).toString() + "\n";
                }
                System.out.println("    ____________________________________________________________\n" +
                        "     Here are the tasks in your list:\n" +
                        tasks +
                        "    ____________________________________________________________\n");
            }
            //Case: Blah
            else if (command.equals("blah")) {
                System.out.println("    ____________________________________________________________\n" +
                        "     blah\n" +
                        "    ____________________________________________________________\n");

            }
            //Case: Bye -> Termination of Duke Program
            else if (command.equals("bye")) {
                System.out.println("    ____________________________________________________________\n" +
                        "     Bye. Hope to see you again soon!\n" +
                        "    ____________________________________________________________\n");
                break;
            }

            else if (command.split(" ")[0].equals("done")) {
                Task temp = task_list.get(Integer.parseInt(command.split(" ")[1]) - 1);
                temp.completeTask();
                System.out.println("    ____________________________________________________________\n" +
                        "     Nice! I've marked this task as done: \n" +
                        "       " + temp + "\n" +
                        "    ____________________________________________________________");

            }
            //Case: Any other Command -> Add task to the task_list
            else {
                task_num++;
                task_list.add(new Task(task_num, command, false));
                System.out.println(String.format("    ____________________________________________________________\n" +
                        "     added: %s\n" +
                        "    ____________________________________________________________\n", command));
            }
        }
    }
}

