import java.util.*;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        String line = "------------------------";

        System.out.println(line);
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke \nWhat can I do for you?");

        Scanner sc = new Scanner(System.in);
        List<Task> tasks = new ArrayList<>();
        
        while(true) {
            System.out.print("your input: ");
            String cmd = sc.nextLine();
            System.out.println("\n" + line);
            if (cmd.equals("bye")) {
                end();
                System.out.println(line);
                break;
            } else if (cmd.equals("list")) {
                list(tasks);
                System.out.println(line);
            } else if (cmd.equals("complete")) {
                System.out.print("which task should we mark as complete?: ");
                int taskNum = Integer.parseInt(sc.nextLine());
                tasks.get(taskNum - 1).markDone();
                System.out.println(line);
            } else {
                System.out.println("*added: " + cmd);
                tasks.add(new Task(cmd));
                System.out.println(line);
            }
        }
    }

    private static void end() {
         System.out.println("Bye. Hope to see you again soon!");       
    }

    private static void list(List<Task> tasks) {
        int i = 1;
        System.out.println("*Here are all your tasks");
        for (Task task: tasks) {
            System.out.println(i + ". " + task);
            i++;
        }
    }
}
