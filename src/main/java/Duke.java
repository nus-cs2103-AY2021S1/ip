import java.util.Scanner;

public class Duke {
    static Task[] tasks = new Task[100];
    static int index = 0;

    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                return;
            }
            if (input.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < 100; i++) {
                    if (tasks[i] == null) {
                        break;
                    }
                    Task task = tasks[i];
                    System.out.println((i + 1) + "." + task);
                }
            } else {
                String[] splitStr = input.split(" ");
                if (splitStr[0].equals("done")) {
                    int taskIndex = Integer.parseInt(splitStr[1]) - 1;
                    Task task = tasks[taskIndex];
                    task.markAsDone();
                    System.out.println("Nice! I've marked this task as done:\n" + task);

                } else {
                    Task task = new Task(input);
                    tasks[index] = task;
                    index++;
                    System.out.println("added: " + task);
                }
            }
        }
    }
//    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);
//    }
}
