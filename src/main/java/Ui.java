import java.util.Scanner;

/**
 * The {@code Ui} class manages user interactions.
 */
public class Ui {

    private final Scanner sc;

    /**
     * Initialises a Ui object.
     */
    public Ui() {
        sc = new Scanner(System.in);
    }

    /**
     * Returns a horizontal line as a formatting tool to
     * create divisions between questions and responses.
     *
     * @return Horizontal line.
     */
    public String div() {
        return "\n______________________________"
            + "________________________________\n\n";
    }

    /**
     * Returns greetings for users.
     *
     * @return Greetings.
     */
    public String greet() {
        return "              █████████\n"
            + "  ███████          ███        ███\n"
            + "  █      █       ███             ███\n"
            + "   █      █    ██                   ██\n"
            + "    █     █   ██     ██      ██     ███          Hey! I'm Jimmy,\n"
            + "     █   █   █      ████    ████      ██     your personal assistant!\n"
            + "   █████████████                      ██\n"
            + "   █            █         █           ██   What can I do for you today?\n"
            + " ██             █   ██          ██    ██\n"
            + "██   ███████████     ██        ██     ██\n"
            + "█               █      ████████       ██\n"
            + "██              █                    ██\n"
            + " █   ███████████                   ██\n"
            + " ██          ████                 █\n"
            + "  ████████████   █████████████████";
    }

    /**
     * Returns {@code String} from reading user input.
     *
     * @return {@code String} form of user input.
     */
    public String process() {
        return this.sc.nextLine();
    }

    /**
     * Formats and prints responses to user inputs.
     *
     * @param input {@code String} that is formatted and printed.
     */
    public void print(String input) {
        System.out.println(this.div() +
            "\t" + input + this.div());
    }
}
