public class Deadline extends TimedTask{
    Deadline(String desc,String date){
        super(desc, date);
    }
    @Override
    public String toString(){
        return "[D]" + super.toString()+" (by: "+getDateby()+")";
    }
}
