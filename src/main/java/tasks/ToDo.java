package tasks;

class ToDo extends Task {
    ToDo(String desc){
        super(desc,false);
    }
    @Override
    public String toString(){
        return "[T]"+super.toString();
    }
    
    public String saveTask(){
        return "T" +SEP+super.saveTask();
    }
}
