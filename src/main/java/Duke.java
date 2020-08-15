import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke\n" +
                "     What can I do for you?");
        System.out.println("________________________________________");

        Scanner sc = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();
        while (true) {
            String s = sc.nextLine();
            if (s.equals("bye")) {
                System.out.println("________________________________________");
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("________________________________________");
                System.exit(0);
            } else if (s.equals("list")) {
                System.out.println("________________________________________");
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println((i + 1) + "." + tasks.get(i).toString());
                }
                System.out.println("________________________________________");
            } else if (s.contains("done")) {
                try {
                    int index = Integer.parseInt(s.substring(5)) - 1;
                    if (index < tasks.size()) {
                        System.out.println("________________________________________");
                        System.out.println("Nice! I've marked this task as done: ");
                        tasks.get(index).markAsDone();
                        System.out.println(tasks.get(index).toString());
                    } else {
                        System.out.println("Integer should have a corresponding task in the list.");
                    }
                } catch (Exception e) {
                    System.out.println("________________________________________");
                    System.out.println("Please key in this format: done <integer>");
                } finally {
                    System.out.println("________________________________________");
                }
            } else {
                System.out.println("________________________________________");
                Task temp = new Task(s);
                tasks.add(temp);
                System.out.println("Yay! New task added: ");
                System.out.println("added: " + temp.toString());
                System.out.println("________________________________________");
            }
        }

    }
}
