import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Duke {
    private final List<Task> items;
    public Duke() {
        this.items = new ArrayList<>();
    }
    private void addItem(String input) {
        this.items.add(new Task(input));
        systemMessage("morning sir i have added \""+input+"\" to the list sir");
    }
    private void printList() {
        String numberedItems = "";
        for (int i=0;i<this.items.size();i++) {
            numberedItems += Integer.toString(i+1) + ". " + this.items.get(i) + "\n";
        }
        systemMessage(numberedItems);
    }
    private void markItem(int idx) {
        Task selected = this.items.get(idx);
        selected.markAsDone();
        systemMessage("afternoon sir i have mark this task done sir:\n  " + selected);
    }
    public void run() {
        Scanner scanner = new Scanner(System.in);
        systemMessage(logo);
        String input = scanner.nextLine();
        while (!input.equals("bye")) {
            if (input.equals("list")) {
                this.printList();
            } else if (input.length() > 5 && input.substring(0,5).equals("done ")) {
                int itemIndex = Integer.parseInt(input.substring(5));
                this.markItem(itemIndex-1);
            } else {
                this.addItem(input);
            }
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
