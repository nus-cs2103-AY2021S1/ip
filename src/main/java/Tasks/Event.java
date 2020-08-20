package Tasks;

import Tasks.TimedTask;

class Event extends TimedTask {
    Event(String desc,String date){
        super(desc, date);
    }
    @Override
    public String toString(){
        return "[E]" + super.toString()+" (at: "+getDateby()+")";
    }
}
