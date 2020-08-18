import java.util.Scanner;

public class Echo {
    public static String echo() {
        String input;
        Scanner scan = new Scanner(System.in);

        input = scan.nextLine();

        if (input.equals("bye")) {
            return Exit.exit();
        } else {
            return input;
        }
    }
}
