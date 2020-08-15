import java.util.ArrayList;
import java.util.List;

public class Store {
    List<String> store;

    public Store() {
        this.store = new ArrayList<>();
    }

    public void addItem(String item) {
        this.store.add(item);
    }
}
