import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

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

    public static void interact(Scanner sc) {
        String command = sc.nextLine();
        List<Task> list = new ArrayList<>();

        while (!command.equals("bye")) {
            if (command.equals("list")) {
                listCommand(list);
            } else if (command.substring(0, 5).equals("done ")) {
                doneCommand(list, command);
            } else {
                addCommand(list, command);
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
}
