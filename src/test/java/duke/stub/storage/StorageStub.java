package duke.stub.storage;

import duke.storage.Storage;

import java.util.ArrayList;
import java.util.List;

public class StorageStub implements Storage {
    @Override
    public void addLine(String saveString) {

    }

    @Override
    public void removeLine(int index) {

    }

    @Override
    public void updateLine(int index, String saveString) {

    }

    @Override
    public List<String> getSavedLines() {
        return new ArrayList<>();
    }
}
