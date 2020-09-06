package duke;

import duke.commands.Command;
import duke.exceptions.DukeInvalidUndoException;

import java.util.ArrayList;

public class CommandList {
    
    private final ArrayList<Command> commandList;
    
    public CommandList () {
        this.commandList = new ArrayList<>();
    }
    
    public void addCommand (Command c) {
        this.commandList.add(c);
    }
    
    public Command removeLatestCommand () throws DukeInvalidUndoException {
        if (size() <= 0) {
            throw new DukeInvalidUndoException("Nothing to undo");
        }
       return this.commandList.remove(size() -1);
    }
    
    public int size() {
        return this.commandList.size();
    }
    
    public void clear() {
        this.commandList.clear();
    }
}
