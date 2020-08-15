import java.util.ArrayList;
import java.util.List;

public class Store {
    List<String> store;

    public Store() {
        List<String> store = new ArrayList<>();
    }

    public void addItem(String item) {
        this.store.add(item);
    }

    public void listItems() {
        int counter = 1;
        for (String item: this.store) {
            System.out.println(counter + ". " + item);
            counter++;
        }
    }
}
