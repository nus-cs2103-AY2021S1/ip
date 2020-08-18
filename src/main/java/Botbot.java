import java.util.Scanner;

public class Botbot {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Chat chat = new Chat();
        while (chat.isRunning()) {
            String input = sc.nextLine();
            try {
                chat.handleInput(input);
            } catch (BotbotException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}