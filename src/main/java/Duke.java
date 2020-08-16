import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        systemMessage(logo);
        String input = scanner.nextLine();
        while(!input.equals("bye")) {
            systemMessage(input);
            input = scanner.nextLine();
        }
        systemMessage("Bye sir thanks for using me sir hope to see you again sir");
    }
    private static final String logo =
                "                 /,   ,|   ,|     \n" +
                "             /| /(  ,' / ,//      \n" +
                "          \\`( |/ /,'  (,/ |      \n" +
                "           \\ \\ ` `   `  /--,    \n" +
                "         _,_\\ `  ` `  ``  /__    \n" +
                "          '-.____________`  /     \n" +
                "            [  \\@,    :] `--,-..-\n" +
                "            [__________]__,'-._/  \n" +
                "             )'o\\ ' o) \\/ )       Hello sir\n" +
                "             \\  /   __  ./        Its me your assistant sir\n" +
                "              \\=`   ==,\\..        How may I be of service sir\n" +
                "               \\ -. `,' (        \n" +
                "               \\`--''    \\.     \n";
    private static void systemMessage(String input) {
        System.out.println(horizontalRule+indent(input)+"\n"+horizontalRule);
    }
    private static String indent(String original) {
        return "     "+ original.replaceAll("\n", "    \n");
    }
    private static final String horizontalRule = "    ____________________________________________________________\n";
}
