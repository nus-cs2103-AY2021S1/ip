import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Ui {

    private final Scanner in;
    private final PrintStream out;

    public Ui() {
        this(System.in, System.out);
    }

    public Ui(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    public void linePrinter() {
        System.out.println("\n-------------------------------------------------------------------------\n");
    }

    public void start() {
        linePrinter();
        printResult("Hello! I'm Duke\n" +
                "What can I do for you?");
        linePrinter();
    }

    public void showLoadingError() {
        System.out.println("Whoops! Some kind of error :/ see here: ");
    }

    public void bye() {
        String byeText = "Running away huh??";
        printResult(byeText);
        linePrinter();
    }

    public String getUserCommand() {
        return this.in.nextLine();
    }

    public void showError(String error) {
        System.out.println(error);
    }

    public void printResult(String lines) {
        System.out.println(lines.replaceAll("(?m)^", "\t"));
    }
}
