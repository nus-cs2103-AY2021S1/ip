package duke.text;

import java.nio.file.Path;

public class TextCacher {

    private static StringBuilder store = new StringBuilder();

    /**
     * Returns the cached string in the class object and clears the cache
     * @return String that was cached
     */
    public static String getStore() {
        String temp = store.toString();
        store = new StringBuilder();
        return temp;
    }

    public static void cacheTextStandard(String middle) {
        store.append(TextStore.top).append("\n").append(middle).append("\n").append(TextStore.bottom).append("\n");
    }

    public static void cacheErrorMsg(String details) {
        store.append(TextStore.errorBling).append("\n").append(details).append("\n").append(TextStore.errorBling).append("\n");
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
