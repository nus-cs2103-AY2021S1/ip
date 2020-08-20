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

    public static void reponse(String input) {
        if (input.equals("bye")) {
            System.out.println(horizontal_line);
            System.out.println("Bye. Hope to see you again soon!");
            System.out.println(horizontal_line);
        } else if (input.equals("list")) {
            System.out.println(horizontal_line);
            for (int i = 0; i < taskList.size(); i++) {
                System.out.println(String.format("%d. %s", i+1, taskList.get(i).description));
            }
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
            reponse(input);
            input = sc.nextLine();
        }

        reponse(input);
    }
}
