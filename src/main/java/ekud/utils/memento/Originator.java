package ekud.utils.memento;

import ekud.tasks.TaskList;

public class Originator {
    private TaskList state;
    private String command;

    public void set(TaskList state, String command) {
        this.state = state.copy();
        this.command = command;
    }

    public TaskList getState() {
        return this.state;
    }

    /**
     * Saves the current state as a memento.
     *
     * @return the memento representing the current state of the data
     */
    public Memento saveToMemento() {
        return new Memento(this.state, this.command);
    }

    /**
     * Restores the current state from memento.
     *
     * @param memento the memento to restore
     */
    public void restoreFromMemento(Memento memento) {
        this.state = memento.state;
        this.command = memento.command;
    }

    public static class Memento {
        private final TaskList state;
        private final String command;

        /**
         * Instantiates a new Memento.
         *
         * @param stateToSave the state to save
         * @param command     the command which resulted in a change of state
         */
        public Memento(TaskList stateToSave, String command) {
            state = stateToSave;
            this.command = command;
        }

        public String getCommand() {
            return this.command;
        }
    }
}
