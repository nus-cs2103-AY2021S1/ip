
public class Duke {

    private static final String logo = ""
            + " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private static final String greeting = "Hello! I'm Duke\nLet's have a conversation!";
    private static final String endingGreeting = "Bye. Hope to see you again soon!";

    public static void main(String[] args) {
        System.out.println("Hello from\n" + logo);
        System.out.println(greeting);
        Chat chat = new Chat();
        chat.run();
        System.out.println(endingGreeting);
    }

}
