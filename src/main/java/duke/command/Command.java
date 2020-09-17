package main.java.duke.command;

import main.java.duke.task.TaskList;
import duke.Ui;
import duke.Storage;
import main.java.duke.dukeexception.DukeException;

public abstract class Command {
//    private String duke.command;
//
//    private Set<String> terminationCommands = new HashSet<String>(
//            Arrays.asList("bye", "toodles", "farewell", "sayonara"));
//
//    public Command(String duke.command) {
//        this.duke.command = duke.command;
//    }

    public abstract boolean isExit();
    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException;
}
