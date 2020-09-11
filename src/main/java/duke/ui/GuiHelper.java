package duke.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GuiHelper implements UserInterface {
    private boolean isChatbotRunning = true;
    private String userName;
    private String userInput;
    private List<String> commandOutput;
    private boolean isNotConsumed = false;
    
    public GuiHelper() {
        this.commandOutput = new ArrayList<>();
    }

    @Override
    public void start(String userName){
        assert !this.isChatbotRunning : "GuiHelper should start only once";
        this.isChatbotRunning = true;
        this.userName = userName;
    }
    
    @Override
    public boolean isRunning() {
        return isChatbotRunning;
    }

    @Override
    public void close() {
        assert this.isChatbotRunning : "GuiHelper should only close once";
        this.isChatbotRunning = false;
        this.commandOutput.add("Goodbye " + userName + " my friend!");
    }

    @Override
    public String nextLine() {
        return this.userInput;
    }

    @Override
    public void systemMessage(String message) {
        this.isNotConsumed = true;
        this.commandOutput.add(message);
    }
    
    public Optional<List<String >> consumeCommandOutput() {
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

    public String toString() {
        return "GUI";
    }
}
