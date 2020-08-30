package seedu.duke;

import java.util.function.Consumer;

import seedu.duke.task.Task;

public class Message {
    private String[] message;

    public Message(String[] message) {
        this.message = message;
    }

    public Message(String message) {
        this.message = new String[]{message};
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

    public static Message getTaskAdded(Task task) {
        return new Message("Task added: " + task);
    }

    public static Message getTaskDone(Task task) {
        return new Message("Task marked as done: " + task);
    }

    public static Message getTaskDeleted(Task task) {
        return new Message("Task deleted: " + task);
    }

    public String getText() {
        StringBuilder result = new StringBuilder();
        for (String line : message) {
            result.append(line).append("\n");
        }
        return result.toString();
    }

    public void forEach(Consumer<? super String> consumer) {
        for (String line : message) {
            consumer.accept(line);
        }
    }
}
