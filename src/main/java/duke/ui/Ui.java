package duke.ui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Implements the user interface functionalities.
 *
 * @author Audrey Felicio Anwar
 */
public class Ui {
    private static final String SEPARATOR = System.getProperty("line.separator");
    private static final String LOGO = " _   _       _   _ _            \n"
            + "| | | |     | | | (_)           \n"
            + "| |_| | ___ | |_| |_ _ __   ___ \n"
            + "|  _  |/ _ \\| __| | | '_ \\ / _ \\\n"
            + "| | | | (_) | |_| | | | | |  __/\n"
            + "\\_| |_/\\___/ \\__|_|_|_| |_|\\___| \n";
    private static final String DIVIDER = "<<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>><<>>";
    private static final String WELCOME_MESSAGE = DIVIDER + "\n Thanks for contacting Hotline! \n"
            + " How can I help you today? \n"
            + DIVIDER + "\n";
    private static final String GOODBYE_MESSAGE =
            "██████████████████████████████████"
            + "████████████████████████████████"
            + "████████████████████████████████"
            + "████████████████████████████████"
            + "████\n"
            + "██                  ████      ██"
            + "██████  ██████████████████      "
            + "████    ██  ▒▒██████████  ██████"
            + "██  ████████        ░░██    ████"
            + "██████\n"
            + "██                  ████      ██"
            + "██████    ████████  ██████      "
            + "██      ██    ████████    ░░████"
            + "▒▒    ████            ██      ▓▓"
            + "      \n"
            + "██                  ████      ██"
            + "████      ████████    ▒▒██      "
            + "██              ██████      ████"
            + "                              ██"
            + "      \n"
            + "████      ████      ████      ██"
            + "████        ██████              "
            + "██            ████████          "
            + "              ████            ██"
            + "      \n"
            + "████      ████      ▒▒        ██"
            + "██          ▒▒████              "
            + "██          ████████████        "
            + "    ██      ██████            ██"
            + "      \n"
            + "████      ████                ██"
            + "██    ░░      ████              "
            + "██        ████████████████      "
            + "    ██      ██████            ██"
            + "      \n"
            + "████      ████                ██"
            + "                ██              "
            + "██          ██████████████      "
            + "  ████      ████▒▒            ██"
            + "      \n"
            + "████      ████      ████    ██░░"
            + "                ▒▒      ██      "
            + "██            ▓▓██████████      "
            + "  ████                ██        "
            + "      \n"
            + "████      ████      ████    ██  "
            + "      ████              ████░░  "
            + "██            ████████████      "
            + "  ████▒▒              ██        "
            + "      \n"
            + "████      ████      ████    ██▒▒"
            + "      ████    ████      ████████"
            + "██      ██    ████████████      "
            + "  ██████░░          ██████      "
            + "    ██\n"
            + "████████████████████████████████"
            + "████████████████████████████████"
            + "████████████████████████████████"
            + "████████████    ████████████    "
            + "██████\n"
            + "████████████████████████████████"
            + "████████████████████████████████"
            + "████████████████████████████████"
            + "████████████████████████████████"
            + "██████\n"
            + "████████████████████████████████"
            + "████████████████████████████████"
            + "      ██████████████████████████"
            + "████████████████████████████████"
            + "██████\n"
            + "████████████████████████████████"
            + "████████████████████████████    "
            + "        ████████████████████████"
            + "████████████████████████████████"
            + "██████\n"
            + "████████████████████████████████"
            + "████████████████████████████    "
            + "  ██    ████████████████████████"
            + "████████████████████████████████"
            + "██████\n"
            + "████████████████████████████████"
            + "████████████████████████████    "
            + "        ████████████████████████"
            + "████████████████████████████████"
            + "██████\n"
            + "████████████████████████████████"
            + "████████████████████████████▓▓  "
            + "      ██  ██████████████████████"
            + "████████████████████████████████"
            + "██████\n"
            + "████████████████████████████████"
            + "██████████████████████████      "
            + "            ████████████████████"
            + "████████████████████████████████"
            + "██████\n"
            + "████████████████████████████████"
            + "██████████████████████████      "
            + "██          ████████████████████"
            + "████████████████████████████████"
            + "██████\n"
            + "████████████████████████████████"
            + "██████████████████████████      "
            + "            ████████████████████"
            + "████████████████████████████████"
            + "██████\n"
            + "████████████████████████████████"
            + "██████████████████████████▒▒    "
            + "          ██████████████████████"
            + "████████████████████████████████"
            + "██████\n"
            + "████████████████████████████████"
            + "████████████████████████████████"
            + "████████████████████████████████"
            + "████████████████████████████████"
            + "██████\n"
            + "████████████████████████████████"
            + "████████████████████████████████"
            + "████████████████████████████████"
            + "████████████████████████████████"
            + "██████\n"
            + "██████████████████        ██████"
            + "██████      ██████████      ████"
            + "██▒▒        ██████▒▒        ████"
            + "██  ██████░░████████████▒▒  ████"
            + "██████\n"
            + "██████████████              ░░██"
            + "██            ████            ██"
            + "              ██              ██"
            + "    ██████    ░░            ████"
            + "██████\n"
            + "████████████░░              ████"
            + "                                "
            + "                                "
            + "      ██                    ████"
            + "██████\n"
            + "████████████      ░░████░░████  "
            + "      ████            ████      "
            + "      ██░░            ██    ▒▒██"
            + "              ██      ██████████"
            + "██████\n"
            + "██████████      ████            "
            + "    ██████          ██████      "
            + "      ████                  ░░██"
            + "██          ▒▒██          ██████"
            + "██████\n"
            + "██████████      ████            "
            + "    ██████          ██████      "
            + "      ████                      "
            + "████        ████          ██████"
            + "██████\n"
            + "██████████      ██████          "
            + "    ████            ████        "
            + "      ██      ██      ████      "
            + "████      ██████            ████"
            + "██████\n"
            + "██████████░░                ▒▒  "
            + "              ██              ██"
            + "              ██                "
            + "████      ██████            ████"
            + "██████\n"
            + "████████████                ████"
            + "            ████            ░░██"
            + "            ████              ██"
            + "████      ██████            ████"
            + "██████\n"
            + "██████████████            ██████"
            + "██          ██████          ████"
            + "          ██████            ████"
            + "████      ██████            ████"
            + "██████";
    private ArrayList<String> accumulatedResponses;

    /**
     * Creates a new Ui object.
     */
    public Ui() {
        this.accumulatedResponses = new ArrayList<>();
    }

    /**
     * Returns welcome message.
     *
     * @return Welcome message.
     */
    public String getGreetings() {
        return LOGO + SEPARATOR + WELCOME_MESSAGE;
    }

    /**
     * Returns line divider.
     *
     * @return Line divider.
     */
    public String showLine() {
        return DIVIDER;
    }

    /**
     * Returns goodbye message.
     *
     * @return Goodbye message.
     */
    public String showGoodbyeUser() {
        return GOODBYE_MESSAGE;
    }

    /**
     * Returns loading error message.
     *
     * @return Loading error message.
     */
    public String showLoadingError() {
        return " Failed to load saved data :(";
    }

    /**
     * Prints messages line by line.
     *
     * @param messages Messages to be printed.
     */
    public void printMessage(String... messages) {
        Arrays.stream(messages).forEach(message -> System.out.println(message));
    }

    /**
     * Reads current input from user.
     *
     * @return String indicating current input.
     */
    public String readInput() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    /**
     * Accumulates response from various functionalities.
     *
     * @param responses Responses to be accumulated.
     */
    public void accumulateResponses(String... responses) {
        Arrays.stream(responses).forEachOrdered(response -> accumulatedResponses.add(response));
    }

    /**
     * Returns the accumulated responses and reset.
     *
     * @return Accumulated responses.
     */
    public String getResponses() {
        StringBuilder sb = new StringBuilder();
        accumulatedResponses.stream().limit(accumulatedResponses.size() - 1)
                .forEachOrdered(response -> sb.append(response + SEPARATOR));
        sb.append(accumulatedResponses.get(accumulatedResponses.size() - 1));
        accumulatedResponses = new ArrayList<>();
        return sb.toString();
    }
}
