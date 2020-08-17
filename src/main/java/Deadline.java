public class Deadline extends Task{
    private static final String DELIMITER = "by";
    private String dateString = "";

    public Deadline(String description, String dateString) {
        super(description);
        this.dateString = dateString;
    }

    // example: deadline return book /by Sunday
    public static Deadline createDeadline(String input) {
        String[] separatedInput = input.split("/" + DELIMITER);
        String dateString = separatedInput[1];
        String[] words = separatedInput[0].split(" ");
        StringBuilder newDescription = new StringBuilder();
        for(int i = 1; i < words.length - 1; i++) {
            newDescription.append(words[i]).append(" ");
        }
        newDescription.append(words[words.length - 1]);
        return new Deadline(newDescription.toString(), dateString);
    }

    @Override
    protected boolean isComplete() {
        return super.isComplete();
    }

    @Override
    public String toString() {
        return "[D]" + super.toString()
                + " (" + DELIMITER + ":" + this.dateString + ")" ;
    }
}
