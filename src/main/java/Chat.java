import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Chat {

    private final List<String> history;

    public Chat() {
        this.history = new ArrayList<>(100);
    }

    private void echo(String input) {
        System.out.println("Echo: " + input);
    }

    private void add(String input) {
        this.history.add(input);
    }

    private void list() {
        int i = 0;
        for (String item : this.history) {
            System.out.println(++i + ". " + item);
        }
    }

    public void run() {
        String input;
        Scanner scanner = new Scanner(System.in);

        while(true) {

            // Prompt for input
            System.out.print("> ");
            input = scanner.nextLine();

            if (input.equals("bye")) {
                break;
            }

            if (input.equals("list")) {
                list();
                continue;
            }

            add(input);
            echo(input);

        }
    }
}
