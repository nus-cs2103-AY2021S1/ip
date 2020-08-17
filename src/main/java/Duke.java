import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static ArrayList<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        greet();
        readTasks();
    }

    private static void greet() {
        String logo =
                " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.print("Hello, I am\n" + logo);
        System.out.println("___________________________________________________" +
            "\nDuke: What can i do for you?");
    }

    private static void printTasks() {
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            System.out.println(i + 1 + ". " + task.getStatusIcon() + " " + task);
        }
        System.out.println("--------------------------" +
                "-------------------------");
    }

    private static void setDoneTask(int index) {
        Task task = tasks.get(index - 1);
        task.markDone();
        System.out.println("Nice! I've marked this task as done: " +
                task.getStatusIcon() + " " + task +
                "\n---------------------------------------------------");
    }

    private static void readTasks() {
        Scanner sc = new Scanner(System.in);
        String reply = "Duke: Added - " + "%s" + "\n--------------------------" +
                "-------------------------";
        while (sc.hasNext()) {
            String input = sc.nextLine();
            Task newTask = new Task(input);
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                sc.close();
                break;
            } else if (input.equals("list")) {
                printTasks();
            } else if (input.split(" ")[0].equals("done")) {
                setDoneTask(Integer.parseInt(input.split(" ")[1]));
            } else {
                tasks.add(newTask);
                System.out.println(String.format(reply, newTask));
            }
        }
    }
}
