import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private final static String divider =
            "\t_____________________________________________\n";

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

    private static void listTasks(ArrayList<Task> tasks) {
        System.out.print(divider);
        for (int i = 0; i < tasks.size(); i++) {
            int number = i + 1;
            System.out.print("\t" + number + ": " + tasks.get(i) + "\n");
        }
        System.out.print(divider);
    }

    private static void markTaskDone() {

    }

    private static void exitFocus() {
        String exit = divider
                + "\tヾ(｡･ω･｡) Hopefully I helped you today. Bye!\n"
                + divider;
        System.out.print(exit);
    }

    private static void printTask(String taskName) {
        String task = divider + "\t added: " + taskName + "\n" + divider;
        System.out.print(task);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        greetings();
        String name = sc.nextLine();
        addressUser(name);

        ArrayList<Task> tasks = new ArrayList<>();
        String input = "";
        while (sc.hasNext()) {
            input = sc.nextLine();
            if (input.equals("list")) { // list tasks
                listTasks(tasks);
            } else if (input.contains("done")) { // done tasks
                markTaskDone();
            } else if (input.equals("bye")) { // say bye
                exitFocus();
                break;
            } else { // add tasks
                Task task = new Task(input);
                tasks.add(task);
                printTask(input);
            }
        }
        sc.close();
    }
}