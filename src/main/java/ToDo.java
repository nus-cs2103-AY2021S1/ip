public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    private static void checkFormat(String formattedString) throws DukeException {
        String keyword = formattedString.split(" ", 2)[0];

        if (!keyword.equals("todo"))
            throw new DukeException("I can't seem to find the todo keyword");
        else if (formattedString.length() <= 5 || formattedString.substring(5).isEmpty())
            throw new DukeException("the description of todo cannot be empty");
    }

    public static ToDo create(String formattedString) throws DukeException {
        checkFormat(formattedString);
        return new ToDo(formattedString.substring(5));
    }


    @Override
    public String toString() {
        char statusIcon = this.isDone ? 'X' : ' ';
        return String.format("[%c] ToDo: %s", statusIcon, this.description);
    }
}
