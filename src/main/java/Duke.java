import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private final String line = "------------------------------------";
    private final String stringFormat = line + "\n%s\n" + line + "\n\n";

    private Scanner scanner;
    private List<String> list;

    private Duke() {
        this.scanner = new Scanner(System.in);
        this.list = new ArrayList<>();
        greet();
    }

    private void greet() {
        System.out.printf(stringFormat, "Hello! I'm Duke\nWhat can I do for you?");
    }

    private void processBye() {
        System.out.printf(stringFormat, "Bye. Hope to see you again soon!");
    }

    private void processAdd(String item) {
        this.list.add(item);
        System.out.printf(stringFormat, "added: " + item);
    }

    private void processList() {
        StringBuilder content = new StringBuilder();

        for (int i = 0; i < this.list.size(); i++) {
            content.append(i + 1);
            content.append(". " + this.list.get(i));
            if (i < this.list.size() - 1) {
                content.append("\n");
            }
        }
        System.out.printf(stringFormat, content.toString());
    }

    private boolean processAction() {
        String action = this.scanner.nextLine();
        switch (action) {
            case "bye":
                this.processBye();
                return false;
            case "list":
                this.processList();
                break;
            default:
                this.processAdd(action);
                break;
        }
        return true;
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|";
        System.out.println("Hello from\n" + logo);

        Duke duke = new Duke();
        boolean status;
        do {
            status = duke.processAction();
        } while (status);

    }
}
