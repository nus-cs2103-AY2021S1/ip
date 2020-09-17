package main.java.duke.command;

import main.java.duke.task.TaskList;
import main.java.duke.Ui;
import main.java.duke.Storage;
import main.java.duke.dukeexception.DukeException;

public abstract class Command {
//    private String command;
//
//    private Set<String> terminationCommands = new HashSet<String>(
//            Arrays.asList("bye", "toodles", "farewell", "sayonara"));
//
//    public Command(String command) {
//        this.command = command;
//    }

    public abstract boolean isExit();
    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException;
}
