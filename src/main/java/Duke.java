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

    private static void markTaskDone(int index) {
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

    private static void addTask(String taskName) {
        Task task = new Task(taskName);
        tasks.add(task);
        String printing = divider + "\t added: "
                + taskName + "\n" + divider;
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
            if (input.equals("list")) { // list tasks
                listTasks();
            } else if (input.contains("done")) { // done tasks
                int index = Integer.parseInt(input.substring(5));
                markTaskDone(index);
            } else if (input.equals("bye")) { // exit bot
                exitFocus();
                break;
            } else { // add tasks
                addTask(input);
            }
        }
        sc.close();
    }
}