import exception.EmptyDescriptionException;
import exception.EmptyTimeException;
import exception.UnknownCommandException;
import exception.UnknownTaskException;
import exception.UnknownTimeException;

/**
 * Represents the Parser class, that interprets user input commands.
 */
public class Parser {

    /**
     * Interprets user input text, into understandable commands.
     *
     * @param inputText Commands input by user.
     * @return Command understood by Chatbot.
     */
    protected Command commandHandler(String inputText) {
        try {
            String[] splitArr = inputText.split(" ");
            String command = splitArr[0];

            if (command.equals("find")) {
                String keyword = splitArr[1];
                return new Command("find", keyword);
            } else if (command.equals("bye")) {
                return new Command("bye");
            } else if (command.equals("list")) {
                return new Command("list");
            } else if (command.equals("done")) {
                if (splitArr.length == 1) {
                    throw new UnknownTaskException("No task number entered");
                }
                int taskNo = Integer.parseInt(splitArr[1]) - 1;
                return new Command("done", taskNo);
            } else if (command.equals("todo")) {
                if (inputText.length() <= 5) {
                    throw new EmptyDescriptionException("No Description entered");
                }
                String description = inputText.substring(5);
                return new Command("todo", new Todo(description));

            } else if (command.equals("deadline")) {
                if (inputText.length() <= 9) {
                    throw new EmptyDescriptionException("No Description entered");
                }
                if (splitArr.length == 1) {
                    throw new UnknownTimeException("No by time added");
                }
                String[] newSplitArr = inputText.substring(9).split("/by ");
                if (newSplitArr.length == 1) {
                    throw new EmptyTimeException("No time entered");
                }
                String description = newSplitArr[0];
                String by = newSplitArr[1];
                return new Command("deadline", new Deadline(description, by));
            } else if (command.equals("event")) {
                if (inputText.length() <= 6) {
                    throw new EmptyDescriptionException("No Description entered");
                }
                if (splitArr.length == 1) {
                    throw new UnknownTimeException("No at time added");
                }
                String[] newSplitArr = inputText.substring(6).split("/at ");
                if (newSplitArr.length == 1) {
                    throw new EmptyTimeException("No time entered");
                }
                String description = newSplitArr[0];
                String at = newSplitArr[1];
                return new Command("event", new Event(description, at));
            } else if (command.equals("delete")) {
                if (splitArr.length == 1) {
                    throw new UnknownTaskException("No task number entered");
                }
                int taskNo = Integer.parseInt(splitArr[1]) - 1;
                return new Command("delete", taskNo);
            } else {
                throw new UnknownCommandException("Unknown command entered");
            }
        } catch (EmptyDescriptionException empty) {
            return new Command("error", "Mate, you've gotta let me know what you're gonna be doing.");
        } catch (UnknownCommandException com) {
            return new Command("error", "Um, are you sure that's not gibberish?");
        } catch (UnknownTimeException by) {
            return new Command("error", "You've gotta let me know the time.");
        } catch (EmptyTimeException at) {
            return new Command("error", "There has to be a time, surely. Don't leave it blank!");
        } catch (UnknownTaskException ex) {
            return new Command("error", "C'mon, I don't live in your head, you gotta tell me the task number!");
        }

    }
}
