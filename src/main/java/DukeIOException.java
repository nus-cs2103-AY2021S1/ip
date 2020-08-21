public class DukeIOException extends DukeException {
    public DukeIOException(String msg) {
        super(msg);
    }

    @Override
    public void printError() {
        StringUtils.printWithWrapper(new String[]{"[IO ERROR]: " + getMessage()}, false);
    }
}
