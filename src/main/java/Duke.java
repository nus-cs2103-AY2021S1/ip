public class Duke {
    public static void main(String[] args) {
        String logo =   "_|_|_|_|_|    _|_|                _|_|_|      _|_|     _|  \n" +
                        "    _|      _|    _|              _|    _|  _|    _|   _|  \n" +
                        "    _|      _|    _|  _|_|_|_|_|  _|    _|  _|    _|   _|  \n" +
                        "    _|      _|    _|              _|    _|  _|    _|       \n" +
                        "    _|        _|_|                _|_|_|      _|_|     _|  ";
        System.out.println("Hello from\n" + logo);
        System.out.println("This is a Duke Project.\n");

        System.out.println("What you are going to do today?");

        String filePath = "data/duke.txt";
        Storage store = new Storage(filePath);
        ToDoList todo = new ToDoList(store.load());
        todo.run();
        store.save(todo.getTodoList());
    }
}
