package clippy.exception;

public class NoFolderException extends ClippyException {
    public NoFolderException() {
        super("Folder does not exists yet, new 'data' folder created under root");
    }
}
