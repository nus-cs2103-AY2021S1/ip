import java.util.Scanner;

public class Ui {
    private final Scanner scanner;
    private boolean isActive;
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
    public Ui() {
        this.scanner = new Scanner(System.in);
        this.isActive = true;
    }
    public String nextLine() {
        return this.scanner.nextLine();
    }
    public boolean isActive() {
        return this.isActive;
    }
    public void close() {
        this.scanner.close();
        this.isActive = false;
    }
    public void welcomeMessage() {
        this.systemMessage(logo);
    }
    public void systemMessage(String input) {
        System.out.println(horizontalRule+indent(input)+"\n"+horizontalRule);
    }
    private String indent(String original) {
        return "     "+ original.replace("\n", "\n     ");
    }
}
