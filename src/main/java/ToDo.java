public class ToDo extends Task{

    ToDo(String name) {
        super(name);
    }

    @Override
    public String toString(){
        if (super.completed){
            return "[T]" + "[" + "✓" + "] " + name;
        } else {
            return "[T]" + "[" + "✗" + "] " + name;
        }
    }
}
