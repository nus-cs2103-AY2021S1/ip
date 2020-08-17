import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Duke {
    private final List<Task> items;
    public Duke() {
        this.items = new ArrayList<>();
    }
    private void addItem(Task task) {
        this.items.add(task);
        systemMessage("morning sir i have added this to the list sir:\n"
                        + task
                        + "\ni counted all your number of tasks sir it is "
                        + this.items.size()
                        + " sir");
    }
    private void printList() {
        String numberedItems = "";
        for (int i=0;i<this.items.size();i++) {
            numberedItems += (i+1) + ". " + this.items.get(i) + "\n";
        }
        systemMessage(numberedItems);
    }
    private void markItem(int idx) {
        Task selected = this.items.get(idx);
        selected.markAsDone();
        systemMessage("afternoon sir i have mark this task done sir:\n  " + selected);
    }
    private void displayParseError() {
        systemMessage("sorry sir i dont understand your command sir\n"
                        + "please enter again sir thank you sir");
    }
    private void parseAndRun(String input) {
        if (input.equals("list")) {
            this.printList();
        } else if (input.matches("^done \\d+")) {
            int itemIndex = Integer.parseInt(input.substring(5));
            this.markItem(itemIndex - 1);
        } else if (input.matches("^todo .*")) {
            String description = input.substring(5);
            this.addItem(new Todo(description));
        } else if (input.matches("^deadline .*")) {
            String[] items = input.substring(9).split(" /by ");
            if(items.length!=2) {
                displayParseError();
                return;
            }
            String description = items[0];
            String time = items[1];
            this.addItem(new Deadline(description, time));
        } else if (input.matches("^event .*")) {
            String[] items = input.substring(6).split(" /at ");
            if(items.length!=2) {
                displayParseError();
                return;
            }
            String description = items[0];
            String time = items[1];
            this.addItem(new Event(description, time));
        } else {
            displayParseError();
        }
    }
    public void run() {
        Scanner scanner = new Scanner(System.in);
        systemMessage(logo);
        String input = scanner.nextLine();
        while (!input.equals("bye")) {
            parseAndRun(input);
            input = scanner.nextLine();
        }
        systemMessage("bye sir thanks for using me sir hope to see you again sir");
    }
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }
    private static final String logo =
                "               /,   ,|   ,|     \n" +
                "           /| /(  ,' / ,//      \n" +
                "        \\`( |/ /,'  (,/ |      \n" +
                "         \\ \\ ` `   `  /--,    \n" +
                "       _,_\\ `  ` `  ``  /__    \n" +
                "        '-.____________`  /     \n" +
                "          [  \\@,    :] `--,-..-\n" +
                "          [__________]__,'-._/  \n" +
                "           )'o\\ ' o) \\/ )       hello sir\n" +
                "           \\  /   __  ./        its me your assistant sir\n" +
                "            \\=`   ==,\\..        how may i be of service sir\n" +
                "             \\ -. `,' (        \n" +
                "             \\`--''    \\.     \n";
    private static final String horizontalRule =
                "    ____________________________________________________________\n";
    private void systemMessage(String input) {
        System.out.println(horizontalRule+indent(input)+"\n"+horizontalRule);
    }
    private String indent(String original) {
        return "     "+ original.replace("\n", "\n     ");
    }
}
