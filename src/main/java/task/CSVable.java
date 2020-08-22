package task;

public interface CSVable<T> {
    String toCSV();
    T fromCSV(String csv);
}
