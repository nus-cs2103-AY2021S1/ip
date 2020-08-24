class InadequateCommandException extends DukeException {
    private String typeOfTask;
    private String[] missingSections;

    InadequateCommandException(String typeOfTask, String[] missingSections) {
        super();
        this.typeOfTask = typeOfTask;
        this.missingSections = missingSections;
    }

    @Override
    public String getMessage() {
        String listing = "";
        for (int i = 0; i < missingSections.length - 1; i++) {
            listing += missingSections[i] + ", ";
        }
        listing += missingSections[missingSections.length - 1];
        return "OOPS, the " + listing + " of a " + typeOfTask + " cannot be empty";
    }
}