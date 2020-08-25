import java.io.FileNotFoundException;
import java.util.Scanner;


public class Duke {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(System.in);
        Chatbot chatbot = new Chatbot(scanner);
        chatbot.start();
    }
}
