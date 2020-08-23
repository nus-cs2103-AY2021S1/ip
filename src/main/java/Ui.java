import java.util.Scanner;

public class Ui {
    private final String line = "----------------------";
    private final String logo = " ____        _        \n"
                                + "|  _ \\ _   _| | _____ \n"
                                + "| | | | | | | |/ / _ \\\n"
                                + "| |_| | |_| |   <  __/\n"
                                + "|____/ \\__,_|_|\\_\\___|\n";
    private Scanner sc = new Scanner(System.in);

    public String getInput() {
        return sc.nextLine();
    }

    public void format(String input) {
        System.out.println(line + "\n" + input + "\n" + line);
    }

    public void greet() {
        format("Hello! I'm\n" + logo + "\n" +
                "What can I do for you?");
    }

    public Scanner getScanner() {
        return this.sc;
    }
}
