public class ToDo extends Listing{

    public ToDo(String s) {
        super(s);
    }

    @Override
    public String toString() {
        return "[T]" + super.doneness() + this.title;
    }
}
