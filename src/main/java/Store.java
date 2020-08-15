import java.util.List;
import java.util.ArrayList;

public class Store {

    private List<String> store;

    Store() {
        this.store = new ArrayList<>();
    }

    public void add(String item) {
        this.store.add(item);
        System.out.printf("     added: %s\n", item);
    }

    public void listItems() {
        for (int i = 0; i < this.store.size(); i++) {
            System.out.printf("     %d. %s\n", i + 1, this.store.get(i));
        }
    }
}
