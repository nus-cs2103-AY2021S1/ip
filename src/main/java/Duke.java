import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        InputHandler handler = new InputHandler();
        while(handler.handleInput(sc.nextLine()));
    }
}
