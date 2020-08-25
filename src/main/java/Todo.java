class Todo extends Task {
    Todo(String task) {

        super(task);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public void updateRep() {
        super.updateRep();
        this.saveRep = "T%d%" + this.done + "%d%" + this.task;
    }
}