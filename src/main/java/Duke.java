import duke.*;

public class Duke {

    /**
     * The starting point of running Duke.
     * Use command <code>java Duke</code> to start Duke.
     * @param args argument strings from the input
     */
    public static void main(String[] args) {
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);

        // initialise a new chat bot
        ChatBot bot = new ChatBot();
        bot.start();
    }
}
