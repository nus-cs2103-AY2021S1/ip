public abstract class Task extends Command{
    private final String title;
    protected String saveRep;
    private boolean isDone = false;

    Task(String title){
        this.title = title;
    }

    private String doneToString(){
        return isDone ? "[DONE]": "[X]";
    }

    public void markDone(){
        isDone = true;
    }

    public boolean isDone() {
        return isDone;
    }

    public String saveStringRepresentation(){
        return saveRep;
    }

    @Override
    public String toString() {
        return doneToString() + " " + title;
    }
}
