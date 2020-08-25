package duck.task;

import duck.ui.Colour;

public class Todo extends Task {
    public Todo(String d) {
        super(d);
    }

    @Override
    public String getStatus() {
        return Colour.Yellow("[T]") + super.getStatus();
    }
}
