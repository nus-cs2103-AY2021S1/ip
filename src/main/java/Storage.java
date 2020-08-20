import java.util.ArrayList;

public class Storage {

    private ArrayList<String> storage = new ArrayList<>();

    public void addItem(String string) {
        storage.add(string);
    }

    public void printOut() {
        String temp = "";
        int counter = 1;
        for (String string: storage) {
            if (counter != 1) {
                temp += "\n";
            }
            temp += counter + ". " + string;
            counter++;
        }
        Text.normalPrint(temp);
    }
}
