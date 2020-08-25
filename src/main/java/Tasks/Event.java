package Tasks;

import Exceptions.DukeDateTimeException;
import Tasks.TimedTask;

class Event extends TimedTask {
    Event(String desc,String date) throws DukeDateTimeException {
        super(desc, date);
    }
    @Override
    public String toString(){
        return "[E]" + super.toString()+" (at: "+getDateby()+")"+"You have "+timeLeft()+" days left till the event!";
    }
}
