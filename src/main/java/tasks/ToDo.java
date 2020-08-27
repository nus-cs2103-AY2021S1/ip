package tasks;

class ToDo extends Task {
    ToDo(String desc){
        super(desc,false);
    }
    ToDo(String desc,Boolean done){
        super(desc,done);
    }
    @Override
    public String toString(){
        return "[T]"+super.toString();
    }
    
    public String saveTask(){
        return "T" +SEP+super.saveTask();
    }
}
