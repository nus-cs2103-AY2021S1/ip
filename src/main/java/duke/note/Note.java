package duke.note;

import java.time.LocalDateTime;

public class Note {
    private String title;
    private String description;
    
    public Note(String title, String description) {
        this.title = title;
        this.description = description;
    }
    
    public String getDescription() {
        return this.description;
    }

    public String writeMessage() {
        return String.format("%s | %s", this.title, this.description);
    }
    
    @Override
    public String toString() {
        return this.title;
    }
    
}
