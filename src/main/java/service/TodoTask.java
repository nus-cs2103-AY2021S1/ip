package service;

import exceptions.InvalidCommandException;

import java.util.Optional;

public class TodoTask extends Task {
    public static final String taskWord = "todo";

    private String description;

    public TodoTask(String[] tokens) {
        super(tokens, taskWord);
    }

    @Override
    public void parse() throws InvalidCommandException {
        Optional<String> optDesc = java.util.Arrays.stream(super.tokens, 0, super.tokens.length)
                                    .reduce((a, b) -> a + " " + b);
        if (optDesc.isEmpty()) {
            throw new InvalidCommandException("Description must not be empty");
        } else {
            this.description = optDesc.get();
        }
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public String toString() {
        return "[T]" + this.getStatusIcon() + " " + this.getDescription();
    }
}
