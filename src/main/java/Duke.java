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
        Boolean bye = false;

        while (sc.hasNextLine() && !bye) {
            String command = sc.nextLine();
                try {
                    Duke.TaskIdentifier(command, task_list);
                }

                catch (DukeInvalidCommandException e) {
                    System.out.println(e.toString());
                }

                catch (DukeIncompleteCommandException e) {
                    System.out.println(e.toString());
                }
            }
        }

    public static void TaskIdentifier(String command, ArrayList<Task> task_list) throws DukeInvalidCommandException, DukeIncompleteCommandException{
            String task_type = command.split(" ")[0];
            String task_details = "";
            String task_info = "";

            if (!task_type.equals("todo") && !task_type.equals("event") && !task_type.equals("deadline")
                    && !task_type.equals("list") && !task_type.equals("bye") && !task_type.equals("done")
                    && !task_type.equals("delete")) {
                throw new DukeInvalidCommandException("    ____________________________________________________________\n" +
                        "     ☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n" +
                        "    ____________________________________________________________");
            }

            try {

                if (!task_type.equals("list") && !task_type.equals("bye")) {
                    task_details = command.split(task_type + " ")[1]; //Includes task description and date/time if applicable
                    task_info = task_details.split(" /")[0];
                }

                switch (task_type) {

                    case "list":
                        String tasks = "";
                        for (int i = 0; i < task_list.size(); i++) {
                            tasks += String.format("     %d." + task_list.get(i) + "\n", i + 1);
                        }
                        System.out.println("    ____________________________________________________________\n" +
                                "     Here are the tasks in your list:\n" +
                                tasks +
                                "    ____________________________________________________________\n");
                        break;

                    case "bye":
                        System.out.println("    ____________________________________________________________\n" +
                                "     Bye. Hope to see you again soon!\n" +
                                "    ____________________________________________________________\n");
                        System.exit(0);
                        break;

                    case "todo":
                        Task todo = new ToDo(task_info);
                        task_list.add(todo);
                        PrintTask(task_list, todo);
                        break;

                    case "deadline":
                        String date = task_details.split(" /")[1];
                        Task deadline = new Deadline(task_info, date);
                        task_list.add(deadline);
                        PrintTask(task_list, deadline);
                        break;

                    case "event":
                        String time = task_details.split(" /")[1];
                        Task event = new Event(task_info, time);
                        task_list.add(event);
                        PrintTask(task_list, event);
                        break;

                    case "done":
                        Task temp = task_list.get(Integer.parseInt(task_details) - 1);
                        temp.completeTask();
                        System.out.println("    ____________________________________________________________\n" +
                                "     Nice! I've marked this task as done: \n" +
                                "       " + temp + "\n" +
                                "    ____________________________________________________________");
                        break;

                    case "delete":
                        Task temp2 = task_list.remove(Integer.parseInt(task_details) - 1);
                        System.out.println(
                                String.format("    ____________________________________________________________\n" +
                                "     Noted. I've removed this task: \n" +
                                "       %s\n" +
                                "     Now you have %d tasks in the list.\n" +
                                "    ____________________________________________________________", temp2.toString(), task_list.size()));

                    default:
                        break;
                }
            }
            catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeIncompleteCommandException(String.format(
                        "    ____________________________________________________________\n" +
                        "     ☹ OOPS!!! The description of a %s cannot be empty.\n" +
                        "    ____________________________________________________________\n", task_type));
            }
    }


    public static void PrintTask(ArrayList<Task> task_list, Task t) {
        System.out.println(
                String.format("    ____________________________________________________________\n" +
                        "     Got it. I've added this task: \n" +
                        "       %s\n" +
                        "     Now you have %d tasks in the list.\n" +
                        "    ____________________________________________________________\n", t.toString(), task_list.size()));

    }
}

