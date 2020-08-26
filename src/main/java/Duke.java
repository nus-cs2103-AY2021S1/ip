public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println(logo +"Hello! I'm Duke\n" + "What can I do for you?");

        IOHandler handler = new IOHandler();

        handler.handleIO();

        System.out.print("Bye. Hope to see you again soon!");
    }
}
