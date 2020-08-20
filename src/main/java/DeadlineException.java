public class DeadlineException {
    boolean description;
    boolean time;
    DeadlineException(boolean description, boolean time){
        this.description = description;
        this.time = time;
    }
    public String toString(){
        if(this.description){
            return "  '\u2639' OOPS!!! The description of a deadline cannot be empty.\n" +
                    "  ____________________________________________________________";
        }
        return "  '\u2639' OOPS!!! The specific date/time of a deadline cannot be empty.\n" +
                "  ____________________________________________________________";
    }
}
