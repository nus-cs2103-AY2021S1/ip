// CommandHistoryView.java
// Copyright (c) 2020, zhiayang, Apache License 2.0.

package ikura.gui.components;

import ikura.gui.GuiFrontend;

import javafx.scene.text.Font;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Priority;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.collections.ObservableList;

/**
 * A View that shows the output of the bot as lines of text.
 */
public class CommandHistoryView extends VBox {

    private final ObservableList<String> history;

    /**
     * Constructs a new CommandHistoryView, linked to the given frontend
     *
     * @param frontend the frontend to use
     */
    public CommandHistoryView(GuiFrontend frontend) {
        this.history = frontend.getOutputLog();

        var list = new ListView<String>(this.history);
        list.setFocusTraversable(false);

        // imagine needing to write 14 lines of code just so you can change the
        // font of the things inside the listview.
        list.setCellFactory(cell -> {
            return new ListCell<String>() {
                @Override protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);

                    if (item != null && !empty) {
                        this.setText(item);
                        this.setFont(Font.font(14));
                    } else {
                        this.setText(null);
                    }
                }
            };
        });

        VBox.setVgrow(list, Priority.ALWAYS);

        this.getChildren().add(list);

        this.setFocusTraversable(false);
        this.setFillWidth(true);
    }
}
