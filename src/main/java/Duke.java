import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Duke {
    private final List<String> items;
    public Duke() {
        this.items = new ArrayList<>();
    }
    private void addItem(String input) {
        this.items.add(input);
        systemMessage("morning sir i have added \""+input+"\" to the list sir");
    }
    private void printList() {
        String numberedItems = "";
        for (int i=0;i<this.items.size();i++) {
            numberedItems += Integer.toString(i+1) + ". " + this.items.get(i) + "\n";
        }
        systemMessage(numberedItems);
    }
    public void run() {
        Scanner scanner = new Scanner(System.in);
        systemMessage(logo);
        String input = scanner.nextLine();
        while (!input.equals("bye")) {
            if (input.equals("list")) {
                this.printList();
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
