package duke.lists;

import duke.commands.Command;
import duke.exceptions.DukeInvalidUndoException;

import java.util.ArrayList;


/**
 * Keeps track of the commands, which edit the task list, that the user has made
 */
public class CommandList {
    
    private final ArrayList<Command> commandList;

    /**
     * Constructor for CommandList class
     */
    public CommandList () {
        this.commandList = new ArrayList<>();
    }

    /**
     * Adds a new command to the list
     * 
     * @param c command added to the list
     */
    public void addCommand (Command c) {
        this.commandList.add(c);
    }

    /**
     * Returns the latest command that the user has made and removes it from the list
     * 
     * @return latest command user has made
     * @throws DukeInvalidUndoException if the list is empty
     */
    public Command removeLatestCommand () throws DukeInvalidUndoException {
        if (size() <= 0) {
            throw new DukeInvalidUndoException("Nothing to undo");
        }
       return this.commandList.remove(size() -1);
    }

    /**
     * Returns the size of the list
     * 
     * @return size of the list
     */
    public int size() {
        return this.commandList.size();
    }
    
    /**
     * Removes all the commands from the list 
     */
    public void clear() {
        this.commandList.clear();
    }
}
