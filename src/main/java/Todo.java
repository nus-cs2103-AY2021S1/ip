public class Todo extends Task{

    //Call the constructor
    public Todo(String description){

        //Call the superclass constructor
        super(description);

    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
