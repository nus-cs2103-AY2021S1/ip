import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static final ArrayList<Task> taskList = new ArrayList<>();
    private static final String EXIT_CMD = "bye";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        InputHandler handler = new InputHandler();
        while(handler.handleInput(sc.nextLine(), taskList));
    }
}
