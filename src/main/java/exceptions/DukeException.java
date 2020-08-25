package exceptions;

/**
 * Exceptions.DukeException is a classification of errors that pertain to any running problems within Duke Class applications
 * Some errors that may occur in the hierarchy of data flow: 
 * 1. FileRead Error (WIP): For handling stored memory and I/O errors
 * 2. Bad Command Given: When a Command that is unknown is given
 * 3. No Input given: When in the flow for a given command, no description is detected
 * 4. Bad Date Given: (WIP): For handling date parsing errors
 */
public abstract class DukeException extends Exception{
    
    String bad_cmd;
    ErrorEncode code;
    public DukeException(String bad_cmd, int code){
        this.bad_cmd = bad_cmd;
        this.code = ErrorEncode.parseCode(code);
    }
    
    /**
     * Takes in the given bad input and the code
     * @return String
     */
    public String message(String s) {
        StringBuilder b = new StringBuilder("");
        b.append("\t Oops you used a invalid command! Not sure what you mean by:\n");
        b.append("\t ").append(bad_cmd).append("\n");
        b.append("\t ").append(s);
        b.append(": ").append(code.toString()).append("\n");
        b.append("\t Heres a tip, use the 'help' command to learn about my commands!\n");
        return b.toString();
    }
    @Override
    public String toString() {

        return message(super.toString());
    }
}
