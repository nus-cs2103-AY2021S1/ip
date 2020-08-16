import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner inputs = new Scanner(System.in);
        boolean run = true;
        int noOfTasks = 0;
        String[] tasks = new String[100];

        System.out.println("Hello~, I'm Duke!\n"
        + "What can I do for you?");

        while (run == true) {
            String next = inputs.nextLine();
            System.out.println("____________________________________________________________");
            if (next.equals("bye")) {
                System.out.println("Goodbye~ Hope to see you again soon!");
                run = false;
            } else if (next.equals("list")) {
                for (int i = 0; i < noOfTasks; i++) {
                    int taskNo = i + 1;
                    System.out.println(taskNo + ". " + tasks[i]);
                }
            } else {
                tasks[noOfTasks] = next;
                noOfTasks += 1;
                System.out.println("Added: " + next);
            }
            System.out.println("____________________________________________________________");
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
