public class DoneException extends Exception{
    boolean IDabsent;
    DoneException(boolean IDabsent){
        this.IDabsent = IDabsent;
    }

    @Override
    public String toString() {
        if(IDabsent){
            return "  '\u2639' OOPS!!! The description of a done cannot be empty.\n" +
                    "  ____________________________________________________________";
        }
        return "  '\u2639' OOPS!!! The ID is not yet defined.\n" +
                "  ____________________________________________________________";
    }
}