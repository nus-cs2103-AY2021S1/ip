public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

//    public static void main(String[] args) {
//        Task todo = new Todo("read book");
//        System.out.println(todo);
//    }
}
