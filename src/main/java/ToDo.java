public class ToDo extends Task {

    ToDo(String title) throws WrongUsageException {
        super(title);
        this.name = "todo";
        this.usage = "todo [TaskToBeDone]";
        this.description = "Stores a task in the list marked as a todo";
        if (title.isEmpty()) {
            throw new WrongUsageException(this.name, this.usage);
        }
        this.saveRep = "[T] " + super.toString();
    }

    @Override
    public void updateSaveRep(){
        this.saveRep = "[T] " + super.toString();
    }

    @Override
    public String toString() {
        return "[T] " + super.toString();
    }
}
