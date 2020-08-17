import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    public static class Task {
        protected String description;
        protected boolean isDone;

        public Task(String description) {
            this.description = description;
            this.isDone = false;
        }

        public String getStatusIcon() {
            return isDone ? "\u2713" : "\u2718";
        }

        public void complete() {
            this.isDone = true;
        }

        public String getDescription() {
            return this.description;
        }

        @Override
        public String toString() {
            return String.format("[%s] %s", this.getStatusIcon(), this.getDescription());
        }
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        Scanner sc = new Scanner(System.in);
        String line = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";
        System.out.println(line + "\n" + logo + "\n    Hello! I'm Duke\n    What can I do for you?" +
                "\n    Awaiting input...\n" + line);
        var list = new ArrayList<Task>();
        while (true) {
            String input[] = sc.nextLine().split(" ");
            if (input[0].equals("bye")) {
                System.out.println(line + "\n    Bye. Hope to see you again soon!\n" + line);
                return;
            } else if (input[0].equals("list")) {
                int i = 0;
                System.out.println(line);
                while (list.size() > i) {
                    System.out.println("    " + ++i + ". " + list.get(i - 1).toString());
                }
                System.out.println( line);
            } else if (input[0].equals("done")) {
                int index = Integer.parseInt(input[1]) - 1;
                list.get(index).complete();
                System.out.println(line + "\n    Nice! I've marked this task as done:\n        "
                        + list.get(index).toString() + "\n" + line);
            } else {
                Task task = new Task(String.join(" ", input));
                list.add(task);
                System.out.println(line + "\n    added: " + task.getDescription() + "\n" + line);
            }
        }
    }
}
