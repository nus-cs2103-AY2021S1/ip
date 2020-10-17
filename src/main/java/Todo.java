class Todo extends Task {

    /**
     * Todo constructor
     * @param task String representation of task name
     */
    Todo(String task) {
        super(task);
    }

    /**
     * Returns string representation of todo
     * @return formatted string representation of todo
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Updates the representation of the todo object to be saved to the storage file
     */
    @Override
    public void updateRep() {
        super.updateRep();
        this.saveRep = "T%d%" + this.done + "%d%" + this.task;
    }
}
