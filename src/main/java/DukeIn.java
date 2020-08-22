import java.util.Scanner;

public class DukeIn {

    private static final Scanner in = new Scanner(System.in);

    public static String prompt() {
        System.out.print(">> ");
        String response = in.nextLine();
        return response;
    }
}
