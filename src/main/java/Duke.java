import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Chat chat = new Chat();
        while (chat.isRunning()) {
            String input = sc.nextLine();
            chat.handleInput(input);
        }
    }
}