public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return ("[" + (isDone ? "\u2713" : "\u2718") + "] ");
    }

    public void markDone() {
        this.isDone = true;
    }

    public String toString() {
        return this.getStatusIcon() + description;
    }

    public static boolean checkIfDone(char number) throws DukeException {
        switch(number) {
            case '0':
                return false;
            case '1':
                return true;
            default:
                throw new DukeException("Item in list on HDD does not have a 'done' status");
        }
    }

    public abstract String stringToSave();

    public abstract void print(int numOfIndents);

}

