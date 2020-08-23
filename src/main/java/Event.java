public class Event extends Task {

    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }

//    public static void main(String[] args) {
//        Task event = new Event("attend wedding", "friday");
//        System.out.println(event);
//    }
}
