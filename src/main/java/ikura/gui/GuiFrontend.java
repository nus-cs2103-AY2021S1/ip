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

public class GuiFrontend extends Frontend {

    public GuiFrontend(TaskList tasks) {
        super(tasks);
    }

    @Override
    public void run() {
        FxWrapper.run(this);
    }

    @Override
    public void println(String fmt, Object... args) {
    }

    @Override
    public void beginLog() {
    }

    @Override
    public void endLog() {

        // since this is called at the end of each command that's run, we can use
        // the opportunity to update the gui.
    }








}
