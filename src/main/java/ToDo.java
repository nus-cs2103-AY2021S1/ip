public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    public String encode() {
        return String.format("T|%s|%s", super.completed ? "Y" : "N", super.description);
    }

    public static ToDo decode(String code) throws DukeException {
        if (code.charAt(0) == 'T') {
            String[] content = code.split("\\|", 3);
            if (content.length != 3) {
                throw new Error("Your data is corrupt.");
            }
            ToDo newToDo = new ToDo(content[2]);
            if (content[1].equals("Y")) {
                newToDo.setCompleted();
            }
            return newToDo;
        } else {
            throw new DukeException("Unable to decode ToDo.");
        }
    }
}
