package ExecutableCommands;

public class DoneCommand extends Execute {

    private String[] userInput;
    private Storage Storage;

    DoneCommand(String[] userInput) {
        this.userInput = userInput;
    }

    public String[] getUserInput() {
        return userInput;
    }

    @Override
    public String execute(TaskList taskList, UI ui) {

        try {
            Storage.save(taskList, Storage.FILE_PATH);
            String[] input = this.getUserInput();
            int index = Integer.parseInt(input[1]) - 1;
            return ui.addLines(taskList.markCompleted(index));
        } catch (Exception e) {
            return (new DukeException("Integer not detected")).toString();
        }
    }
}

