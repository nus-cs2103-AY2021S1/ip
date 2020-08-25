package commands;

import services.DukeService;

public abstract class Command {
    String input;

    public Command(String input) {
        this.input = input;
    }

    public abstract void execute(DukeService dukeService);
}
