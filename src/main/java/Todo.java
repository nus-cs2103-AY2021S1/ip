public class Todo extends Task {
    Todo(String s) {
        super(s);
    }

    Todo(String name, boolean completed) {
        super(name, completed);
    }

    @Override
    String getType() {
        return "todo";
    }

    @Override
    String getTime() {
        return "0";
    }

    @Override
    Todo completeTask() {
        return new Todo(this.name, true);
    }

    @Override
    public String toString() {
        return "[todo]" + super.toString();
    }

    @Override
    public boolean equals(Object obj) {
        Todo test = (Todo) obj;
        if (obj == this) {
            return true;
        } else if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        } else if (this.name.equals(test.name) && (this.completed == test.completed)) {
            return true;
        } else {
            return false;
        }
    }
}
