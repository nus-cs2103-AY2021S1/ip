package duke.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Container for messages from {@link duke.Duke} to the {@link MainLauncher}
 */
public class GuiHelper implements UserInterface {
    private boolean isChatbotRunning = true;
    private String userName;
    private String userInput;
    private List<String> commandOutput;
    private boolean isNotConsumed = false;
    /**
     * Constructs helper class for GUI Interfacing
     */
    public GuiHelper() {
        commandOutput = new ArrayList<>();
    }

    @Override
    public void start(String userName) {
        assert !isChatbotRunning : "GuiHelper should start only once";
        isChatbotRunning = true;
        userName = userName;
    }
    @Override
    public boolean isRunning() {
        return isChatbotRunning;
    }

    @Override
    public void close() {
        assert isChatbotRunning : "GuiHelper should only close once";
        isChatbotRunning = false;
        commandOutput.add("Goodbye " + userName + " my friend!");
    }

    @Override
    public String nextLine() {
        return userInput;
    }

    @Override
    public void systemMessage(String message) {
        isNotConsumed = true;
        commandOutput.add(message);
    }

    /**
     * Consumes command and returns output from Duke command if any.
     * Otherwise returns Optional.empty()
     * If a output is returned, mark as "consumed"
     * and subsequent calls to consumeCommandOutput returns Optional.empty()
     * until a new {@link duke.command.CommandEnums} from Duke is run
     * @return Output from Duke Command if it is first invocation, else optional.empty.
     */
    public Optional<List<String >> consumeCommandOutput() {
        if (isNotConsumed) {
            List<String> result = commandOutput;
            isNotConsumed = false;
            commandOutput = new ArrayList<>();
            return Optional.of(result);
        } else {
            return Optional.empty();
        }
    }

    /**
     * Set user input
     * @param input userInput
     */
    public void setUserInput(String input) {
        userInput = input;
    }

    public String toString() {
        return "GUI";
    }
}
