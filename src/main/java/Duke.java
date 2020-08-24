import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

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

        LocalDateTime deadline;

        public Deadline(String description, String deadline) throws DateTimeParseException {
            super(description, "D");
            this.deadline = LocalDateTime.parse(deadline,
                    DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"));
        }

        @Override
        public String toString() {
            return String.format("%s (by: %s)", super.toString(),
                    this.deadline.format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm")));
        }

    }

    public static class Event extends Task {

        LocalDateTime time;

        public Event(String description, String time) throws DateTimeParseException {
            super(description, "E");
            this.time = LocalDateTime.parse(time,
                    DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"));
        }

        @Override
        public String toString() {
            return String.format("%s (at: %s)", super.toString(),
                    this.time.format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm")));
        }

    }

    public static class DukeException extends Exception {
        public DukeException(String error) {
            super(error);
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
            try {
                Task task = null;
                StringBuilder description = new StringBuilder();
                StringBuilder time = new StringBuilder();
                String[] input = sc.nextLine().split(" ");
                switch (input[0]) {
                    case "bye":
                        System.out.println(line + "\n    Bye. Hope to see you again soon!\n" + line);
                        return;
                    case "list": {
                        int i = 0;
                        System.out.println(line + "\n    Here are the tasks in your list: ");
                        while (list.size() > i) {
                            System.out.println("        " + ++i + ". " + list.get(i - 1).toString());
                        }
                        System.out.println(line);
                        break;
                    }
                    case "done": {
                        int index = 0;
                        if (input.length == 1) {
                            throw new DukeException("Please select a task to mark as completed!");
                        }
                        try {
                            index = Integer.parseInt(input[1]);
                        } catch (NumberFormatException e) {
                            throw new DukeException("Please choose an integer value!");
                        }
                        if (index <= 0) {
                            throw new DukeException("Please choose an integer greater than 0!");
                        } else if (index > list.size()) {
                            throw new DukeException("Your task list is not that long yet!");
                        }
                        list.get(index - 1).complete();
                        System.out.println(line + "\n    Nice! I've marked this task as done:\n        "
                                + list.get(index - 1).toString() + "\n" + line);
                        break;
                    }
                    case "todo": {
                        if (input.length == 1) {
                            throw new DukeException("The description of a todo cannot be empty!");
                        }
                        for (int i = 1; i < input.length; i++) {
                            description.append(input[i]).append(" ");
                        }
                        description.deleteCharAt(description.length() - 1);
                        task = new ToDo(description.toString());
                        list.add(task);
                        System.out.println(line + "\n    Got it. I've added this task:\n        "
                                + task.toString()
                                + "\n    You now have " + list.size()
                                + (list.size() == 1 ? " task" : " tasks")
                                + " in your list.\n" + line);
                        break;
                    }
                    case "delete": {
                        int index = 0;
                        if (input.length == 1) {
                            throw new DukeException("Please select a task to mark as completed!");
                        }
                        try {
                            index = Integer.parseInt(input[1]);
                        } catch (NumberFormatException e) {
                            throw new DukeException("Please choose an integer value!");
                        }
                        if (index <= 0) {
                            throw new DukeException("Please choose an integer greater than 0!");
                        } else if (index > list.size()) {
                            throw new DukeException("Your task list is not that long yet!");
                        }
                        task = list.get(index - 1);
                        list.remove(task);
                        System.out.println(line + "\n    Noted. I've removed this task:\n        "
                                + task.toString()
                                + "\n    You now have " + list.size()
                                + (list.size() == 1 ? " task" : " tasks")
                                + " in your list.\n" + line);
                        break;
                    }
                    case "deadline": {
                        if (input.length == 1) {
                            throw new DukeException("A deadline requires a description and a time!");
                        }
                        int i = 1;
                        while (!input[i].equals("/by")) {
                            description.append(input[i++]).append(" ");
                            if (i == input.length) {
                                throw new DukeException("deadline requires the use of \"/by\"!");
                            }
                        }
                        if (description.length() == 0) {
                            throw new DukeException("The description of a deadline cannot be empty!");
                        }
                        while (++i < input.length) {
                            time.append(input[i]).append(" ");
                        }
                        if (time.length() == 0) {
                            throw new DukeException("The time of a deadline cannot be empty!");
                        }
                        description.deleteCharAt(description.length() - 1);
                        time.deleteCharAt(time.length() - 1);
                        try {
                            task = new Deadline(description.toString(), time.toString());
                            list.add(task);
                            System.out.println(line + "\n    Got it. I've added this task:\n        "
                                    + task.toString()
                                    + "\n    You now have " + list.size()
                                    + (list.size() == 1 ? " task" : " tasks")
                                    + " in your list.");
                        } catch (DateTimeParseException dtpe) {
                            System.out.println("    Error: Please use the following format instead:\n"
                                    + "        dd-MM-yyyy HHmm");
                        }
                        System.out.println(line);
                        break;
                    }
                    case "event": {
                        if (input.length == 1) {
                            throw new DukeException("An event requires a description and a time!");
                        }
                        int i = 1;
                        while (!input[i].equals("/at")) {
                            description.append(input[i++]).append(" ");
                            if (i == input.length) {
                                throw new DukeException("event requires the use of \"/at\"!");
                            }
                        }
                        if (description.length() == 0) {
                            throw new DukeException("The description of a deadline cannot be empty!");
                        }
                        while (++i < input.length) {
                            time.append(input[i]).append(" ");
                        }
                        if (time.length() == 0) {
                            throw new DukeException("The time of a deadline cannot be empty!");
                        }
                        description.deleteCharAt(description.length() - 1);
                        time.deleteCharAt(time.length() - 1);
                        try {
                            task = new Event(description.toString(), time.toString());
                            list.add(task);
                            System.out.println(line + "\n    Got it. I've added this task:\n        "
                                    + task.toString()
                                    + "\n    You now have " + list.size()
                                    + (list.size() == 1 ? " task" : " tasks")
                                    + " in your list.");
                        } catch (DateTimeParseException dtpe) {
                            System.out.println("    Error: Please use the following format instead:\n"
                                    + "        dd-MM-yyyy HHmm");
                        }
                        System.out.println(line);
                        break;
                    }
                    default:
                        throw new DukeException("I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException e) {
                System.out.println(line + "\n    Error: " + e.getMessage() + "\n" + line);
            }
        }
    }
}
