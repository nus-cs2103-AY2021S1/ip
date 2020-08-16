import java.util.Scanner;

public class Duke {

    /**
     * Print greeting message.
     */
    public void greet() {
        System.out.println(TextFormatter.LOGO);
        System.out.println(TextFormatter.getFormattedText("Hi I'm Cat Bot.\nWhat can I do for you?"));
    }

    /**
     * Print goodbye message.
     */
    public void bye() {
        System.out.println(TextFormatter.getFormattedText("Bye! Hope to see you again!"));
    }

    /**
     * Print echoed command.
     *
     * @param command User command.
     */
    public void echo(String command) {
        System.out.println(TextFormatter.getFormattedText(command));
    }

    public static void main(String[] args) {
        Duke bot = new Duke();
        Scanner sc = new Scanner(System.in);

        // Greet user
        bot.greet();

        // Echo command until user types bye
        while (sc.hasNext()) {
            String s = sc.next();
            if (s.equals("bye")) {
                break;
            }
            bot.echo(s);
        }

        // Exit bot
        bot.bye();
    }
}
