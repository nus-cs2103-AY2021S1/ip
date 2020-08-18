package service;

import parser.Flag;

import java.util.Map;

public class EventTask extends Task {
    public static final String taskWord = "event";

    private String description;
    private String time;

    public EventTask(String[] tokens) {
        super(tokens);
    }

    @Override
    public void parse() throws Exception {
        Map<Flag, String> flags = Flag.parseFlags(tokens);

        if (!flags.containsKey(Flag.AT)) {
            throw new Exception("Flag \\at not found");
        }

        if (!flags.containsKey(Flag.NONFLAG)) {
            throw new Exception("Need description");
        }

        this.description = flags.get(Flag.NONFLAG);
        this.time = flags.get(Flag.AT);
    }

    public String getDescription() {
        return this.description + " (at" + this.time + ")";
    }
}
