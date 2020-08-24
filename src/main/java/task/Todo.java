package task;

public class Todo extends Task{
    public Todo(String item) throws EmptyStringException{
        super(item);
        taskType = "T";
    }

    @Override
    public String encode() {
        String encoded = "todo " + item;
        if(this.done){
            encoded = encoded + "\n" + "done";
        }
        return encoded;
    }
}
