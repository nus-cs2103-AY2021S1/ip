public class TodoTask extends Task{

    public TodoTask(String name, boolean isComplete) {
        super(name, isComplete);
    }

    public String getType(){
        return "T";
    }

    @Override
    public String toString(){
        return "[T]" + super.toString();
    }
}
