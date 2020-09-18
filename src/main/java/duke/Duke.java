package duke;

import java.io.IOException;

import duke.command.CommandParser;
import duke.data.DukeCommandSet;
import duke.data.DukeState;
import duke.data.DukeTaskList;
import duke.gui.GuiResponse;
import duke.input.UserInput;
import duke.storage.DukeStorage;
import duke.storage.TaskStorage;
import duke.ui.UiResponse;

/**
 * Duke is a console program that can save tasks and modify saved tasks.
 */
public class Duke {

    protected DukeCommandSet commandSet;
    protected DukeState state;
    protected UiResponse uiResponse;
    protected GuiResponse guiResponse;
    protected CommandParser commandParser;
    protected DukeTaskList taskList;
    protected TaskStorage taskStorage;
    protected DukeStorage storage;

    /**
     * Constructs a Duke.
     */
    public Duke() {
        commandSet = new DukeCommandSet();
        state = new DukeState();
        uiResponse = new UiResponse(this);
        guiResponse = new GuiResponse(this);
        commandParser = new CommandParser();
        taskList = new DukeTaskList();
        try {
            taskStorage = new TaskStorage();
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
        storage = new DukeStorage(this);
    }

    //---------------------------------------------------------------------------

    /**
     * Sets up things at start.
     * @param useGui going to use gui or not
     */
    public void onStart(boolean useGui) {
        storage.loadSavedTasks();
        state.setUseGui(useGui);
    }

    public void onExit() throws IOException {
        storage.saveCurrentTasks();
    }

    /**
     * Gets the response string based on input.
     * @param input input string
     * @return
     */
    public String getResponse(String input) {
        commandParser.parse(input, this);
        return guiResponse.getResponse();
    }
    //---------------------------------------------------------------------------

    /**
     * Runs the Duke console program.
     * @throws IOException
     */
    public void run() throws IOException {
        onStart(false);

        uiResponse.greet();

        while (!state.getExitLoop()) {
            String inputLine = UserInput.getOneLine();
            commandParser.parse(inputLine, this);
        }

        onExit();
    }

    /**
     * The main function of Duke
     * @param args arguments
     * @throws IOException likely to be thrown when there are problems
     * loading or saving data
     */
    public static void main(String[] args) throws IOException {
        new Duke().run();
    }

    //region getters
    public DukeCommandSet getCommandSet() {
        return commandSet;
    }

    public DukeState getState() {
        return state;
    }

    public UiResponse getUiResponse() {
        return uiResponse;
    }

    public GuiResponse getGuiResponse() {
        return guiResponse;
    }

    public DukeTaskList getTaskList() {
        return taskList;
    }

    public TaskStorage getTaskStorage() {
        return taskStorage;
    }

    public DukeStorage getStorage() {
        return storage;
    }
    //endregion
}
