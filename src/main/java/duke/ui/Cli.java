package duke.ui;

import java.util.Scanner;

/**
 * Class implementing Ui as a command line interface (stdio).
 */
public class Cli implements Ui {
    private static final String LOGO =
        "               /,   ,|   ,|     \n"
            + "           /| /(  ,' / ,//      \n"
            + "        \\`( |/ /,'  (,/ |      \n"
            + "         \\ \\ ` `   `  /--,    \n"
            + "       _,_\\ `  ` `  ``  /__    \n"
            + "        '-.____________`  /     \n"
            + "          [  \\@,    :] `--,-..-\n"
            + "          [__________]__,'-._/  \n"
            + "           )'o\\ ' o) \\/ )       hello sir\n"
            + "           \\  /   __  ./        its me your assistant sir\n"
            + "            \\=`   ==,\\..        how may i be of service sir\n"
            + "             \\ -. `,' (        \n"
            + "             \\`--''    \\.     \n";
    private static final String HORIZONTAL_RULE =
        "    ____________________________________________________________\n";
    private final Scanner scanner;
    private boolean isActive;

    /**
     * Constructor for Ui object.
     */
    public Cli() {
        this.scanner = new Scanner(System.in);
        this.isActive = false;
    }

    @Override
    public String nextLine() {
        return this.scanner.nextLine();
    }

    @Override
    public boolean isActive() {
        return this.isActive;
    }

    /**
     * Closes this Ui object, and sets isActive() to false.
     */
    public void close() {
        assert this.isActive : "Cli instance should not be closed twice or closed before initialization.";
        this.scanner.close();
        this.isActive = false;
    }

    @Override
    public void start() {
        assert !this.isActive : "Cli instance should not be initialized twice.";
        this.isActive = true;
        this.systemMessage(LOGO);
    }

    @Override
    public void systemMessage(String input) {
        System.out.println(HORIZONTAL_RULE + indent(input) + "\n" + HORIZONTAL_RULE);
    }

    private String indent(String original) {
        return "     " + original.replace("\n", "\n     ");
    }
}
