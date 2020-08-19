import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Chatbot bot = new Chatbot();
        bot.greeting();

        while (sc.hasNext()) {
            String next = sc.next();
            if (!next.equals("bye")) {
                bot.echo(next);
            } else {
                bot.exit();
                sc.close();
            }
        }
    }
}
