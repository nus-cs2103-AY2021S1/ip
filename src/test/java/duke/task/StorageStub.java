package duke.task;

import duke.io.Storage;

public class StorageStub extends Storage {
    StorageStub() {
        super("data/duke.txt");
    }
}