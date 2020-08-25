import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Ui {
    Scanner scan;
    static String format = "\t";

    Ui() {
        this.scan = new Scanner(System.in);
    }

    void welcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println(format + "Hello! I'm Duke");
        System.out.println(format + "What can I do for you?");
    }

    String readCommand() {
        return this.scan.nextLine();
    }


    public void showLine() {
        System.out.println("_______");
    }

    public void goodbye() {
        System.out.println(format + "Bye. Hope to see you again soon!");
    }

    public void showError(String message) {
        System.out.println(format + "An error was thrown: " + message);
    }

    public void printf(String printStr) {
        String print = format + printStr;
        String[] printSplit = print.split("\n");
        for (int i = 1; i <  printSplit.length; i ++) {
            printSplit[i] = "\t" + printSplit[i];
        }
        System.out.println(Arrays.asList(printSplit).stream().collect(Collectors.joining("\n")));
    }
}
