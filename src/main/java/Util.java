public class Util {
    public static final String logo =
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
    public static final String horizontalRule =
            "    ____________________________________________________________\n";
    public static void systemMessage(String input) {
        System.out.println(horizontalRule+indent(input)+"\n"+horizontalRule);
    }
    public static String indent(String original) {
        return "     "+ original.replace("\n", "\n     ");
    }
}
