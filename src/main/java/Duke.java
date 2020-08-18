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

    public static class Deadline extends Task {

        protected String by;

        public Deadline(String description, String by) {
            super(description);
            this.by = by;
        }

        @Override
        public String toString() {
            return "[D]" + super.toString() + "(by:" + by + ")";
        }
    }

    public static class Event extends Task {
        protected String at;

        public Event(String description, String at) {
            super(description);
            this.at = at;
        }

        @Override
        public String toString() {
            return "[E]" + super.toString() + "(at:" + at + ")";
        }
    }

    public static class Todo extends Task {

        public Todo(String description) {
            super(description);
        }

        @Override
        public String toString() {
            return "[T]" + super.toString();
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

        } else if (display.startsWith("done")) {
            int index = Integer.parseInt(display.substring(5));
            Task task = taskList.get(index - 1);
            task.markAsDone();
            System.out.println("Nice! I've marked this task as done:\n" + task);
            readUserInput();

        } else if (display.startsWith("todo")) {
            String description = display.substring(5);
            Todo todo = new Todo(description);
            taskList.add(todo);
            System.out.println("Got it. I've added this task:\n" + todo + "\n"
                    + "Now you have " + taskList.size() + " tasks in the list.");
            readUserInput();

        } else if (display.startsWith("deadline")) {
            int index = display.indexOf("/");
            String description = display.substring(9, index);
            String deadlineDate = display.substring(index + 3);
            Deadline deadline = new Deadline(description, deadlineDate);
            taskList.add(deadline);
            System.out.println("Got it. I've added this task:\n" + deadline + "\n"
                    + "Now you have " + taskList.size() + " tasks in the list.");
            readUserInput();

        } else if (display.startsWith("event")) {
            int index = display.indexOf("/");
            String description = display.substring(6, index);
            String eventDate = display.substring(index + 3);
            Event event = new Event(description, eventDate);
            taskList.add(event);
            System.out.println("Got it. I've added this task:\n" + event + "\n"
                    + "Now you have " + taskList.size() + " tasks in the list.");
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
