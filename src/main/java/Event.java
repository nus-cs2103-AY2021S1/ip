public class Event extends Task{
    String preposition;
    String dateTime;
    Event(String title, String preposition, String dateTime) throws WrongUsageException{
        super(title);
        this.name = "event";
        this.usage = "event [EventName] ['/on' OR '/at'] [DateTimeString]";
        this.description = "Stores a task in the list marked as an event";
        if(title.isEmpty() || preposition.isEmpty() || dateTime.isEmpty()){
            throw new WrongUsageException(this.name, this.usage);
        }
        this.preposition = preposition;
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return "[E] " + super.toString() + "(" + preposition + ": " + dateTime + ")";
    }


}
