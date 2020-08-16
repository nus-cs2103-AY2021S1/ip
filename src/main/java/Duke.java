import Exception.DukeException;
import Exception.InvalidCommandException;
import Task.DeadlineTask;
import Task.EventTask;
import Task.Task;
import Task.ToDoTask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.function.BiConsumer;

public class Duke {
    public static void main(String[] args) {
        introduction();
        Scanner sc  = new Scanner(System.in);
        interact(sc);
        sc.close();
    }

    public static void introduction() {
        String greeting = "    ____________________________________________________________\n"
                + "     Hello! I'm Duke\n"
                + "     What can I do for you?\n"
                + "    ____________________________________________________________\n";
        System.out.println(greeting);
    }

    public static HashMap<String, BiConsumer<String, List<Task>>> setUp() {
        HashMap<String, BiConsumer<String, List<Task>>> map = new HashMap<>();

        map.put("list", (command, list) -> listCommand(list));
        map.put("done", (command, list) -> doneCommand(list, command));
        map.put("todo", (command, list) -> toDoCommand(list, command));
        map.put("deadline", (command, list) -> deadlineCommand(list, command));
        map.put("event", (command, list) -> eventCommand(list, command));
        return map;
    }

    public static void interact(Scanner sc) {
        HashMap<String, BiConsumer<String, List<Task>>> map = setUp();
        String command = sc.nextLine();
        List<Task> list = new ArrayList<>();

        while (!command.equals("bye")) {
            BiConsumer<String, List<Task>> action =  map.get(command.replaceAll(" .*", ""));

            try {
                if (action == null) {
                    throw new InvalidCommandException();
                } else {
                    action.accept(command, list);
                }
            } catch (DukeException e){
                System.out.println(e);
            }
            command = sc.nextLine();
        }

        System.out.println("    ____________________________________________________________\n"
                + "     Bye. Hope to see you again soon!\n"
                + "    ____________________________________________________________\n"
        );
    }

    public static void addCommand(List<Task> list, String command) {
        list.add(new Task(command));
        System.out.println("    ____________________________________________________________\n"
                + "     added: "
                + command
                + "\n"
                + "    ____________________________________________________________\n"
        );
    }

    public static void listCommand(List<Task> list) {
        System.out.print("    ____________________________________________________________\n");
        System.out.print("     Here are the tasks in your list:\n");
        for(int i = 1; i <= list.size(); i++) {
            System.out.println("     " + i + "." + list.get(i - 1));
        }
        System.out.println("    ____________________________________________________________\n");
    }

    public static void doneCommand(List<Task> list, String command) {
        int index = Integer.parseInt(command.substring(5));
        list.get(index - 1).markAsDone();
        System.out.println("    ____________________________________________________________\n"
                + "     Nice! I've marked this task as done:\n"
                + "     "
                + list.get(index - 1)
                + "\n"
                + "    ____________________________________________________________\n"
        );
    }

    public static void toDoCommand(List<Task> list, String command) {
        int spaceIndex = command.indexOf(" ");
        Task task = new ToDoTask(command.substring(spaceIndex + 1));
        list.add(task);

        System.out.println("    ____________________________________________________________\n"
                + "     Got it. I've added this task:\n"
                + "     "
                + task
                + "\n"
                + "     Now you have "
                + list.size()
                + " task(s) in the list.\n"
                + "    ____________________________________________________________\n"
        );
    }

    public static void deadlineCommand(List<Task> list, String command) {
        int spaceIndex = command.indexOf(" ");
        int slashIndex = command.indexOf("/");
        String description = command.substring(spaceIndex + 1, slashIndex - 1);
        String time = command.substring(slashIndex + 4);

        Task task = new DeadlineTask(description, time);
        list.add(task);

        System.out.println("    ____________________________________________________________\n"
                + "     Got it. I've added this task:\n"
                + "     "
                + task
                + "\n"
                + "     Now you have "
                + list.size()
                + " task(s) in the list.\n"
                + "    ____________________________________________________________\n"
        );
    }

    public static void eventCommand(List<Task> list, String command) {
        int spaceIndex = command.indexOf(" ");
        int slashIndex = command.indexOf("/");
        String description = command.substring(spaceIndex + 1, slashIndex - 1);
        String time = command.substring(slashIndex + 4);

        Task task = new EventTask(description, time);
        list.add(task);

        System.out.println("    ____________________________________________________________\n"
                + "     Got it. I've added this task:\n"
                + "     "
                + task
                + "\n"
                + "     Now you have "
                + list.size()
                + " task(s) in the list.\n"
                + "    ____________________________________________________________\n"
        );
    }
}
