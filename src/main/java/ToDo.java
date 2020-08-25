public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }


    public String encode() {
        return String.format("T|%s|%s", super.isDone ? "Y" : "N", super.description);
    }

    public static ToDo decode(String code) throws DukeException {
        if (code.charAt(0) == 'T') {
            String[] content = code.split("\\|", 3);
            if (content.length != 3) {
                throw new Error("data string is not equal to 4");
            }
            ToDo newToDo = new ToDo(content[2]);
            if (content[1].equals("Y")) {
                newToDo.markAsDone();
            }
            return newToDo;
        } else {
            throw new DukeException("Unable to decode ToDo");
        }
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
