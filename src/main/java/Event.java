class Event extends Task {

    String dateTime;

    Event(String inputText) {
        super("");
        String[] refinedInputText = inputText.substring(6).split(" /at ");
        super.description = refinedInputText[0];
        this.dateTime = refinedInputText[1];
    }

    @Override
    public String toString() {
        return "[E][" + super.getStatusIcon() + "] " + super.description + " (at: " + this.dateTime + ")";
    }
}
