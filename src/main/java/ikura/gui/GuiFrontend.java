// GuiFrontend.java
// Copyright (c) 2020, zhiayang, Apache License 2.0.

package ikura.gui;

import java.util.Optional;
import java.util.stream.Stream;
import java.util.stream.Collectors;

import ikura.Bot;
import ikura.Frontend;
import ikura.TaskList;
import ikura.task.Todo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class GuiFrontend extends Frontend {

    private final ObservableList<String> outputLog;

    public GuiFrontend(TaskList tasks) {
        super(tasks);

        this.outputLog = FXCollections.observableArrayList();
    }

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
    }
}
