package duke.ui;

/**
 * Represents the interactions between Focus and user.
 */
public class UI {
    /**
     * Divider.
     */
    private static final String divider =
            "\t------------------------------------------------------------------\n";

    /**
     * Prints the divider.
     */
    public void printDivider() {
        System.out.print(divider);
    }

    /**
     * Prints divider with next line.
     */
    public void printDividerWithSpacing() {
        System.out.println(divider);
    }

    /**
     * Greets the user upon entering the Focus.
     */
    public void greetUser() {
        String logo =
                "\t __\n"
                        + "\t/ _|   ___    ___  _   _  ___\n"
                        + "\t| |_  / _ \\  / __|| | | |/ __|\n"
                        + "\t|  _|| (_) || (__ | |_| |\\__ \\ _\n"
                        + "\t|_|   \\___/  \\___| \\__,_||___/(_)\n";
        System.out.println("Welcome to\n" + logo);
        String greetings = divider
                + "\t\"\\(*^O^*) I am Pocus, your personal assistant!"
                + "\n\tBefore we start, may I know your name?\n"
                + divider;
        System.out.println(greetings);
    }

    /**
     * Addresses the user with the name provided.
     * @param name Name of user.
     */
    public void addressUser(String name) {
        String address = divider
                + "\tHi there, " + name + "!"
                + "\n\tHow can I help you today?\n"
                + divider;
        System.out.println(address);
    }

    /**
     * Exits Focus.
     */
    public void exitFocus() {
        String exit = "\tHopefully I have helped you today. Byeee! (*^O^*)/\"\n"
                + divider;
        System.out.println(exit);
    }
}
