public class NoFolderException extends ClippyException {
    NoFolderException() {
        super("Folder does not exists yet, new 'data' folder created under root");
    }
}
