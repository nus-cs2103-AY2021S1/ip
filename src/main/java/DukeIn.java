import java.util.Scanner;

public class DukeIn {
    public static String prompt() {
        System.out.print(">> ");
        Scanner in = new Scanner(System.in);
        String response = in.nextLine();
        return response;
    }
}
