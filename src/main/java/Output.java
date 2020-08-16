public class Output {
    void response(String s, TodoList todoList) {
        if (s.equals("bye")) {
            System.out.println("Bye. Hope to see you again soon!");
        } else if (s.isEmpty()) {
            System.out.println("Please enter a command");
        } else if (s.equals("list")) {
            todoList.listAllTodos();
        } else if (s.length() >= 4 && s.substring(0, 4).equals("done")) {
            int numTodos = todoList.list.size();
            int todoIndex = Integer.valueOf(s.substring(5,6));
            if (todoIndex <= numTodos && todoIndex > 0) {
                Todo selectedTodo = todoList.getTodo(todoIndex);
                selectedTodo.setDone();
                System.out.println("Nice! I've marked this task as done:\n " + selectedTodo);
            } else {
                System.out.println("Todo number not in TodoList");
            }
        }
        else {
            Todo todo = new Todo(s);
            todoList.add(todo);
            System.out.println("added: " + s);
        }
    }
}
