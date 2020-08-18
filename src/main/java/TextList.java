import java.util.ArrayList;

public class TextList {

    private ArrayList<String> list;
    private String textIndentation = "     ";

    TextList() {
        list = new ArrayList<>();
    }

    public boolean isEmpty() {
        return list.size() == 0;
    }

    public void addToList(String message) {
        list.add(message);
    }

    public void printList() {
        int listSize = list.size();
        for (int i = 0; i < listSize; i++) {
            System.out.println(textIndentation + (i+1) + ". " + list.get(i));
        }
    }
}
