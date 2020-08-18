import java.util.ArrayList;
import java.util.List;

public class Sparkles {

    private final String[] args;
    private static final String dash = "____________________________________________________________";
    private static final List<String> storage = new ArrayList<>();


    public Sparkles(String[] args) {
        this.args = args;
    }

    private void welcome() {
        System.out.println(dash);
        System.out.println("*Hello, I am Sparkles*\n\nHow can I help you?");
        System.out.println(dash);
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

    private void run() {
        printResult(this.args);
    }

    private void printDash() {
        System.out.println(dash);
    }

    private void respond(String str) {
        System.out.println("\n" + str);
        printDash();
        String lowerCase = str.toLowerCase();
        if (lowerCase.equals("bye")) {
            System.out.println("     Bye. Hope to see you again!");
        } else {
            addToList(str);
        }
        printDash();
    }

    private void addToList(String str) {
        System.out.println("     added to list: " + str);
    }

    public static void main(String[] args) {
        new Sparkles(args).run();
    }
}
