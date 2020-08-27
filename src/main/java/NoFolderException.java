public class NoFolderException extends DukeException {
    NoFolderException() {
        super("Folder does not exists yet, new 'data' folder created under root");
    }
}
