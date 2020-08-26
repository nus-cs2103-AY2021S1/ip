package ui;
import java.util.Scanner;

public class Ui {
    public static final String LINE = "_______________________________________\n";
    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public String getUserInput() {
        return this.scanner.nextLine();
    }

    public void welcome() {
        String open = "_______________________________________ \n"
                + "Hello! I'm Duke \n"
                + "What can I do for you? \n"
                + "_______________________________________ \n";
        System.out.println(open);
    }

    public void goodbye() {
        String close = "_______________________________________ \n"
                + "Goodbye! See you soon! \n"
                + "_______________________________________ \n";
        System.out.println(close);
        this.scanner.close();
    }
}
