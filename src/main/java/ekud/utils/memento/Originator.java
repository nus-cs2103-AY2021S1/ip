package ekud.utils.memento;

import ekud.tasks.TaskList;

public class Originator {
    private TaskList state;
    private String command;

    public void set(TaskList state, String command) {
        this.state = state.copy();
        this.command = command;
        System.out.println("set " + state + command);
    }

    public TaskList getState() {
        return this.state;
    }

    public Memento saveToMemento() {
        System.out.println("save " + state + command);
        return new Memento(this.state, this.command);
    }

    public void restoreFromMemento(Memento memento) {
        this.state = memento.state;
        this.command = memento.command;
        System.out.println("restore " + state + command);
    }

    public static class Memento {
        private final TaskList state;
        private final String command;

        public Memento(TaskList stateToSave, String command) {
            state = stateToSave;
            this.command = command;
        }

        public String getCommand() {
            return this.command;
        }
    }
}
