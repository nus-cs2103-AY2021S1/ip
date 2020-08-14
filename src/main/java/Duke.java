import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static final String divider =
            "\t_____________________________________________\n";
    private static final ArrayList<Task> tasks = new ArrayList<>();

    private static void greetings() {
        String logo = "\n"
                + "\t███████╗ ██████╗  ██████╗██╗   ██╗███████╗   \n"
                + "\t██╔════╝██╔═══██╗██╔════╝██║   ██║██╔════╝   \n"
                + "\t█████╗  ██║   ██║██║     ██║   ██║███████╗   \n"
                + "\t██╔══╝  ██║   ██║██║     ██║   ██║╚════██║   \n"
                + "\t██║     ╚██████╔╝╚██████╗╚██████╔╝███████║██╗\n"
                + "\t╚═╝      ╚═════╝  ╚═════╝ ╚═════╝ ╚══════╝╚═╝\n";
        System.out.println("Welcome to " + logo);
        String greetings = divider
                + "\t(｡･ω･)ﾉﾞ I am Pocus, your personal assistant!"
                + "\n\tBefore we start, may I know your name?\n"
                + divider;
        System.out.print(greetings);
    }

    private static void addressUser(String name) {
        String address = divider
                + "\tHi there, " + name + "!"
                + "\n\tHow can I help you today?\n"
                + divider;
        System.out.print(address);
    }

    private static void listTasks() {
        System.out.print(divider);
        System.out.print("\tHere are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            int number = i + 1;
            System.out.print("\t" + number + ". " + tasks.get(i) + "\n");
        }
        System.out.print(divider);
    }

    private static void markTaskDone(String input) {
        int index = Integer.parseInt(input.substring(5));
        Task finishedTask = tasks.get(index - 1);
        finishedTask.markAsDone();
        String doneTask = divider
                + "\tGood job! I've marked this task as done:\n\t\t"
                + finishedTask + "\n\tKeep going!\n" + divider;
        System.out.print(doneTask);
    }

    private static void exitFocus() {
        String exit = divider
                + "\tヾ(｡･ω･｡) Hopefully I helped you today. Bye!\n"
                + divider;
        System.out.print(exit);
    }

    private static void addToDo(String input) {
        String description = input.substring(5);
        Task toDo = new ToDo(description);
        tasks.add(toDo);
        String printing = divider
                + "\tGot it. I've added this task:\n\t\t"
                + toDo + "\n\t" + "Now you have "
                + tasks.size() + " tasks in the list.\n"
                + divider;
        System.out.print(printing);
    }

    private static void addDeadline(String input) {
        int end = input.indexOf("/");
        String description = input.substring(9, end);
        String by = input.substring(end + 4);
        Task deadline = new Deadline(description, by);
        tasks.add(deadline);
        String printing = divider
                + "\tGot it. I've added this task:\n\t\t"
                + deadline + "\n\t" + "Now you have "
                + tasks.size() + " tasks in the list.\n"
                + divider;
        System.out.print(printing);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        greetings();
        String name = sc.nextLine();
        addressUser(name);

        String input = "";
        while (sc.hasNext()) {
            input = sc.nextLine();
            if (input.contains("todo")) {
                addToDo(input);
            } else if (input.contains("deadline")) {
                addDeadline(input);
            } else if (input.contains("done")) { // done tasks
                markTaskDone(input);
            } else if (input.equals("list")) { // list tasks
                listTasks();
            } else if (input.equals("bye")) { // exit bot
                exitFocus();
                break;
            }
        }
        sc.close();
    }
}