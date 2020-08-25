package duck.ui;

import java.util.List;

/**
 * Interface to be implemented for the bot to display messages
 * to the user.
 */
public interface Ui {
    void respond(List<String> messages);
}
