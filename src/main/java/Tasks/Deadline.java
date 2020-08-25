package Tasks;


import Exceptions.DukeDateTimeException;

class Deadline extends TimedTask {
    Deadline(String desc,String date) throws DukeDateTimeException {
        super(desc, date);
    }
    @Override
    public String toString(){
        return "[D]" + super.toString()+" (by: "+getDateby()+")"+ "You have "+timeLeft()+" days left till its due!";
    }
}
