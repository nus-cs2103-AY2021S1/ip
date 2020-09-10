package duke.gui;

import javafx.scene.control.ListCell;

/**
 * A ListCell that displays its index
 * @param <T> The Object represented by this ListCell
 */
public class IndexListCell<T> extends ListCell<T> {

    // Override the display of the ListCell
    @Override
    protected void updateItem(T item, boolean empty) {
        super.updateItem(item, empty);
        if (item == null) {
            setText("");
            return;
        }

        setText((getIndex() + 1) + ". " + item.toString());
    }
}
