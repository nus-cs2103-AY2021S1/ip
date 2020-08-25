/**
 * Represents the Parser class, that interprets user input commands.
 */
public class Parser {

    /**
     * Interprets user input text, into understandable commands.
     * @param command Commands input by user.
     * @return Command understood by Chatbot.
     */
    protected Command commandHandler(String command) {
        try {
            if(command.split(" ")[0].equals("find")) {
                String keyword = command.split(" ")[1];
                return new Command("find", keyword);
            } else if (command.equals("bye")) {
                return new Command("bye");
            } else if (command.equals("list")) {
                return new Command("list");
            } else if (command.split(" ")[0].equals("done")) {
                if (command.split(" ").length == 1) {
                    throw new UnknownTaskException("No task number entered");
                }
                int taskNo = Integer.parseInt(command.split(" ")[1]) - 1;
                return new Command("done", taskNo);
            } else if (command.split(" ")[0].equals("todo")) {
                if (command.length() <= 5) {
                    throw new EmptyDescriptionException("No Description entered");
                }
                String description = command.substring(5);
                return new Command("todo", new Todo(description));

            } else if (command.split(" ")[0].equals("deadline")) {
                if (command.length() <= 9) {
                    throw new EmptyDescriptionException("No Description entered");
                }
                String[] splitArr = command.split("/");
                if (splitArr.length == 1) {
                    throw new UnknownTimeException("No by time added");
                }
                String description = splitArr[0].substring(9);
                if (splitArr[1].length() <= 3) {
                    throw new EmptyTimeException("No time entered");
                }
                String by = splitArr[1].substring(3);
                return new Command("deadline", new Deadline(description, by));
            } else if (command.split(" ")[0].equals("event")) {
                if (command.length() <= 6) {
                    throw new EmptyDescriptionException("No Description entered");
                }
                String[] splitArr = command.split("/");
                if (splitArr.length == 1) {
                    throw new UnknownTimeException("No by time added");
                }
                String description = splitArr[0].substring(6);
                if (splitArr[1].length() <= 3) {
                    throw new EmptyTimeException("No time entered");
                }
                String at = splitArr[1].substring(3);
                return new Command("event", new Event(description, at));
            } else if (command.split(" ")[0].equals("delete")) {
                if (command.split(" ").length == 1) {
                    throw new UnknownTaskException("No task number entered");
                }
                int taskNo = Integer.parseInt(command.split(" ")[1]) - 1;
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
