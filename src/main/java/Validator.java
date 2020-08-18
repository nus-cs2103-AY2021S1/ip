public class Validator {

    protected static void info(Command command, int splitNum, boolean timeRelated) throws DukeException {
        if(splitNum < 2) {
            if(timeRelated
                    && (command == Command.DEADLINE || command == Command.EVENT)) {
                throw new DukeException("HEY!!! Feed me with time/date. I am hungry T_T");
            } else {
                throw new DukeException("HEY!!! Don't be stingy give me more information >.<");
            }
        }
    }

    protected static Command command(String command) throws IllegalArgumentException{
        try {
            return Command.valueOf(command.toUpperCase());
        } catch (IllegalArgumentException ex) {
            throw new IllegalArgumentException("Hey!!! I'm sorry, but I don't know what that means :-()");
        }
    }

    protected static int index(Command command, String strIndex, int listLen, int splitNum)
            throws DukeException, NumberFormatException {
        try {
            int index = Integer.parseInt(strIndex);

            if (index > listLen) {
                if(command == Command.DONE){
                    throw new DukeException("I don't have this work to mark as Done :>");
                } else if (command == Command.DELETE){
                    throw new DukeException("I don't have this work to Delete @_@");
                }
            } else if (splitNum < 2) {
                throw new DukeException("HEY!!! Don't be stingy give me more information >.<");
            }
            return index;
        } catch ( NumberFormatException ex) {
            throw new NumberFormatException("Please feed me with integer number ~_~");
        }
    }
}

