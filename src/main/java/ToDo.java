public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    public static ToDo load(String str) {
        String[] arr = str.split("\\|", 3);
        ToDo td = new ToDo(arr[2]);
        if (arr[1].equals("true")) {
            td.isDone = true;
        }
        return td;
    }

    @Override
    public String store() {
        return "T|" + super.store();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
