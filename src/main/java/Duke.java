import duke.ChatBot;

public class Duke {
    private static ChatBot bot = new ChatBot();

    /**
     * The starting point of running Duke.
     * Use command <code>java Duke</code> to start Duke.
     * @param args argument strings from the input
     */
    public static void main(String[] args) {
        /*
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
         */

        // initialise a new chat bot
        // ChatBot bot = new ChatBot();
        // bot.start();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    String getResponse(String input) {
        return bot.getResponse(input);
    }
}
