/**
 * deals with making sense of the user command
 */

public class Parser {
    public String getResponse(String input, TaskList tasks) {
        String firstWord = input.split(" ")[0];

        try {
            switch(firstWord) {
                case "list":
                    return tasks.returnList();
                case "done":
                    int i = Integer.parseInt(input.substring(5));
                    return (tasks.done(i));
                case "delete":
                    int j = Integer.parseInt(input.substring(7));
                    return tasks.delete(j);
                case "todo":
                    return tasks.add(new ToDo(input));
                case "deadline":
                    return tasks.add(new Deadline(input));
                case "event":
                    return tasks.add(new Event(input));
                case "find":
                    return tasks.find(input.substring(5));
                default:
                    throw new DukeException("oops! im sorry, but i do not know what that means :-(");
            }
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}
