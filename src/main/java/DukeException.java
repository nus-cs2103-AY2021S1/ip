public class DukeException extends Exception {
    public DukeException(String msg) {
        super(msg);
    }

    public void printError() {
        StringUtils.printWithWrapper(new String[]{"[ERROR]: " + getMessage()}, false);
    }
}
