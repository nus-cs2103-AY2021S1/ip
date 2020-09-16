package duke;

/**
 * A parser object that makes sense of all the user input.
 */

public class Parser {

    Parser() { }

    /**
     * split up the user input and recognise the executable functions.
     * @param input user input.
     * @return command type.
     */
    public Command parse(String input) {
        if (input.contains("list")) {
            return Command.PRINT_TASKS;
        } else if (input.contains("bye")) {
            return Command.BYE;
        } else {
            try {
                String key = input.split(" ", 2)[0];
                if (key.contains("done")) {
                    return Command.DONE;
                } else if (key.equals("find")) {
                    return Command.FIND;
                } else if (key.equals("delete")) {
                    return Command.DELETE;
                } else if (key.equals("todo")) {
                    return Command.TODO;
                } else if (key.equals("event")) {
                    return Command.EVENT;
                } else if (key.equals("deadline")) {
                    return Command.DEADLINE;
                } else if (key.equals("prioritise")) {
                    return Command.PRIORITISE;
                }else {
                    return Command.ERROR;
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                return Command.ERROR;
            }
        }
    }
}
