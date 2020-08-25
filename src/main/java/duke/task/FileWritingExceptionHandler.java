package duke.task;

import duke.storage.FileWritingException;

public interface FileWritingExceptionHandler {
    public void handle(FileWritingException e);
}
