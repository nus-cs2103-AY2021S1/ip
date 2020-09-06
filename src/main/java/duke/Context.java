package duke;

import duke.command.MacroList;
import duke.task.TaskList;
import duke.ui.Ui;

public class Context {
    private final TaskList taskList;
    private final Ui ui;
    private final MacroList macroList;

    public Context(TaskList taskList, Ui ui) {
        assert taskList != null && ui != null : "taskList and ui should not be null";
        this.taskList = taskList;
        this.ui = ui;
        this.macroList = new MacroList();
    }

    public TaskList getTaskList() {
        return this.taskList;
    }

    public Ui getUi() {
        return this.ui;
    }

    public MacroList getMacroList() {
        return macroList;
    }
}
