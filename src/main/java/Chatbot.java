import java.util.ArrayList;
import java.util.List;

public class Chatbot {
    public String line = "____________________________________________________________";
    public List<Task> list = new ArrayList<>();

    public void greeting() {
        System.out.println(line);
        System.out.println("Hello! I'm Duke\n" +
                "What can I do for you?");
        System.out.println(line);
    }

//    public void echo(String s) {
//        System.out.println(line);
//        System.out.println(s);
//        System.out.println(line);
//    }

    public void addTask(Task t) {
        System.out.println(line);
        System.out.println("Got it. I've added this task:");
        System.out.println(t.printTask());
        System.out.println("Now you have " + list.size() + (list.size() > 1 ? " tasks" : " task") + " on the list");
        System.out.println(line);
    }
    public void generateList() {
        System.out.println(line);
        for (int i = 0; i < list.size(); i++) {
            Task t = list.get(i);
            System.out.println((i + 1) + ". " + t.printTask());
        }
        System.out.println(line);
    }

    public boolean chat(String s) throws IncorrectInputException {

        if (s.equals("bye")) {
            exit();
            return false;
        }

        if (s.equals("list")) {
            generateList();
            return true;
        }

        if (s.contains("done")) {
            char x = s.charAt(s.length() - 1);
            int i = Character.getNumericValue(x);
            Task t = list.get(i - 1);
            t.markAsDone();
            list.set(i - 1, t);
            System.out.println(line);
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(t.printTask());
            System.out.println(line);

            return true;
        }

        try {
            if (s.startsWith("deadline")) {
                if (s.length() != "deadline".length()) {
                    String[] value = s.split("(?<=/by) ");
                    Deadline deadline = new Deadline(value[0].replace(" /by", ""), value[1]);
                    list.add(deadline);
                    addTask(deadline);
                    return true;
                } else {
                    throw new IncorrectInputException("☹ OOPS!!! The description of a deadline cannot be empty.");
                }
            }

            if (s.startsWith("event")) {
                if (s.length() != "event".length()) {
                    String[] value = s.split("(?<=/at) ");
                    Event event = new Event(value[0].replace(" /at", ""), value[1]);
                    list.add(event);
                    addTask(event);
                    return true;
                } else {
                    throw new IncorrectInputException("☹ OOPS!!! The description of an event cannot be empty.");
                }
            }

            if (s.startsWith("todo")) {
                if (s.length() != "todo".length()) {
                    list.add(new ToDo(s));
                    addTask(new ToDo(s));
                    return true;
                } else {
                    throw new IncorrectInputException("☹ OOPS!!! The description of a todo cannot be empty.");
                }
            }

            throw new IncorrectInputException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public void exit() {
        System.out.println(line);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(line);
    }
}
