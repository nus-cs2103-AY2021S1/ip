package exceptions;

public class DukeIndexException extends DukeException {
    int size;
    public DukeIndexException(String cmd, int size){
        super(cmd,5);
        this.size = size;
    }
    /**
     * Takes in the given bad input and the code
     * @return String
     */
    public String message(String s) {
        StringBuilder b = new StringBuilder("");
        b.append("\t Oops you requested for a index ourside the list range:\n");
        b.append("\t ").append(bad_cmd).append("\n");
        b.append("\t ").append(s);
        b.append(": ").append(code.toString()).append(" out of ").append(size).append("\n");
        b.append("\t Heres a tip, use the 'list' command to see the current tasks!\n");
        return b.toString();
    }
}
