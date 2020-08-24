public class Event extends Task{
    String preposition;
    String dateTime;
    Event(String title, String preposition, String dateTime) {
        super(title);
        this.preposition = preposition;
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return "[E] " + super.toString() + "(" + preposition + ": " + dateTime + ")";
    }


}
