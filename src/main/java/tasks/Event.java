package tasks;

class Event extends TimedTask {
    Event(String desc,String date){
        super(desc, date);
    }
    @Override
    public String toString(){
        return "[E]" + super.toString()+" (at: "+getDateby()+")";
    }
    
    public String saveTask(){
        return "E" +SEP+super.saveTask();
    }
}
