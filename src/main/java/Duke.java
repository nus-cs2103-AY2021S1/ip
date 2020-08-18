import java.util.Scanner;

public class Duke {
    public static String linePrinter() {
        return "\n----------------------------\n";
    }

    public static void main(String[] args) {
        String endCommand = "bye";
        System.out.println((linePrinter() +
                "Hello! I'm Duke\n" +
                "What can I do for you?" +
                linePrinter()).replaceAll("(?m)^", "\t"));

        Scanner in = new Scanner(System.in);
        String s = in.nextLine();
        while(!s.equals(endCommand)) {
            System.out.println((linePrinter() +
                    s +
                    linePrinter()).replaceAll("(?m)^", "\t"));
            s = in.nextLine();
        }
        if (s.equals(endCommand)) {
            System.out.println((linePrinter() +
                    "Bye. Hope to see you again soon!" +
                    linePrinter()).replaceAll("(?m)^", "\t"));
        }
    }
}
