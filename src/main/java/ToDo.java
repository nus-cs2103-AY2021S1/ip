public class ToDo extends Task  {

    public ToDo(String description) {
        super(description);
    }

    public ToDo(boolean isDone, String description) {
        super(isDone, description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toStoreFormat(){
        String type = "T";
        String status = isDone ? "1" : "0";
        String connect = " | ";
        return type + connect + status + connect + description;
    }
}
