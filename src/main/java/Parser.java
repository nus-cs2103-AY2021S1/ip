package duke;
public class Parser {

    public Parser() {

    }

    public String getNameBy(String cmd) {
        for (int i = 0; i < cmd.length(); ++i) {
            if (cmd.charAt(i) == '/' && cmd.charAt(i + 1) == 'b' && cmd.charAt(i + 2) == 'y') {
                return cmd.substring(9, i - 1);
            }
        }
        return "";
    }

    public String getDeadlineBy(String cmd) {
        for (int i = 0; i < cmd.length(); ++i) {
            if (cmd.charAt(i) == '/' && cmd.charAt(i + 1) == 'b' && cmd.charAt(i + 2) == 'y') {
                return cmd.substring(i + 4);
            }
        }
        return "";
    }

    public String getNameAt(String cmd) {
        for (int i = 0; i < cmd.length(); ++i) {
            if (cmd.charAt(i) == '/' && cmd.charAt(i + 1) == 'a' && cmd.charAt(i + 2) == 't') {
                return cmd.substring(6, i - 1);
            }
        }
        return "";
    }

    public String getDeadlineAt(String cmd) {
        for (int i = 0; i < cmd.length(); ++i) {
            if (cmd.charAt(i) == '/' && cmd.charAt(i + 1) == 'a' && cmd.charAt(i + 2) == 't') {
                return cmd.substring(i + 4);
            }
        }
        return "";
    }
}
