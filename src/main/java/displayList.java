import java.util.ArrayList;

public class DisplayList {
    ArrayList<String> displayList = new ArrayList<>();

    public void add(String input) {
        displayList.add(input);
    }

    public String toString() {
        String output = "";
        if (displayList.size() > 0) {
            for (int i = 1; i < displayList.size() + 1; i++) {
                output += String.valueOf(i) + ". " + displayList.get(i - 1) + "\n";
            }
        }
        return output;
    }
}
