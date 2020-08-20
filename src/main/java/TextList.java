import java.util.ArrayList;

public class TextList {
    ArrayList<String> texts;

    //constructor
    TextList() {
        texts = new ArrayList<>();
    }

    public void acceptText(String text) {
        texts.add(text);
    }

    public ArrayList<String> retrieve() {
        return texts;
    }

}
