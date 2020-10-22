

public class Event extends Task{
    String timeFrame;

    public Event(String userInput) throws eventException {
        String[] handledUserInput = handleUserInput(userInput);
        this.description = handledUserInput[0];
        this.timeFrame = handledUserInput[1];
    }

    public Event(String description, String timeFrame, boolean isDone) throws eventException {
        this.description = description;
        this.timeFrame = timeFrame;
        this.isDone = isDone;
    }

    private String[] handleUserInput(String userInput) throws eventException{
        eventException properFormatAdvisoryException = new eventException("Please provide event in the following " +
                "format: <description /at time-frame>");
        if (userInput.equals("event")) throw properFormatAdvisoryException;
        String[] StringArr = userInput.split(" /at");
        if (StringArr.length != 2) throw properFormatAdvisoryException;
        StringArr[0] = StringArr[0].replace("event ", "");
        return StringArr;
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " ~ " + this.timeFrame;
    }

    @Override
    public String getStatusIcon() {
        return (isDone ? "[\u2713]" : "[\u2718]"); //return tick or X symbols
    }

    @Override
    public String toString() {
        String returnString = "";
        returnString += "[E]" + getStatusIcon() + this.description + " (at:" + this.timeFrame + ")";
        if (!duration.equals("")) returnString += " (duration: " + this.duration + ")";
        return returnString;
    }
}
