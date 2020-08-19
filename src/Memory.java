import java.util.ArrayList;

public class Memory<T> {
    private ArrayList<T> memory;

    public Memory() {
        this.memory = new ArrayList<>();
    }

    public ArrayList<T> getMemory() {
        return this.memory;
    }

    public void addMemory(T event) {
        this.memory.add(event);
    }

    @Override
    public String toString() {
        String results = "";
        int size = this.memory.size();
        if (size != 0) {
            for (int i = 1; i < size; i++) {
                results += "    " + i + ". " + memory.get(i - 1) + "\n";
            }
            results += "    " + size + ". " + memory.get(size - 1);
<<<<<<< HEAD
=======
            return results;
>>>>>>> 6b6b3daee88ae887d705e111bebd7b4ac11543ff
        }
        return results;
    }
}
