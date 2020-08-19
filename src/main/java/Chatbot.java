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

    public boolean chat(String s) {

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

        if (s.contains("/by")) {
            String[] value = s.split("(?<=/by) ");
            Deadline deadline = new Deadline(value[0].replace(" /by", ""), value[1]);
            list.add(deadline);
            addTask(deadline);
            return true;
        }

        if (s.contains("/at")) {
            String[] value = s.split("(?<=/at) ");
            Event event = new Event(value[0].replace(" /at", ""), value[1]);
            list.add(event);
            addTask(event);
            return true;
        }

        list.add(new ToDo(s));
        addTask(new ToDo(s));
        return true;
    }

    public void exit() {
        System.out.println(line);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(line);
    }
}
