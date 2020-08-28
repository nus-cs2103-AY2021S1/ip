package duke.storage;

import java.util.List;

public interface Storage {
    void addLine(String saveString);

    void updateLine(int index, String saveString);

    void removeLine(int index);

    List<String> getSavedLines();
}
