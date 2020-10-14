import java.util.List;

public class Note {
    private static String nameSaveSeparator = "Name: ";
    private static String contentSaveSeparator = " Content: ";

    private String content;
    private String name;

    /**
     * Creates a new note with the given name and content.
     *
     * @param name The name of the note to be added.
     * @param content The content of the note to be added.
     */
    public Note(String name, String content) {
        this.name = name;
        this.content = content;
    }

    /**
     * Creates a new note with the given name and no content.
     *
     * @param name The name of the note to be added.
     */
    public Note(String name) {
        this.name = name;
    }

    /**
     * Gets the name of the note.
     *
     * @return Name of the note.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the content of the note.
     *
     * @return Content of the note.
     */
    public String getContent() {
        return content;
    }

    /**
     * Change the content of the note.
     *
     * @param content The new content.
     */
    public void updateContent(String content) {
        this.content = content;
    }

    /**
     * Generates the save text for the note.
     *
     * @return The string representation of the note.
     */
    public String saveText() {
        return nameSaveSeparator + name + contentSaveSeparator + content;
    }

    /**
     * Reads the save text and converts it back to a list of Notes.
     *
     * @param result The list that will contain the generated Notes.
     * @param text The save text to be read.
     */
    public static void readAllText(List<Note> result, String text) {
        String[] notes = text.split(nameSaveSeparator);
        for (int i = 1; i < notes.length; i++) {
            String s = notes[i];
            String[] temp = s.split(contentSaveSeparator);
            result.add(new Note(temp[0], temp[1]));
        }
    }
}
