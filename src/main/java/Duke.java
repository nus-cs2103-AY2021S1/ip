import chatbot.ChatbotEcho;

public class Duke {

    private static final String logo = ""
            + " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    public static void main(String[] args) {
        System.out.println("Hello from\n" + logo);

        String greeting = "Hello! I'm Echo\nLet's have a conversation!";
        String endingGreeting = "Bye. Hope to see you again soon!";

        ChatbotEcho chatbotEcho = new ChatbotEcho(greeting, System.in, endingGreeting);
        chatbotEcho.run();
    }
}
