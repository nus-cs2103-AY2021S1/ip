import java.util.ArrayList;

public class TaskList {
    ArrayList<String> texts;

    //constructor
    TaskList() {
        texts = new ArrayList<>();
    }

    public void acceptText(String text) {
        texts.add(text);
    }

    public ArrayList<String> retrieve() {
        return texts;
    }

}
