import java.util.Scanner;

public class ReadIn {
    public static String readIn() {
        String input;
        Scanner scan = new Scanner(System.in);

        input = scan.nextLine();

        return input;
    }
}