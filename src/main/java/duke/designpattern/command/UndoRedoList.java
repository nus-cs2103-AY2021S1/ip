package duke.designpattern.command;

import java.util.ArrayList;
import java.util.List;

/**
 * UndoRedoList stores a list of ReversibleExecutable and provides
 * methods to easily add, undo and redo those ReversibleExecutables
 */
public class UndoRedoList {

    private final List<ReversibleExecutable> list;
    private int index; // index always points to next empty spot in the list

    /**
     * Constructs an empty UndoRedoList
     */
    public UndoRedoList() {
        this.list = new ArrayList<>(100);
        this.clear();
    }

    /**
     * Empty the UndoRedoList
     */
    public void clear() {
        this.list.clear();
        this.index = 0;
    }

    /**
     * Appends item to end of list
     * @param item ReversibleExecutable to be added into list
     * @throws NullPointerException if item is null
     */
    public void add(ReversibleExecutable item) throws NullPointerException {
        // Undo() has been called before the execution of this method
        if (this.index != this.list.size()) {
            // Remove all undone items from list
            this.list.subList(this.index, this.list.size()).clear();
        }

        // null not allowed
        if (item == null) {
            throw new NullPointerException();
        }

        // Add item to list and increment index
        this.list.add(item);
        this.index++;
    }

    /**
     * Undo the previously added item in this list.
     * Automatically keeps track of item's undo redo status
     * @return true if successful, or false if all
     * item has already been undo
     */
    public boolean undo() {
        // Already at earliest state, cannot undo
        if (this.index == 0) {
            return false;
        }

        // Decrement index and undo executable
        this.list.get(--this.index).reverse();
        return true;
    }

    /**
     * Redo the previously undone item in this list
     * Automatically keeps track of item's undo redo status
     * @return true if successful, or false if all
     * item has already been redo
     */
    public boolean redo() {
        // Already at latest state, cannot redo
        if (this.index == this.list.size()) {
            return false;
        }

        // Redo executable and increment index
        this.list.get(this.index++).execute();
        return true;
    }

}
