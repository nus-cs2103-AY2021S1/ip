import java.time.LocalDateTime;
import java.util.List;

public class Note {
    private String content;
    private String name;

    public Note(String name, String content) {
        this.name = name;
        this.content = content;
    }

    public Note(String name) {
        this.name = name;
    }

    public static void readAllText(List<Note> result, String text) {
        String[] notes = text.split(nameSaveSeparator);
        for (int i = 1; i < notes.length; i++) {
            String s = notes[i];
            String[] temp = s.split(contentSaveSeparator);
            result.add(new Note(temp[0],temp[1]));
        }
    }

    public String getName() {
        return name;
    }

    public String getContent() {
        return content;
    }

    public void updateContent(String content) {
        this.content = content;
    }

    static String nameSaveSeparator = "Name: ";
    static String contentSaveSeparator = " Content: ";

    public String saveText() {
        return nameSaveSeparator + name + contentSaveSeparator + content;
    }
}
