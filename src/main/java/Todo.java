public class Todo extends Task{

    public Todo(String description){
        super(description);
    }

    @Override
    public String toString(){
        return "[T]" + super.toString();
    }

    @Override
    public String write(){
        return "T|" + super.toString();
    }
}
