// GuiFrontend.java
// Copyright (c) 2020, zhiayang, Apache License 2.0.

package ikura.gui;

import ikura.Frontend;
import ikura.TaskList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class GuiFrontend extends Frontend {

    private final ObservableList<String> outputLog;

    /**
     * Constructs a new GuiFrontend using the given tasklist.
     *
     * @param tasks the TaskList to use
     */
    public GuiFrontend(TaskList tasks) {
        super(tasks);

        this.outputLog = FXCollections.observableArrayList();
    }

    /**
     * Get the output log of the Bot, as an ObservableList.
     *
     * @return the output log as an ObservableList
     */
    public ObservableList<String> getOutputLog() {
        return this.outputLog;
    }

    /**
     * Executes the given command with the bot instance. The interface is the same; if this returns
     * true, then the bot should not exit; if it returns false, then it should exit.
     *
     * @param cmd the command to execute
     * @return true if the bot should continue, false if it should exit
     */
    public boolean processCommand(String cmd) {
        return this.bot.processCommand(cmd);
    }

    @Override
    public void run() {
        FxWrapper.run(this);
    }

    @Override
    public void println(String fmt, Object... args) {
        this.outputLog.add(String.format(fmt, args));
    }

    @Override
    public void beginLog() {
    }

    @Override
    public void endLog() {

        // make some spacing, since the user input is not echoed
        this.outputLog.add("");
    }
}
