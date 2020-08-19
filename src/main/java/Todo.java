public class Todo extends Task {

    public String type;

    public Todo(String desc, boolean isDone) {
        super(desc, isDone);
        type = "T";
    }

    @Override
    public String getType() {
        return this.type;
    }

}
