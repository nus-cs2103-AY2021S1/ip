class ToDo extends Task {

    ToDo(String inputText) {
        super(inputText.substring(5));
    }

    @Override
    public String toString() {
        return "[T][" + super.getStatusIcon() + "] " + super.description;
    }
}
