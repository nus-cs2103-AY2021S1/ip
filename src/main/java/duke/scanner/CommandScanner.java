package duke.scanner;

import java.util.Scanner;

/**
 * CommandScanner that scans for commands
 */
public class CommandScanner {
    private Scanner sc;

    public CommandScanner() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Returns next command as as string from the user input
     *
     * @return a String that represent the next command in the user input
     */
    public String nextCommand() {
        return this.sc.nextLine();
    }
}
