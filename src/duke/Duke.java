package duke;

/**
 * Entry point of the programme, Duke is used as it is the project's name.
 */
public class Duke {

    /**
     * The main method which handles users and calls relevant methods:
     * Storage is called to print out stored tasks on the hard disk from the previous run,
     * Ui is called to handle user inputs/ commands specifically.
     *
     * @param args standard format
     */
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo +
                "People call me Duke the All-Knowing ," +
                "ask me anything by typing a line.");

        Storage.read();

        Ui ui = new Ui();
        ui.deal();

    }

}
