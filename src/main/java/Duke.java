import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static List<Task> taskList = new ArrayList<>();

    public static String horizontal_line = "____________________________________________________________";

    public static void greeting() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println(horizontal_line);
        System.out.println("Hello from\n" + logo);
        System.out.println(horizontal_line);
    }

    public static void showList() {
        System.out.println(horizontal_line);
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            System.out.println(String.format("%d.%s", i+1, task));
        }
        System.out.println(horizontal_line);
    }

    public static void response(String input) {
        if (input.equals("bye")) {
            System.out.println(horizontal_line);
            System.out.println("Bye. Hope to see you again soon!");
            System.out.println(horizontal_line);
        } else if (input.equals("list")) {
            showList();
        } else if (input.split(" ").length == 2 && input.split(" ")[0].equals("done")){
            int doneIndex = Integer.parseInt(input.split(" ")[1]);
            Task task = taskList.get(doneIndex - 1);
            task.markAsDone();
            System.out.println(horizontal_line);
            System.out.println("Nice! I've marked this task as done: ");
            System.out.println(task);
            System.out.println(horizontal_line);
        } else {
            taskList.add(new Task(input));
            System.out.println(horizontal_line);
            System.out.println("added: "+ input);
            System.out.println(horizontal_line);
        }
    }

    public static void main(String[] args) {
        greeting();

        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        while (!input.equals("bye")) {
            response(input);
            input = sc.nextLine();
        }
        response(input);
    }
}
