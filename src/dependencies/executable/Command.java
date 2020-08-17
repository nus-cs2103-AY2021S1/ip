package dependencies.executable;

public class Command<E> implements Executable {
    private E e;
    public Command(E e) {
        this.e = e;
    }
}
