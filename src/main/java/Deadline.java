public class Deadline extends Task {

    private final String end;

    public Deadline(String name, boolean isDone, String end){
        super(name, isDone);
        this.end = end;
    }

    @Override
    public Task setToTrue(){
        return new Deadline(this.name, true, this.end);
    }

    @Override
    public String getType(){
        return "D";
    }

    @Override
    public String getEnd() {
        return this.end;
    }

    @Override
    public String toString(){
        return isDone
                ? "[D][✓] " + this.getName() + " (by: " + this.end + ")"
                : "[D][✗] " + this.getName() + " (by: " + this.end + ")";
    }
}