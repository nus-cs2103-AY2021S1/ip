package luoyi.duke;

import luoyi.duke.data.Duke;
import luoyi.duke.data.IDuke;

import java.util.Scanner;

/**
 * Main driver class.
 */
public class Main {
    public static void main(String[] args) {
        IDuke bot = Duke.getDuke("data/duke.txt");
        Scanner sc = new Scanner(System.in);

        // Greet user
        bot.greet();

        // Handle commands until user types bye
        while (sc.hasNext()) {
            String s = sc.nextLine();
            if (s.equals("bye")) {
                break;
            } else if (!s.matches("\\s*")) {
                // Ignore white spaces or empty lines
                bot = bot.handleCommand(s);
            }
        }

        // Exit bot
        bot.bye();
    }
}
