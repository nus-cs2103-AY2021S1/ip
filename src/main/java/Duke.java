import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Duke {

    public static abstract class Task {
        protected String description;
        protected String tag;
        protected boolean isDone;

        protected Task(String description, String tag, boolean isDone) {
            this.description = description;
            this.tag = tag;
            this.isDone = isDone;
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

        public static Task parse(String str) throws DukeException {
            String[] input = str.split(Pattern.quote(" | "));
            try {
                boolean isDone = input[1].equals("1");
                switch (input[0]) {
                    case "T":
                        return new ToDo(input[2], isDone);
                    case "D":
                        return new Deadline(input[2], input[3], isDone);
                    case "E":
                        return new Event(input[2], input[3], isDone);
                    default:
                        throw new DukeException("One or more Tasks are wrongly tagged!");
                }
            } catch (ArrayIndexOutOfBoundsException aiooe) {
                throw new DukeException("One or more Tasks have too few arguments!");
            }
        }

        public String toSave() {
            return String.format("%s | %s | %s", this.tag, isDone ? "1" : "0", this.description);
        }

        @Override
        public String toString() {
            return String.format("[%s][%s] %s", this.tag, this.getStatusIcon(), this.description);
        }
    }

    public static class ToDo extends Task {

        public ToDo(String description, boolean isDone) {
            super(description, "T", isDone);
        }

    }

    public static class Deadline extends Task {

        String deadline;

        public Deadline(String description, String deadline, boolean isDone) {
            super(description, "D", isDone);
            this.deadline = deadline;
        }

        @Override
        public String toString() {
            return String.format("%s (by: %s)", super.toString(), this.deadline);
        }

        @Override
        public String toSave() {
            return String.format("%s | %s", super.toSave(), this.deadline);
        }

    }

    public static class Event extends Task {

        String time;

        public Event(String description, String time, boolean isDone) {
            super(description, "E", isDone);
            this.time = time;
        }

        @Override
        public String toString() {
            return String.format("%s (at: %s)", super.toString(), this.time);
        }

        @Override
        public String toSave() {
            return String.format("%s | %s", super.toSave(), this.time);
        }

    }

    public static class DukeException extends Exception {
        public DukeException(String error) {
            super(error);
        }
    }

    public static void main(String[] args){
        String logo
                = "     ____        _        \n"
                + "    |  _ \\ _   _| | _____ \n"
                + "    | | | | | | | |/ / _ \\\n"
                + "    | |_| | |_| |   <  __/\n"
                + "    b|____/ \\__,_|_|\\_\\___|\n";
        var list = new ArrayList<Task>();
        Scanner sc = new Scanner(System.in);
        String line = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";
        System.out.println(line + "\n" + logo + "\n    Hello! I'm Duke!\n");
        try {
            BufferedReader br = new BufferedReader(new FileReader("./src/main/data/duke.txt"));
            System.out.println("    A save file has been found and loaded!\n"
                    + "    Your current tasks are: ");
            br.lines().forEach(str -> {
                try {
                    list.add(Task.parse(str));
                } catch (DukeException de){
                    System.out.println("    Error: "+ de.getMessage());
                }
            });
            int i = 0;
            if (list.size() == 0) {
                System.out.println("    ... empty! Good work!");
            } else {
                while (list.size() > i) {
                    System.out.println("        " + ++i + ". " + list.get(i - 1).toString());
                }
            }
        } catch (FileNotFoundException fnfe) {
            System.out.println("    No save file found in [root]/src/main/data/duke.txt.\n"
                    + "    A file has been created by default.\n"
                    + "    If you'd like to import one, simply copy the file\n"
                    + "    over to the above location and rerun me!\n");
            new File("./src/main/data").mkdirs();
            try {
                new File("./src/main/data/duke.txt").createNewFile();
            } catch (IOException ioe) {
                System.out.println("Sorry, the file couldn't be created!\n"
                        + "Please try again.");
            }
        }
        System.out.println("    Awaiting input...\n" + line);
        while (true) {
            try {
                Task task;
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
                        String toWrite = list.stream()
                                .map(Task::toSave)
                                .reduce((x, y) -> x + "\n" + y)
                                .orElse("");
                        try {
                            FileWriter fw = new FileWriter("./src/main/data/duke.txt");
                            fw.write(toWrite);
                            fw.close();
                        } catch (IOException ignored) { }
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
                        task = new ToDo(description.toString(), false);
                        list.add(task);
                        System.out.println(line + "\n    Got it. I've added this task:\n        "
                                + task.toString()
                                + "\n    You now have " + list.size()
                                + (list.size() == 1 ? " task" : " tasks")
                                + " in your list.\n" + line);
                        String toWrite = list.stream()
                                .map(Task::toSave)
                                .reduce((x, y) -> x + "\n" + y)
                                .orElse("");
                        try {
                            FileWriter fw = new FileWriter("./src/main/data/duke.txt");
                            fw.write(toWrite);
                            fw.close();
                        } catch (IOException ignored) { }
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
                        String toWrite = list.stream()
                                .map(Task::toSave)
                                .reduce((x, y) -> x + "\n" + y)
                                .orElse("");
                        try {
                            FileWriter fw = new FileWriter("./src/main/data/duke.txt");
                            fw.write(toWrite);
                            fw.close();
                        } catch (IOException ignored) { }
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
                        task = new Deadline(description.toString(), time.toString(), false);
                        list.add(task);
                        System.out.println(line + "\n    Got it. I've added this task:\n        "
                                + task.toString()
                                + "\n    You now have " + list.size()
                                + (list.size() == 1 ? " task" : " tasks")
                                + " in your list.\n" + line);
                        String toWrite = list.stream()
                                .map(Task::toSave)
                                .reduce((x, y) -> x + "\n" + y)
                                .orElse("");
                        try {
                            FileWriter fw = new FileWriter("./src/main/data/duke.txt");
                            fw.write(toWrite);
                            fw.close();
                        } catch (IOException ignored) { }
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
                        task = new Event(description.toString(), time.toString(), false);
                        list.add(task);
                        System.out.println(line + "\n    Got it. I've added this task:\n        "
                                + task.toString()
                                + "\n    You now have " + list.size()
                                + (list.size() == 1 ? " task" : " tasks")
                                + " in your list.\n" + line);
                        String toWrite = list.stream()
                                .map(Task::toSave)
                                .reduce((x, y) -> x + "\n" + y)
                                .orElse("");
                        try {
                            FileWriter fw = new FileWriter("./src/main/data/duke.txt");
                            fw.write(toWrite);
                            fw.close();
                        } catch (IOException ignored) { }
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
