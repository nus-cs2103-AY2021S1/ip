import java.util.ArrayList;
import java.util.List;

public class Sparkles {

    private final String[] args;
    private static final String dash = "____________________________________________________________";
    private static final List<Task> storage = new ArrayList<>();


    public Sparkles(String[] args) {
        this.args = args;
    }

    private void printDash() {
        print(dash);
    }

    private void respond(String str) {
        String lowerCase = str.toLowerCase();
        if (lowerCase.equals("list")) {
            showList();
        } else if (lowerCase.contains("done")) {
            markAsDone(str);
        } else if (lowerCase.equals("bye")) {
            exit(str);
        } else {
            addToList(str);
        }
    }

    private void printHead(String str) {
        print("\n" + str);
    }

    private void exit(String str) {
        printHead(str);
        printDash();
        print("     Bye. Hope to see you again!");
        printDash();
    }

    private void markAsDone(String str) {
        printHead(str);
        printDash();
        int index = Integer.parseInt(String.valueOf(str.charAt(5)));
        Task task = storage.get(index - 1);
        task.markAsDone();
        print("     Nice! I have marked this task as done :-)");
        printDash();
    }

    private void showList() {
        printHead("list");
        printDash();

        for(int i = 1; i < storage.size() + 1; i++) {
            Task task = storage.get(i - 1);
            task.printTask(i);
        }

        printDash();
    }

    private void addToList(String str) {
        printHead(str);
        printDash();
        print("     added to list: " + str);
        storage.add(new Task(str));
        printDash();
    }

    private void run() {
        printResult(this.args);
    }


    private void printResult(String... arg) {
        welcome();
        for (String str : arg) {
            respond(str);
            if (str.toLowerCase().equals("bye")) {
                System.exit(0);
            }
        }
    }

    private void welcome() {
        print(dash);
        print("*Hello, I am Sparkles*\n\nHow can I help you?");
        print(dash);
    }
    
    private void print(String str) {
        System.out.println(str);
    }

    public static void main(String[] args) {
        new Sparkles(args).run();
    }
}
