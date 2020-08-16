public class Event extends Task {
    private static final String DELIMITER = "at ";
    private static final String TIMEDELIMITER = "-";
    private String startTime = "";
    private String endTime = "";
    private String dateString = "";

    public Event(String description, String dateString, String startTime, String endTime) {
        super(description);
        this.dateString = dateString;
        this.startTime = startTime;
        this.endTime = endTime;
    }


    public static Event createEvent(String input) {
        String[] separatedInput = input.split("/" + DELIMITER);
        String[] words = separatedInput[0].split(" ");
        String[] separatedTimeStrings = separatedInput[1].split(" ");
        String duration = separatedTimeStrings[separatedTimeStrings.length-1];
        String[] separatedTime = duration.split(TIMEDELIMITER);

        StringBuilder dateStringBuilder = new StringBuilder();
        for(int i = 0; i < separatedTimeStrings.length - 2; i++) {
            dateStringBuilder.append(separatedTimeStrings[i] + " ");
        }
        dateStringBuilder.append(separatedTimeStrings[separatedTimeStrings.length - 2]);
        String dateString = dateStringBuilder.toString();

        String startTime = separatedTime[0],
                endTime = separatedTime[1];
        StringBuilder newDescription = new StringBuilder();
        for(int i = 1; i < words.length - 1; i++) {
            newDescription.append(words[i]).append(" ");
        }
        newDescription.append(words[words.length - 1]);
        return new Event(newDescription.toString(),dateString,startTime,endTime);

    }

    @Override
    protected boolean isComplete() {
        return super.isComplete();
    }

    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (" + DELIMITER + ": "
                    + this.dateString + " "
                    + this.startTime + TIMEDELIMITER + this.endTime + ")" ;
    }
}
