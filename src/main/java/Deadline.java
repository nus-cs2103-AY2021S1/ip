class Deadline extends Task {

    String dateTime;

    Deadline(String inputText) {
        super("");
        String[] refinedInputText = inputText.substring(9).split(" /by ");
        super.description = refinedInputText[0];
        this.dateTime = refinedInputText[1];
    }

    @Override
    public String toString() {
        return "[D][" + super.getStatusIcon() + "] " + super.description + " (by: " + this.dateTime + ")";
    }
}
