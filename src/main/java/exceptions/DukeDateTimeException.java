package exceptions;

public class DukeDateTimeException extends DukeException {
    public DukeDateTimeException(String cmd){
        super(cmd,3);
    }
    /**
     * Takes in the given bad input and the code
     * @return String
     */
    public String message(String s) {
        StringBuilder b = new StringBuilder("");
        b.append("\t Oops you did not mark your datetime! Not sure what you mean by:\n");
        b.append("\t ").append(bad_cmd).append("\n");
        b.append("\t ").append(s);
        b.append(": ").append(code.toString()).append("\n");
        b.append("\t Heres a tip, use the 'help' command to learn about my commands!\n");
        return b.toString();
    }
}
