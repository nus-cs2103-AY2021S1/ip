import java.util.Scanner;
import java.util.ArrayList;

public class Dobby {
    public static void main(String[] args) {
        /* String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);*/
        String underscore_line = "    __________________________________________";
        System.out.println(underscore_line);
        System.out.println("    Hello! I'm Dobby\n    How can I help you?");
        System.out.println(underscore_line);
        ArrayList<Task> tasks = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNext()) {
            String text = scanner.nextLine();
            if (text.equalsIgnoreCase("bye")) {
                scanner.close();
                System.out.println(underscore_line);
                System.out.println("    Goodbye. Hope to see you again soon!");
                System.out.println(underscore_line);
                System.exit(0);
            } else if (text.equalsIgnoreCase("list")) {
                int i = 0;
                System.out.println(underscore_line);
                for (Task task : tasks) {
                    i++;
                    String description = task.getDescription();
                    System.out.println("    " + i + ". " + description);
                }
                System.out.println(underscore_line);
            } else if (text.startsWith("done")) {
                int index = Integer.parseInt(text.substring(5));
                Task task = tasks.get(index - 1);
                task.setDone();
                System.out.println(underscore_line);
                System.out.println("    Great! I've marked this task as done:\n");
                System.out.println("        " + task.getDescription());
                System.out.println(underscore_line);
            } else {
                Task task = new Task(text, false);
                tasks.add(task);
                System.out.println(underscore_line);
                System.out.println("    Added: " + text );
                System.out.println(underscore_line);
            }
        }
    }
}

