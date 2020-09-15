package duke.text;

import java.nio.file.Path;

public class TextCacher {

    private static String store = "";

    /**
     * Returns the cached string in the class object and clears the cache
     * @return String that was cached
     */
    public static String getStore() {
        String temp = store;
        store = "";
        return temp;
    }

    public static void cacheTextStandard(String middle) {
        store += TextStore.top + "\n" + middle + "\n" + TextStore.bottom + "\n";
    }

    public static void cacheErrorMsg(String details) {
        store += TextStore.errorBling + "\n" + details + "\n" + TextStore.errorBling + "\n";
    }

    // Bling msgs

    public static void cacheStartMessage() {
        cacheTextStandard(TextStore.startMessage);
    }

    public static void cacheEndMessage() { cacheTextStandard(TextStore.endMessage);}

    // Error msgs
    public static void cacheTaskNotFoundError() {
        cacheErrorMsg(TextStore.taskNotFound);
    }

    public static void cacheCommandNotFoundError() {
        cacheErrorMsg(TextStore.commandNotFound);
    }

    public static void cacheDescriptionNotFoundError() {
        cacheErrorMsg(TextStore.descriptionNotFound);
    }

    public static void cacheTaskNumNotSpecifiedError() {
        cacheErrorMsg(TextStore.taskNumNotSpecified);
    }

    public static void cacheTimeNotFoundError() {
        cacheErrorMsg(TextStore.timeNotFound);
    }

    public static void cacheDateTimeFormatError() {
        cacheErrorMsg(TextStore.dateTimeFormatError);
    }

    public static void cacheNoSearchTermError() {
        cacheErrorMsg(TextStore.noSearchTermError);
    }

    // Status and info msgs

    public static void cacheSaveFound() { cacheTextStandard(TextStore.saveFound);}

    public static void cacheSaveNotFound() { cacheTextStandard(TextStore.saveNotFound);}

    public static void cacheTasksSavedToTextFile(Path path) {
        cacheTextStandard(TextStore.tasksSavedToTextFile + "\n" + "file saved at:\n" + path.toString());
    }

    public static void cacheCommands() {
        cacheTextStandard(TextStore.commandList);
    }

    public static void cachePromptMsg() { cacheTextStandard(TextStore.promptMsg); }
}
