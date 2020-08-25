public class Parser {
    public static Command parse(String fullCommand) throws DukeException { 
        String[] splitted = fullCommand.split("\\s+", 2);
        
        // command: bye
        if (splitted[0].equals(ExitCommand.COMMAND)) {
            return new ExitCommand();
        }
        
        // command: list
        if (splitted[0].equals(ListCommand.COMMAND)) {
            return new ListCommand();
        }
        
        // command: delete [index]
        if (splitted[0].equals(DeleteCommand.COMMAND)) {
            if (splitted.length == 0) {
                throw new InvalidIndexException();
            }
            return new DeleteCommand(splitted[1]);
        }
        
        // command: done [index]
        if (splitted[0].equals(DoneCommand.COMMAND)) {
            if (splitted.length == 0) {
                throw new InvalidIndexException();
            }
            return new DoneCommand(splitted[1]);
        }
        
        //command: find [date]
        if (splitted[0].equals(FindCommand.COMMAND)) {
            if (splitted.length == 0) {
                throw new DukeException("Missing date");
            } else {
                return new FindCommand(splitted[1]);
            }
        }
        
        // command: todo [description]
        if (splitted[0].equals(ToDoCommand.COMMAND)) {
            if (splitted.length == 1 || splitted[1].equals("")) {
                throw new InadequateCommandException("todo", new String[] {"description"});
            }
            return new ToDoCommand(splitted[1]);
        }
        
        // command: deadline [description] /by [time] 
        // or: event [description] /at [time]
        if (splitted[0].equals(DeadlineCommand.COMMAND) || splitted[0].equals(EventCommand.COMMAND)) {
            String type, timeSpecifier;
            boolean isDeadline;
            if (splitted[0].equals(DeadlineCommand.COMMAND)) {
                type = "deadline";
                timeSpecifier = DeadlineCommand.TIME_SPECIFIER;
                isDeadline = true;
            } else {
                type = "event";
                timeSpecifier = EventCommand.TIME_SPECIFIER;
                isDeadline = false;
            }
            if (splitted.length == 1) {
                throw new InadequateCommandException(type, new String[] {"description", "time"});
            } else {
                String content = splitted[1];
                String[] split2Test = content.split("\\s+");
                int timeIdx = content.indexOf(" " + timeSpecifier);
                if (split2Test.length == 0 ||
                        (split2Test.length == 1 &&
                                (split2Test[0].equals(timeSpecifier) || split2Test[0].equals(""))
                        )
                ) {
                    String[] missing = {"description", "time"};
                    throw new InadequateCommandException(type, missing);
                }
                if (timeIdx == 0 || content.indexOf(timeSpecifier) == 0) {
                    throw new InadequateCommandException(type, new String[]{"description"});
                }
                if (timeIdx == -1 || timeIdx + 5 >= content.length()) {
                    throw new InadequateCommandException(type, new String[]{"time"});
                }
                String description = content.substring(0, timeIdx);
                String time = content.substring(timeIdx + 5);
                if (time.split("\\s+").length == 0) {
                    throw new InadequateCommandException(type, new String[]{"time"});
                }
                if (isDeadline) {
                    return new DeadlineCommand(description, time);
                } else {
                    return new EventCommand(description, time);
                }
            }
        }
        return new Command();
    }
}
