package duke.storage;

import java.util.ArrayList;

import duke.exception.DukeException;

public class StateList {
    private ArrayList<State> listOfStates = new ArrayList<>();
    private int pointer;

    /**
     * Creates a new StateList object with starting state
     *
     * @param state The starting state
     */
    public StateList(State state) {
        listOfStates.add(state);
        pointer = 0;
    }

    /**
     * Shifts the pointer to a previous state
     *
     * @throws DukeException If unable to undo
     */
    public void undoState() throws DukeException {
        if (!canUndo()) {
            throw new DukeException("Oops! No more tasks to undo.");
        } else if (canUndo()) {
            pointer--;
        } else {
            throw new DukeException("What is going on");
        }
    }

    /**
     * Shifts the pointer to a future state
     *
     * @throws DukeException If unable to redo
     */
    public void redoState() throws DukeException {
        if (!canRedo()) {
            throw new DukeException("Oops! No more tasks to redo!");
        } else if (canRedo()) {
            pointer++;
        } else {
            throw new DukeException("What is going on");
        }
    }

    private boolean canRedo() {
        return pointer < listOfStates.size() - 1;
    }

    private boolean canUndo() {
        return pointer > 0 && listOfStates.size() > 0;
    }

    /**
     * Adds a new state at the pointer. All states after the pointer is deleted.
     *
     * @param updatedState The new State to add to the list of states
     */
    public void newState(State updatedState) {
        if (pointer + 1 < listOfStates.size()) {
            listOfStates.subList(pointer + 1, listOfStates.size()).clear();
        }
        listOfStates.add(updatedState);
        pointer++;
    }

    /**
     * Returns the current State where the pointer is.
     *
     * @return The current State
     */
    public State getState() {
        return listOfStates.get(pointer);
    }
}
