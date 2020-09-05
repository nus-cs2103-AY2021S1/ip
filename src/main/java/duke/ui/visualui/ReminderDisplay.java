package duke.ui.visualui;

import java.io.IOException;
import java.time.format.DateTimeFormatter;

import duke.parser.Parser;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

/**
 * Displays all the task that users have set on reminder.
 */
public class ReminderDisplay extends VBox {
    private static final DateTimeFormatter Format_Date_Time = DateTimeFormatter.ofPattern("EEEE, dd MMM yyyy, h:mma");
    private static final Insets INSETS = new Insets(10, 10, 0, 10);
    private static final int TEXT_SPACE__HEIGHT = 40;
    private static final int TEXT_CENTERING = 7;
    private static final String TEXT_DATE = "Date: ";
    private static final String TEXT_TIME = "Time: ";
    private static final String TEXT_EMPTY = "-";
    private static final String TEXT_COMPLETED = "Completed: ";
    private static final String TEXT_COLON = ": ";
    private static final String TEXT_COMMA = ", ";
    private static final String TEXT_TICK = "\u2714";
    private static final String TEXT_CROSS = "\u2718";
    private static final String TODO_BACKGROUND = "-fx-background-color: #648DFC;";
    private static final String EVENT_BACKGROUND = "-fx-background-color: #072AC8;";
    private static final String DEADLINE_BACKGROUND = "-fx-background-color: #8FADFD";
    private static final String DISPLAY_FONT = "Helvetica";
    private static final int DISPLAY_SIZE = 12;
    private static final String TITLE_COLOR = "#363f80";
    private static final String DESCRIPTION_COLOR = "#8E8FB5";
    @FXML
    private TextFlow description;
    @FXML
    private TextFlow isDone;
    @FXML
    private TextFlow date;
    @FXML
    private VBox bar;
    @FXML
    private TextFlow time;

    private ReminderDisplay(String ... taskDetails) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/ReminderDisplay.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
            this.getStylesheets().add("view/reminderText.css");
        } catch (IOException e) {
            e.printStackTrace();
        }
        createReminderList(taskDetails);
    }

    public static ReminderDisplay getReminderDisplay(Task task) {
        String description = task.getDescription();
        String isDone = Integer.toString(task.getTaskStatus());
        if (task instanceof ToDo) {
            return new ReminderDisplay(description, isDone, "Todo");
        } else if (task instanceof Event) {
            return new ReminderDisplay(description, isDone, "Event",
                    task.getDueDate().format(Format_Date_Time));
        } else {
            return new ReminderDisplay(description, isDone, "Deadline",
                    task.getDueDate().format(Format_Date_Time));
        }
    }

    /**
     * Creates a text array that constitute the text header of a specific color and text description of a
     * specific color
     *
     * @param title
     * @param description
     * @return
     */
    public Text[] createText(String title, String description) {
        Text text1 = new Text(title);
        text1.setFill(Color.web(TITLE_COLOR));
        text1.setFont(Font.font(DISPLAY_FONT, FontWeight.BOLD, DISPLAY_SIZE));
        Text text2 = new Text(description);
        text2.setFill(Color.web(DESCRIPTION_COLOR));
        text2.setFont(Font.font(DISPLAY_FONT, FontWeight.BOLD, DISPLAY_SIZE));
        return new Text[]{text1, text2};
    }
    public void setPadding() {
        time.setPadding(INSETS);
        date.setPadding(INSETS);
        description.setPadding(INSETS);
        isDone.setPadding(INSETS);
    }
    public void setHeight() {
        description.setPrefHeight(TEXT_SPACE__HEIGHT);
        isDone.setPrefHeight(TEXT_SPACE__HEIGHT);
        date.setPrefHeight(TEXT_SPACE__HEIGHT);
        time.setPrefHeight(TEXT_SPACE__HEIGHT);
        description.translateYProperty().setValue(TEXT_CENTERING);
        isDone.translateYProperty().setValue(TEXT_CENTERING);
        date.translateYProperty().setValue(TEXT_CENTERING);
        time.translateYProperty().setValue(TEXT_CENTERING);
    }
    public void setBarColor(String task) {
        switch (task.toLowerCase()) {
        case Parser.KEYWORD_TODO:
            bar.setStyle(TODO_BACKGROUND);
            break;
        case Parser.KEYWORD_EVENT:
            bar.setStyle(EVENT_BACKGROUND);
            break;
        case Parser.KEYWORD_DEADLINE:
            bar.setStyle(DEADLINE_BACKGROUND);
            break;
        default:
            return;
        }
    }
    /**
     * Creates the display for the list of tasks that is set on reminder.
     *
     * @param taskDetails A list of details regarding the task.
     */
    public void createReminderList(String ... taskDetails) {
        Text[] dateText;
        Text[] timeText;
        Text[] descriptionText = createText(taskDetails[2] + TEXT_COLON, taskDetails[0]);
        Text[] isDoneText = createText(TEXT_COMPLETED, taskDetails[1].equals("0") ? TEXT_TICK : TEXT_CROSS);
        if (taskDetails.length == 4) {
            String[] dateTimeArray = taskDetails[3].split(TEXT_COMMA);
            dateText = createText(TEXT_DATE, dateTimeArray[0] + TEXT_COMMA + dateTimeArray[1]);
            timeText = createText(TEXT_TIME, dateTimeArray[2]);
        } else {
            dateText = createText(TEXT_DATE, TEXT_EMPTY);
            timeText = createText(TEXT_TIME, TEXT_EMPTY);
        }
        setHeight();
        setPadding();
        date.getChildren().addAll(dateText[0], dateText[1]);
        time.getChildren().addAll(timeText[0], timeText[1]);
        description.getChildren().addAll(descriptionText[0], descriptionText[1]);
        isDone.getChildren().addAll(isDoneText[0], isDoneText[1]);
        setBarColor(taskDetails[2]);
    }
}
