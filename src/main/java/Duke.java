import java.util.Scanner;
import java.util.ArrayList;
public class Duke {
    static class Task {
        boolean completed = false;
        String name;

        Task(String name) {
            this.name = name;
        }
        public void setCompleted() {
            completed = true;
        }

        @Override
        public String toString() {
            return (completed ? "[✓]" : "[✗]") + " " + name;
        }

    }

    static class Todo extends Task {
        Todo(String name) {
            super(name);
        }

        @Override
        public String toString() {
            return "[T]" + super.toString();
        }
    }

    static class Deadline extends Task {
        String deadline;
        Deadline(String name, String deadline) {
            super(name);
            this.deadline = deadline;
        }

        @Override
        public String toString() {
            return "[D]" + super.toString() + " (by: " + deadline + ")";
        }
    }

    static class Event extends Task {
        String time;
        Event(String name, String time) {
            super(name);
            this.time = time;
        }

        @Override
        public String toString() {
            return "[T]" + super.toString() + " (at: " + time + ")";
        }
    }

    static boolean isNum(String s) {
        if (s == null) {
            return false;
        }
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    static void print(String s) {
        System.out.println("\t____________________________________________________________\n" +
                s +
                "\t____________________________________________________________\n");
    }
    static ArrayList<Task> list = new ArrayList<>();
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s;
        print("\tHello! I'm Duke\n" +
                "\tWhat can I do for you?\n");
        while(!(s = scanner.nextLine()).equals("bye")) {
            String[] done = s.split(" ");
            String[] task = new String[2];
            if (done.length == 2 && done[0].equals("done") && isNum(done[1])
                    && Integer.parseInt(done[1]) <= list.size() && Integer.parseInt(done[1]) > 0) {
                list.get(Integer.parseInt(done[1]) - 1).setCompleted();
                print("\tNice! I've marked this task as done:\n" +
                        "\t" + list.get(Integer.parseInt(done[1]) - 1) + "\n");
            } else if(s.equals("list")) {
                String temp = "";
                for(int i = 0; i < list.size(); i++) {
                    temp += "\t" + (i+1) + ". " + list.get(i) + "\n";
                }
                print("\tHere are the tasks in your list:\n" + temp);
            } else if (done.length > 0 &&
                    (done[0].equals("todo") ||
                            (done[0].equals("deadline") && (task = s.split(" /by ")).length == 2)||
                            (done[0].equals("event") && (task = s.split(" /at ")).length == 2))){
                if(done[0].equals("todo")) {
                    if(done.length == 1) {
                        print("\t☹ OOPS!!! The description of a todo cannot be empty.\n");
                        continue;
                    }
                    list.add(new Todo(s.replaceFirst("todo ", "")));
                } else if(done[0].equals("deadline")) {
                    list.add(new Deadline(task[0].replaceFirst("deadline ", ""), task[1]));
                } else {
                    list.add(new Event(task[0].replaceFirst("event ", ""), task[1]));
                }
                print("\tGot it. I've added this task: \n" +
                        "\t" + list.get(list.size()-1) + "\n" +
                        "\tNow you have " + list.size() + " tasks in the list.\n");
            } else {
                print("\t☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n");
            }
        }
        print("\tBye. Hope to see you again soon!\n");
    }
}
