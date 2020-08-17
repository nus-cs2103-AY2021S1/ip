import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner inputs = new Scanner(System.in);
        boolean run = true;
        int noOfTasks = 0;
        Task[] tasks = new Task[100];

        System.out.println("Hello~, I'm Duke!\n"
                + "What can I do for you?");

        while (run == true) {
            String next = inputs.next();
            System.out.println("____________________________________________________________");
            if (next.equals("bye")) {
                System.out.println("Goodbye~ Hope to see you again soon!");
                run = false;

            } else if (next.equals("list")) {
                for (int i = 0; i < noOfTasks; i++) {
                    System.out.println(i + 1 + ". " + tasks[i]);
                }
            } else if (next.equals("done")) {
                int doneTask = Integer.parseInt(inputs.next()) - 1;
                tasks[doneTask].setDone();
                System.out.println("Nice! I've set this task as done~\n" +
                        tasks[doneTask]);
            } else if (next.equals("todo")) {
                Todo newTask = new Todo(inputs.nextLine());
                tasks[noOfTasks] = newTask;
                noOfTasks += 1;
                System.out.println("Got it~ I've added this task:\n"
                        + newTask + "\n" + "You now have " + noOfTasks + " tasks in the list~");
            } else if (next.equals("deadline")) {
                String[] deadlineSplit = inputs.nextLine().split("/by");
                Deadline newTask = new Deadline(deadlineSplit[0], deadlineSplit[1]);
                tasks[noOfTasks] = newTask;
                noOfTasks += 1;
                System.out.println("Got it~ I've added this task:\n"
                        + newTask + "\n" + "You now have " + noOfTasks + " tasks in the list~");
            } else if (next.equals("event")) {
                String[] eventSplit = inputs.nextLine().split("/at");
                Event newTask = new Event(eventSplit[0], eventSplit[1]);
                tasks[noOfTasks] = newTask;
                noOfTasks += 1;
                System.out.println("Got it~ I've added this task:\n"
                        + newTask + "\n" + "You now have " + noOfTasks + " tasks in the list~");
            }

//            } else {
//                Task newTask = new Task(next, noOfTasks + 1);
//                tasks[noOfTasks] = newTask;
//                noOfTasks += 1;
//                System.out.println("Added: " + next);
//            }
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