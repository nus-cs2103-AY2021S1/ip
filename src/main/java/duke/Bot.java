package duke;

/**
 * Interface which defines how a Function can control the bot
 */
public interface Bot {
    public void stop();

    public void sayLine(String string);
}
