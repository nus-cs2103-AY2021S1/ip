import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;

public class DialogBox extends HBox {

    /**
     * Creates a dialog box showing interaction between user and <code>Duke</code>.
     *
     * @param l the text to be displayed
     * @param iv the image to be displayed
     */
    public DialogBox(Label l, ImageView iv) {
        l.setFont(Font.font ("Verdana", 11));
        l.setTextFill(Color.web("#596186"));
        Circle clip = new Circle(50, 50, 45);
        iv.setClip(clip);

        l.setWrapText(true);
        iv.setFitWidth(100.0);
        iv.setFitHeight(100.0);

        l.setAlignment(Pos.CENTER);
        this.setAlignment(Pos.TOP_RIGHT);
        this.getChildren().addAll(l, iv);
    }

    /**
     * Flips the content in this <code>DialogBox</code>.
     */
    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }

    /**
     * Returns a <code>DialogBox</code> to be staged.
     *
     * @param l the text to be displayed
     * @param iv the image to be displayed
     * @return a <code>DialogBox</code> to be staged
     */
    public static DialogBox getUserDialog(Label l, ImageView iv) {
        return new DialogBox(l, iv);
    }

    /**
     * Returns a <code>DialogBox</code> to be staged.
     *
     * @param l the text to be displayed
     * @param iv the image to be displayed
     * @return a <code>DialogBox</code> to be staged
     */
    public static DialogBox getDukeDialog(Label l, ImageView iv) {
        var db = new DialogBox(l, iv);
        db.flip();
        return db;
    }
}
