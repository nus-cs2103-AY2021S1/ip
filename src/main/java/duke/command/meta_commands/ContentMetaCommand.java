package duke.command.meta_commands;

import duke.command.Command;

public class ContentMetaCommand extends MetaCommand {
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
