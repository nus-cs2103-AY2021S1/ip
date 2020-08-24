import java.io.IOException;
import java.util.Scanner;

/**
 * Gets input from user and passes information to user.
 */
public class Ui {
    public static String greeting = "Hello, I'm Duke, your personal assistant. \n What can I do for you?";
    public static String home = System.getProperty("user.home");//home = C:/Users/david
    public PlayBot bot;
    /**
     * Greets with user.
     */
    public void greeting() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println(greeting);
    }

    /**
     * Interacts with user.
     * @throws IOException
     */
    public void run() throws IOException {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String order = sc.nextLine();
            String reply = Parser.process(order);
            System.out.println(reply);
        }
    }

    public void setBot(PlayBot bot) {
        this.bot = bot;
    }

    public void play() {
        bot.greeting();
        Scanner scPlay = new Scanner(System.in);
        bot.setName(scPlay.nextLine());
        bot.intro();
        while (scPlay.hasNextLine()) {
            String playerMove = scPlay.nextLine();
            if (playerMove.equals("bye")) {
                break;
            } else {
                bot.respond(playerMove);
            }
        }
    }

    public void showLoadingError() {
    }
}
