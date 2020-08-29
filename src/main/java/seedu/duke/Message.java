package seedu.duke;

import java.util.function.Consumer;

public class Message {
    public static String TASK_ADDED = "Task added: ";
    public static String TASK_DONE = "Task marked as done: ";
    public static String TASK_DELETED = "Task deleted: ";

    private String[] message;

    public Message(String[] message) {
        this.message = message;
    }

    public Message(String message) {
        this.message = new String[]{message};
    }

    public String getText() {
        String result = "";
        for (String line: message) {
            result += line + "\n";
        }
        return result;
    }

    public void forEach(Consumer<? super String> consumer) {
        for (String line: message) {
            consumer.accept(line);
        }
    }

    public static Message getWelcome() {
        return new Message(new String[]{"Hello from",
                " ____        _        ",
                "|  _ \\ _   _| | _____ ",
                "| | | | | | | |/ / _ \\",
                "| |_| | |_| |   <  __/",
                "|____/ \\__,_|_|\\_\\___|"});
    }

    public static Message getGoodbye() {
        return new Message("Have a nice day.");
    }
}
