import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    public static ArrayList<Task> taskList = new ArrayList<>();

    public static class Task {
        protected String description;
        protected boolean isDone;

        public Task(String description) {
            this.description = description;
            this.isDone = false;
        }

        public String getStatusIcon() {
            return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
        }

        public void markAsDone() {
            isDone = true;
        }

        @Override
        public String toString() {
            return "[" + getStatusIcon() + "] " + description;
        }
    }

    public static void readUserInput() {
        Scanner sc = new Scanner(System.in);
        String display = sc.nextLine();

        if (display.equals("bye")) {
            System.out.println("Bye. Hope to see you again soon!");

        } else if (display.equals("list")) {
            for (int i = 0; i < taskList.size(); i++) {
                if (i == 0) {
                    System.out.println("Here are the tasks in your list:\n");
                }
                Task task = taskList.get(i);
                System.out.println((i + 1) + ". " + task + "\n");
            }
            readUserInput();

        } else if (display.substring(0, 4).equals("done")) {
            int index = Integer.parseInt(display.substring(5, display.length()));
            Task task = taskList.get(index - 1);
            task.markAsDone();
            System.out.println("Nice! I've marked this task as done:\n" + task);
            readUserInput();

        } else {
            System.out.println("added: " + display);
            Task task = new Task(display);
            taskList.add(task);
            readUserInput();
        }
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String greeting = "Hello! I'm Duke \n" + "What can I do for you?";
        System.out.println("Hello from\n" + logo + "\n" + greeting);
        readUserInput();
    }
}
