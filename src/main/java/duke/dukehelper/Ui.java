package duke.dukehelper;

import duke.task.Task;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

public class Ui {
    private final Button add = new Button("Add");
    private final VBox chatBox = new VBox(5);
    private List<Label> messages = new ArrayList<>();
    private ScrollPane container = new ScrollPane();
    private int index = 0;

    public ScrollPane getContainer() {
        return this.container;
    }
    public Button getAdd() {
        return this.add;
    }
    public void initChatBox(){
        container.setPrefSize(216, 400);
        container.setContent(chatBox);
        chatBox.getStyleClass().add("chatbox");
        add.setOnAction( evt-> {
            messages.add(new Label("I'm a message"));
            if (index % 2 == 0) {
                messages.get(index).setAlignment(Pos.CENTER_LEFT);
                //System.out.println("1");
            } else {
                messages.get(index).setAlignment(Pos.CENTER_RIGHT);
                //System.out.println("2");
            }
            chatBox.getChildren().add(messages.get(index));
            index++;
        });
    }

    public static void printDialog(String content) {
        System.out.println("    ----------------------------------------");
        System.out.println("    " + content + "\n");
        System.out.println("    ----------------------------------------");
    }

    /**
     * Prints all tasks
     * @param taskStorage
     */
    public static void printStoredTasks(ArrayList<Task> taskStorage) {
        String result = "Here are the tasks in your list:\n    ";
        for(int i = 0; i < taskStorage.size(); i++) {
            result += ((i + 1) + "." + taskStorage.get(i).returnStringForm());
            if(i < taskStorage.size() - 1) result += "\n    ";
        }
        printDialog(result);
    }
}
