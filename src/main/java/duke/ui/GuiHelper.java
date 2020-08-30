package duke.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Container for messages from Duke to the GUI.
 */
public class GuiHelper implements Ui {
    private boolean isActive = false;
    private String userInput;
    private List<String> commandOutput;
    private boolean isNotConsumed = false;

    public GuiHelper() {
        this.commandOutput = new ArrayList<>();
    }

    @Override
    public void start() {
        this.isActive = true;
    }

    @Override
    public void close() {
        this.isActive = false;
    }

    @Override
    public boolean isActive() {
        return isActive;
    }

    @Override
    public String nextLine() {
        return this.userInput;
    }

    @Override
    public void systemMessage(String input) {
        this.isNotConsumed = true;
        this.commandOutput.add(input);
    }

    /**
     * Returns the output from the Duke Command if any,
     * else returns Optional.empty().
     * If an output is returned, will mark it as 'consumed',
     * and subsequent calls to consumeCommandOutput() will return
     * Optional.empty() until new a new Command from Duke is run.
     *
     * @return Output from Duke Command if this is the first invocation
     * and output from the Command is not empty, Optional.empty() otherwise.
     */
    public Optional<List<String>> consumeCommandOutput() {
        if (this.isNotConsumed) {
            List<String> result = this.commandOutput;
            this.isNotConsumed = false;
            this.commandOutput = new ArrayList<>();
            return Optional.of(result);
        } else {
            return Optional.empty();
        }
    }

    public void setUserInput(String input) {
        this.userInput = input;
    }

}
