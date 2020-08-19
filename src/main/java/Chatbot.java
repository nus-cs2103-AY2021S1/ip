import java.util.ArrayList;
import java.util.List;

public class Chatbot {
    public String line = "____________________________________________________________";
    public List<String> list = new ArrayList<>();

    public void greeting() {
        System.out.println(line);
        System.out.println("Hello! I'm Duke\n" +
                "What can I do for you?");
        System.out.println(line);
    }

    public void echo(String s) {
        System.out.println(line);
        System.out.println(s);
        System.out.println(line);
    }

    public void generateList() {
        System.out.println(line);
        for (int i = 0; i < list.size(); i++) {
            System.out.println((i + 1) + ". " + list.get(i));
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

        list.add(s);
        System.out.println(line);
        System.out.println("added: " + s);
        System.out.println(line);
        return true;
    }

    public void exit() {
        System.out.println(line);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(line);
    }
}
