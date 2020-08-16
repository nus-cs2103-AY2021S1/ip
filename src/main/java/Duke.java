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
            } else if (cmd.equals("todo")) {
                System.out.println("what is the name of the todo: ");
                String todoName = sc.nextLine();
                tasks.add(new Todo(todoName));
                System.out.println("*added: " + todoName);
                System.out.println(line);
            } else if (cmd.equals("deadline")) {
                System.out.println("what is the name of the task: ");
                String deadlineName = sc.nextLine();
                System.out.println("when is the due date: ");
                String dueDate = sc.nextLine();
                tasks.add(new Deadline(deadlineName, dueDate));
                System.out.println("*added: " + deadlineName);
                System.out.println(line);
            } else if (cmd.equals("event")){
                System.out.println("what is the name of the event: ");
                String eventName = sc.nextLine();
                System.out.println("when does the event start: ");
                String start = sc.nextLine();
                System.out.println("when does the event end: ");
                String end = sc.nextLine();
                tasks.add(new Event(eventName, start, end));
                System.out.println("*added: " + eventName);
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
        System.out.println(String.format("You have a total of %d tasks",  i - 1));
    }
}
