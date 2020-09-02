// Frontend.java
// Copyright (c) 2020, zhiayang, Apache License 2.0.

package ikura;

import java.util.Optional;

public abstract class Frontend {

    public static final String BOT_NAME  = "ikurabowl";

    protected final Bot bot;
    protected Frontend(TaskList tasks) {
        this.bot = new Bot(this, tasks);
    }

    /**
     * Returns the bot instance owned by this frontend.
     *
     * @return the bot.
     */
    public Bot getBot() {
        return this.bot;
    }

    /**
     * Runs the main loop (or equivalent) of the frontend. When this method returns, the
     * program should exit.
     */
    public abstract void run();

    /**
     * Prints a line of output to this TextFrontend's output stream. Has an identical interface to
     * System.out.printf().
     *
     * @param fmt  the format string.
     * @param args the list of objects.
     */
    public abstract void println(String fmt, Object... args);

    /**
     * Begins a "session", ie. the output to a given command. Used to delimit output between consecutive
     * commands.
     */
    public abstract void beginLog();

    /**
     * Ends a "session", ie. the output to a given command. Used to delimit output between consecutive
     * commands, or to signal that the frontend should update.
     */
    public abstract void endLog();
}
