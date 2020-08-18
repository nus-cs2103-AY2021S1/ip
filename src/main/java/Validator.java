public class Validator {

    protected static void info(String command, int splitNum, boolean timeRelated) throws DukeException {
        if(splitNum < 2) {
            if(timeRelated
                    && (command.equals("deadline") || command.equals("event"))) {
                throw new DukeException("HEY!!! Feed me with time/date. I am hungry T_T");
            } else {
                throw new DukeException("HEY!!! Don't be stingy give me more information >.<");
            }
        }
    }

    protected static int index(String command, String strIndex, int listLen, int splitNum)
            throws DukeException, NumberFormatException {
        try {
            int index = Integer.parseInt(strIndex);

            if (index > listLen) {
                if(command.equals("done")){
                    throw new DukeException("I don't have this work to mark as Done :>");
                } else if (command.equals("delete")){
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
