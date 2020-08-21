public class Validator {

    protected static void info(Command command, int splitNum, boolean timeRelated) throws DukeException {
        if(splitNum < 2) {
            if(timeRelated
                    && (command == Command.DEADLINE || command == Command.EVENT)) {
                throw new DukeException("HEY!!! Feed me with time/date. MUG is hungry T_T");
            } else {
                throw new DukeException("HEY!!! Don't be stingy give MUG more information >.<");
            }
        }
    }

    protected static Command command(String command) throws IllegalArgumentException{
        try {
            return Command.valueOf(command.toUpperCase());
        } catch (IllegalArgumentException ex) {
            throw new IllegalArgumentException("Hey!!! I'm sorry, but MUG don't know what that means :-()");
        }
    }

    protected static int index(Command command, String strIndex, int splitNum)
            throws DukeException, NumberFormatException {
        try {
            int index = Integer.parseInt(strIndex);

            if (splitNum < 2) {
                throw new DukeException("HEY!!! Don't be stingy give MUG more information >.<");
            }
            return index;
        } catch ( NumberFormatException ex) {
            throw new NumberFormatException("Please feed MUG an integer number ~_~");
        }
    }
}

