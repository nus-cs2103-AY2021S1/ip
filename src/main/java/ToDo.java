public class ToDo extends Task {
    
    public ToDo(String new_task) {
        super(new_task);
    }

    public String fileFormat() {
        return "T" + " | " + super.fileFormat();
    }
    
    public String timeConverted() {
        return "T" + " | " + super.fileFormat();

    }
    @Override
    public String toString() {
        return "[T]" +  super.toString() ;
    }
}

