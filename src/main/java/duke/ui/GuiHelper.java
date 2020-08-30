package duke.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//TODO
public class GuiHelper implements Ui {
    private boolean isActive = false;
    private String userInput;
    private List<String> commandOutput;
    private boolean shouldPrint = false;

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
        this.shouldPrint = true;
        this.commandOutput.add(input);
    }

    public Optional<List<String>> consumeCommandOutput() {
        if (this.shouldPrint) {
            List<String> result = this.commandOutput;
            this.shouldPrint = false;
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
