/**
 * deals with making sense of the user command
 */

public class Parser {
    public String getResponse(String input, TaskList tasks) {
        String firstWord = input.split(" ")[0];

        try {
            if (firstWord.equals("list")) {
                return tasks.returnList();
            } else if (firstWord.equals("done")) {
                int i = Integer.valueOf(input.substring(5));
                return (tasks.done(i));
            } else if (firstWord.equals("delete")) {
                int i = Integer.valueOf(input.substring(7));
                return tasks.delete(i);
            } else if (firstWord.equals("todo")) {
                return tasks.add(new ToDo(input));
            } else if (firstWord.equals("deadline")) {
                return tasks.add(new Deadline(input));
            } else if (firstWord.equals("event")) {
                return tasks.add(new Event(input));
            } else if (firstWord.equals("find")) {
                return tasks.find(input.substring(5));
            } else {
                throw new DukeException("oops! im sorry, but i do not know what that means :-(");
            }
        } catch (EmptyDescriptionException e) {
            return e.getMessage();
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}
