package commands;

import enums.TaskEnum;
import services.DukeService;

public class AddCommand extends Command {
    private final TaskEnum taskType;

    public AddCommand(String input, TaskEnum taskType) {
        super(input);
        this.taskType = taskType;
    }

    @Override
    public void execute(DukeService dukeService) {
        switch(taskType) {
            case TODO:
                dukeService.addToDoEvent(input);
                break;
            case EVENT:
                dukeService.addEventTask(input);
                break;
            case DEADLINE:
                dukeService.addDeadlineEvent(input);
                break;
        }
    }
}
