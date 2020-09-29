package duke;

import java.util.function.Consumer;

import duke.task.Task;

/**
 * Represents a response from Duke to the user
 */
public class Message {
    private String[] message;

    /**
     * Class constructor.
     *
     * @param message to be shown to the user
     */
    public Message(String[] message) {
        this.message = message;
    }

    /**
     * Class constructor.
     *
     * @param message to be shown to the user
     */
    public Message(String message) {
        this.message = new String[]{message};
    }

    /**
     * Returns a welcome message from Duke.
     *
     * @return the welcome message
     */
    public static Message getWelcome() {
        return new Message(new String[]{"Hello from",
            " ____        _        ",
            "|  _ \\ _   _| | _____ ",
            "| | | | | | | |/ / _ \\",
            "| |_| | |_| |   <  __/",
            "|____/ \\__,_|_|\\_\\___|"});
    }

    /**
     * Returns a farewell message from Duke.
     *
     * @return the farewell message
     */
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

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (String line : message) {
            result.append(line).append("\n");
        }
        return result.toString();
    }

    /**
     * Carries out an action on each line in the message.
     *
     * @param consumer action to be carried out
     */
    public void forEach(Consumer<? super String> consumer) {
        for (String line : message) {
            consumer.accept(line);
        }
    }
}
