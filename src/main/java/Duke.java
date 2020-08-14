import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private final static String EXIT_COMMAND = "bye";
    private List<String> list;

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.hello();
        duke.handle();
        duke.bye();
    }

    private Duke() {
        list = new ArrayList<>();
    }

    private void handle() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        while (!input.equals(EXIT_COMMAND)){
            switch (input) {
                case "list":
                    this.displayList();
                    break;
                default:
                    this.addToList(input);
            }
            input = scanner.nextLine();
        }
        scanner.close();
    }

    private void say(String text) {
        System.out.println("------------------------------------------------------------");
        System.out.println(text);
        System.out.println("------------------------------------------------------------\n");
    }

    private void hello() {
        say("Hello! I'm Duke\nWhat can I do for you?");
    }

    private void addToList(String text) {
        this.list.add(text);
        say("added: " + text);
    }

    private void displayList() {
        String text = "";
        for (int i = 0; i < this.list.size(); i++) {
            text += ((i + 1) + ". " + this.list.get(i) + (i == this.list.size() - 1 ? "" : "\n"));
        }
        say(text);
    }

    private void bye() {
        say("Bye. Hope to see you again soon!");
    }

}

