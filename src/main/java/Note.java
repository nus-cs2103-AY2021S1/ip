import java.util.Map;

public class Note {

    // Static Variables
    private static int count = 0;

    // Attributes
    private int id;
    private String title;
    private Priority priority;
    private String description;
    private boolean isArchived;

    // Constructor
    private Note(int id, String title, Priority priority, String description, boolean isArchived) {
        this.id = id;
        this.title = title;
        this.priority = priority;
        this.description = description;
        this.isArchived = isArchived;
    }

    // Static Factory Methods
    public static Note createNote(String title, String description, Priority priority) {
        Validator.requireNonNull(title, description, priority);
        int id = count;
        count += 1;
        return new Note(id, title, priority, description, false);
    }

    // Methods
    public void archive() {
        if (!this.isArchived) {
            this.isArchived = true;
        } else {
            throw new UnsupportedOperationException("Note is already archived.");
        }
    }

    public void editDescription(String newDescription) {
        this.description = newDescription;
    }

    public void editTitle(String newTitle) {
        this.title = title;
    }

}
