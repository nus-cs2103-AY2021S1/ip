package seedu.duke;

/**
 * Takes care of parsing user commands.
 */
public class Parser {

    /**
     * Returns the command that the user has inputted.
     * @param input User command inputted.
     * @return Command the user wants
     * @throws DukeException If the command is invalid
     */
    public Command parse(String input) throws DukeException {
        String endCommand = "bye";
        String listCommand = "list";
        String doneCommand = "done";
        String deleteCommand = "delete";
        String toDoCommand = "todo";
        String deadlineCommand = "deadline";
        String eventCommand = "event";
        String findCommand = "find";

        if (input.equals(listCommand)) {
            return new ListCommand();
        } else if (input.equals(endCommand)) {
            return new ByeCommand();
        } else {
            String[] words = input.split(" ", 2);
            if (words.length < 2) { // some kind of error
                throw new DukeNotSureException("Huh?? What are you trying to do??");
            } else if (words[0].equals(doneCommand)) { // the case where a task is done
                return new DoneCommand(words);
            } else if (words[0].equals(deleteCommand)) { // the case where a task is deleted
                return new DeleteCommand(words);
            } else if (words[0].equals(findCommand)) {
                return new FindCommand(words);
            } else { // the case where tasks are added
                if (words[0].equals(toDoCommand)) {
                    return new AddTodo(words);
                } else if (words[0].equals(deadlineCommand)) {
                    return new AddDeadline(words);
                } else if (words[0].equals(eventCommand)) {
                    return new AddEvent(words);
                } else {
                    throw new DukeNotSureException("Man I don't know what you want? :s");
                }
            }
        }
    }
}
