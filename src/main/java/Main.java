import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IncorrectInputException, IOException {
        Scanner sc = new Scanner(System.in);

        Chatbot bot = new Chatbot();
        bot.greeting();


        while (sc.hasNext() && bot.chat(sc.nextLine())) {
//            String next = sc.next();
//            bot.chat(next);
        }
        sc.close();
    }
}
