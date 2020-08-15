public class Deadline extends task {
    public Deadline(String work){
        super(work.substring(0, work.indexOf("/") - 1) +
                " (by: " + work.substring(work.indexOf("/") + 4) + ")");
    }

    public String toString(){
        return "[D]" + super.toString();
    }
}
