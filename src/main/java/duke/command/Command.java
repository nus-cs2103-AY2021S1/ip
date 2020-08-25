package duke.command;

import duke.exception.DukeException;

import duke.storage.Storage;

import duke.task.TaskList;

import duke.ui.Ui;

public class Command {
    private String cmd;
    
    Command(String cmd) {
        this.cmd = cmd;
    }
    
    public Command() {
        
    }
    
    public boolean isExit() {
        return false;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ui.showUnrecognizedCommandMessage();
    }
}
