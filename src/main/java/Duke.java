import java.io.IOException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Duke  {

    public static void main(String[] args) throws IOException {
        String separationLine = "     _____________________________________________________\n";
        String indentation = "      ";
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(separationLine + logo);

        System.out.print(indentation + "Ah another dude trying to be productive. Sure. \n");
        System.out.print(indentation + "Let's see how long it takes. \n");
        System.out.print(indentation + "So, tell me, what do you want, sweetie? \n" + separationLine);

        /*IoHandler handler = new IoHandler();

        handler.handleInput();*/
    }

    String getResponse(String input) throws DukeException, IOException {
        return NewIoHandler.handleInputAndReply(input);
    }
}
