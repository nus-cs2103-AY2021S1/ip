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
        ArrayList<? extends Task> task_list = new ArrayList<>(); //List to keep track of tasks

        while (sc.hasNextLine()) {
            String command = sc.nextLine();

            //Case: List -> Print out all the tasks in the list
            if (command.equals("list")) {
                String tasks = "";
                for (int i = 0; i < task_list.size(); i++) {
                    tasks += String.format("     %d."  + task_list.get(i) + "\n", i + 1);
                }
                System.out.println("    ____________________________________________________________\n" +
                        "     Here are the tasks in your list:\n" +
                        tasks +
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

            //Case: ToDo, Deadline, Event -> Add particular task to the task_list
            else {
                Duke.TaskIdentifier(command, task_list);
            }
        }
    }

    public static void TaskIdentifier(String command, ArrayList task_list) {
        String task_type = command.split(" ")[0];
        String task_details = command.split(task_type + " ")[1]; //Includes task description and date/time if applicable
        String task_info = task_details.split(" /")[0];

        switch(task_type) {
            case "todo":
                Task todo = new ToDo(task_info);
                task_list.add(todo);
                break;

            case "deadline":
                String date = task_details.split(" /")[1];
                Task deadline = new Deadline(task_info, date);
                task_list.add(deadline);
                break;

            case "event":
                String time = task_details.split(" /")[1];
                Task event = new Event(task_info, time);
                task_list.add(event);
                break;

            default:
                break;
        }

        System.out.println(
                String.format("    ____________________________________________________________\n" +
                "     Got it. I've added this task: \n" +
                "       %s\n" +
                "     Now you have %d tasks in the list.\n" +
                "    ____________________________________________________________\n", task_list.get(task_list.size() - 1).toString(), task_list.size()));
    }
}

