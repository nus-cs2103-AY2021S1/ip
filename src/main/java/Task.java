class Task {
    protected final String task;
    protected String saveRep;
    protected boolean done;

    Task(String task) {
        this.task = task;
        this.done = false;
        this.updateRep();
    }
    static Task createTask(String type, String done, String name, String time) {
        boolean doneBool = done.equals("true");
        Task returnTask;
        if (type.equals("T")) {
            returnTask = new Todo(name);
        } else if (type.equals("D")) {
            returnTask = new Deadline(name, time);
        } else {
            returnTask = new Event(name, time);
        }
        returnTask.done = doneBool;
        return returnTask;
    }

    @Override
    public String toString() {
        return done ? ("[\u2713] " + task) : ("[\u274C] " + task);
    }

    public void updateRep() {
        this.saveRep = this.done + "%d%" + this.task;
    }

    public void setDone() {
        this.done = true;
        this.updateRep();
    }

    public String getSaveRep() {
        return this.saveRep;
    }
}
