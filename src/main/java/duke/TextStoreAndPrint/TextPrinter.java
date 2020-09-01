package duke.textstoreandprint;


import duke.DialogBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class TextPrinter {

    private static VBox dialogBoxPointer;
    private static Image imagePointer;

    public static void setDialogBox(VBox dialogBox, Image duke) {
        dialogBoxPointer = dialogBox;
        imagePointer = duke;
    }

    public static void standardPrint(String middle) {
        dialogBoxPointer.getChildren().add(new DialogBox(
                new Label(TextStore.top + "\n" + middle + "\n" + TextStore.bottom + "\n"), new ImageView(imagePointer)));
        System.out.print(TextStore.top + "\n" + middle + "\n" + TextStore.bottom + "\n");
    }

    public static void printStandAlone(String details) {
        dialogBoxPointer.getChildren().add(new DialogBox(
                new Label(details), new ImageView(imagePointer)));
        System.out.println(details);
    }

    // Bling msgs

    public static void printTopGraphic() { System.out.print(TextStore.top + "\n"); }

    public static void printBottomGraphic() { System.out.print(TextStore.bottom + "\n"); }

    public static void printStartMessage() {
        standardPrint(TextStore.startMessage);
    }

    public static void printEndMessage() {
        standardPrint(TextStore.endMessage);
    }

    // Error msgs
    public static void printTaskNotFoundError() {
        standardPrint(TextStore.taskNotFound);
    }

    public static void printCommandNotFoundError() {
        standardPrint(TextStore.commandNotFound);
    }

    public static void printDescriptionNotFoundError() {
        standardPrint(TextStore.descriptionNotFound);
    }

    public static void printTaskNumNotSpecifiedError() {
        standardPrint(TextStore.taskNumNotSpecified);
    }

    public static void printTimeNotFoundError() {
        standardPrint(TextStore.timeNotFound);
    }

    public static void printDateTimeFormatError() {
        standardPrint(TextStore.dateTimeFormatError);
    }

    public static void printNoSearchTermError() {
        standardPrint(TextStore.noSearchTermError);
    }

    // Status and info msgs
    public static void printLoadingSave() { standardPrint(TextStore.loadingSave);}

    public static void printSaveFound() { standardPrint(TextStore.saveFound);}

    public static void printSaveNotFound() { standardPrint(TextStore.saveNotFound);}

    public static void printSavingToTextFile() {
        standardPrint(TextStore.savingToTextFile);
    }

    public static void printCommands() {
        standardPrint(TextStore.commandList);
    }

    public static void printPromptMsg() { standardPrint(TextStore.promptMsg); }
}
