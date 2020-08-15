public class Duke {
    private static final String LOGO = " ____        _        \n"
                                    + "|  _ \\ _   _| | _____ \n"
                                    + "| | | | | | | |/ / _ \\\n"
                                    + "| |_| | |_| |   <  __/\n"
                                    + "|____/ \\__,_|_|\\_\\___|\n";

    public static void main(String[] args) {
        greet();
        String response = DukeIn.prompt();
        String exitCondition = "bye";
        while (!response.equals(exitCondition)) {
            handleResponse(response);
            response = DukeIn.prompt();
        }
        exit();
    }

    private static void greet() {
        String greeting = "Hi! I am\n" + LOGO + "\n" + "What can I do for you?";
        System.out.println(greeting);
    }

    private static void exit() {
        String goodbye = "Bye! Hope to see you again!";
        DukeOut.print(goodbye);
    }

    private static void handleResponse(String response) {
        DukeOut.print(response);
    }
}
