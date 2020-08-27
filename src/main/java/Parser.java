//package duke;
/**
 * translate command -> name and time
 */
public class Parser {

    public Parser() {

    }

    /**
     * get name
     * by command
     */
    public String getNameBy(String cmd) {
        for (int i = 0; i < cmd.length(); ++i) {
            if (cmd.charAt(i) == '/' && cmd.charAt(i + 1) == 'b' && cmd.charAt(i + 2) == 'y') {
                return cmd.substring(9, i - 1);
            }
        }
        return "";
    }

    /**
     * get deadline
     * by command
     */
    public String getDeadlineBy(String cmd) {
        for (int i = 0; i < cmd.length(); ++i) {
            if (cmd.charAt(i) == '/' && cmd.charAt(i + 1) == 'b' && cmd.charAt(i + 2) == 'y') {
                return cmd.substring(i + 4);
            }
        }
        return "";
    }

    /**
     * getname
     * at command
     */
    public String getNameAt(String cmd) {
        for (int i = 0; i < cmd.length(); ++i) {
            if (cmd.charAt(i) == '/' && cmd.charAt(i + 1) == 'a' && cmd.charAt(i + 2) == 't') {
                return cmd.substring(6, i - 1);
            }
        }
        return "";
    }

    /**
     * get time
     * at command
     */
    public String getDeadlineAt(String cmd) {
        for (int i = 0; i < cmd.length(); ++i) {
            if (cmd.charAt(i) == '/' && cmd.charAt(i + 1) == 'a' && cmd.charAt(i + 2) == 't') {
                return cmd.substring(i + 4);
            }
        }
        return "";
    }
}
