public class ToDo extends Task {
    
    public ToDo(String new_task) {
        super(new_task);
    }

    @Override
    public String toString() {
        return "[T]" +  super.toString() ;
    }
}

