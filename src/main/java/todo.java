public class todo extends Task {

    todo(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}
