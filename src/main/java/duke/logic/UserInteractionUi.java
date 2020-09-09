package duke.logic;

/**
 * Encapsulates key functionalities of the UIManager that interacts with a User.
 * Contains functionalities to provide Strings related to User Interaction
 * as well as functionalities to print said Strings.
 */
public interface UserInteractionUi {
    void printDukeIntro();

    String getDukeIntro();

    void printDukeOutro();

    String getDukeOutro();
}
