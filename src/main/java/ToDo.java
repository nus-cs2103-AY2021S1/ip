public class ToDo extends Task {
    public ToDo(String name){
        super(name);
    }

    public ToDo(String line, boolean isAutomated) {
        super(line, true);
    }
    @Override
    public String toString(){
        return "[T]"+ super.toString();
    }
}
