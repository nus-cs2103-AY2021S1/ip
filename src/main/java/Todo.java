public class Todo extends Task {

    public Todo(String itemString) {
        super(itemString);
    }


    @Override
    public String toString() {
        char stateSymbol = this.isDone ? DONE : NOT_DONE;
        return String.format("[T][%s] %s", stateSymbol, this.itemString);
    }

}
