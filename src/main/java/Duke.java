import java.util.ArrayList;
import java.util.Scanner;

/**
 * Immutable Duke chatbot class to encapsulate the behavior of the chatbot.
 */
public class Duke {
    private final ArrayList<String> list;

    private Duke(ArrayList<String> list) {
        this.list = new ArrayList<>(list);
    }

    /**
     * Return a new Duke chatbot object.
     *
     * @return New Duke chatbot object.
     */
    public static Duke getDuke() {
        return new Duke(new ArrayList<>());
    }

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

    /**
     * Store a command in the bot.
     *
     * @param command User input string.
     * @return Updated Duke object.
     */
    public Duke storeItem(String command) {
        ArrayList<String> newList = new ArrayList<>(this.list);
        newList.add(command);
        return new Duke(newList);
    }

    public void displayList() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(" ").append(i + 1).append(". ").append(list.get(i)).append("\n");
        }
        System.out.println(TextFormatter.getFormattedText(sb.toString()));
    }

    public static void main(String[] args) {
        Duke bot = getDuke();
        Scanner sc = new Scanner(System.in);

        // Greet user
        bot.greet();

        // Echo command until user types bye
        while (sc.hasNext()) {
            String s = sc.nextLine();
            if (s.equals("bye")) {
                break;
            } else if (s.equals("list")) {
                bot.displayList();
            } else {
                bot = bot.storeItem(s);
                System.out.println(TextFormatter.getFormattedText("added: " + s));
            }
        }

        // Exit bot
        bot.bye();
    }
}
