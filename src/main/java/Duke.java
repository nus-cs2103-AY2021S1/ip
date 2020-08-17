import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    public static abstract class Task {
        protected String description;
        protected String tag;
        protected boolean isDone;

        protected Task(String description, String tag) {
            this.description = description;
            this.tag = tag;
            this.isDone = false;
        }

        public String getStatusIcon() {
            return isDone ? "O" : "X";
        }

        public void complete() {
            this.isDone = true;
        }

        public String getDescription() {
            return this.description;
        }

        @Override
        public String toString() {
            return String.format("[%s][%s] %s", this.tag, this.getStatusIcon(), this.description);
        }
    }

    public static class ToDo extends Task {

        public ToDo(String description) {
            super(description, "T");
        }

    }

    public static class Deadline extends Task {

        String deadline;

        public Deadline(String description, String deadline) {
            super(description, "D");
            this.deadline = deadline;
        }

        @Override
        public String toString() {
            return String.format("%s (by: %s)", super.toString(), this.deadline);
        }

    }

    public static class Event extends Task {

        String time;

        public Event(String description, String time) {
            super(description, "E");
            this.time = time;
        }

        @Override
        public String toString() {
            return String.format("%s (at: %s)", super.toString(), this.time);
        }

    }

    public static void main(String[] args) {
        String logo
                = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        Scanner sc = new Scanner(System.in);
        String line = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";
        System.out.println(line + "\n" + logo + "\n    Hello! I'm Duke!"
                + "\n    What can I do for you?"
                + "\n    Awaiting input...\n" + line);
        var list = new ArrayList<Task>();
        while (true) {
            String[] input = sc.nextLine().split(" ");
            if (input.length == 1) {
                if (input[0].equals("bye")) {
                    System.out.println(line + "\n    Bye. Hope to see you again soon!\n" + line);
                    return;
                } else if (input[0].equals("list")) {
                    int i = 0;
                    System.out.println(line + "\n    Here are the tasks in your list: ");
                    while (list.size() > i) {
                        System.out.println("        " + ++i + ". " + list.get(i - 1).toString());
                    }
                    System.out.println(line);
                }
            } else {
                if (input[0].equals("done")) {
                    int index = Integer.parseInt(input[1]) - 1;
                    list.get(index).complete();
                    System.out.println(line + "\n    Nice! I've marked this task as done:\n        "
                            + list.get(index).toString() + "\n" + line);
                } else {
                    Task task = null;
                    StringBuilder description = new StringBuilder();
                    StringBuilder time = new StringBuilder();
                    if (input[0].equals("todo")) {
                        for (int i = 1; i < input.length; i++) {
                            description.append(input[i]).append(" ");
                        }
                        description.deleteCharAt(description.length() - 1);
                        task = new ToDo(description.toString());
                    } else if (input[0].equals("deadline")) {
                        int i = 1;
                        while (!input[i].equals("/by")) {
                            description.append(input[i++]).append(" ");
                        }
                        while (++i < input.length) {
                            time.append(input[i]).append(" ");
                        }
                        description.deleteCharAt(description.length() - 1);
                        time.deleteCharAt(time.length() - 1);
                        task = new Deadline(description.toString(), time.toString());
                    } else if (input[0].equals("event")) {
                        int i = 1;
                        while (!input[i].equals("/at")) {
                            description.append(input[i++]).append(" ");
                        }
                        while (++i < input.length) {
                            time.append(input[i]).append(" ");
                        }
                        description.deleteCharAt(description.length() - 1);
                        time.deleteCharAt(time.length() - 1);
                        task = new Event(description.toString(), time.toString());
                    }
                    list.add(task);
                    System.out.println(line + "\n    Got it. I've added this task:\n      "
                            + task.toString()
                            + "\n    You now have " + list.size()
                            + (list.size() == 1 ? " task" : " tasks")
                            + " in your list.\n" + line);
                }
            }
        }
    }
}
