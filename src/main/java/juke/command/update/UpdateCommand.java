package juke.command.update;

import juke.command.Command;

public abstract class UpdateCommand extends Command {

    protected Integer index;

    public UpdateCommand(int index) {
        super();
        this.index = index;
    }

}
