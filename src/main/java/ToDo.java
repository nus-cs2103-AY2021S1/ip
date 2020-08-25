/**
 * ToDo class creates a type of Task called ToDo which contains a description of the task
 */
public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    /**
     * convertes a String into a condensed form
     * @return condesnsed form of inputted string
     */
    public String encode() {
        return String.format("T|%s|%s", super.isDone ? "Y" : "N", super.description);
    }

    /**
     * unravels encoded Strings
     * @param code String that has been previously encoded()
     * @return ToDo object
     * @throws DukeException in the event it is unable to decode the string
     */
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

    /**
     * overrides ToDo String output to be formatted
     * @return String of formatted ToDo
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
