import java.util.Scanner;

public class InputHandler {

    Scanner sc;

    public InputHandler() {
        sc = new Scanner(System.in);
    }

    public String input() {
        return sc.nextLine();
    }
}
