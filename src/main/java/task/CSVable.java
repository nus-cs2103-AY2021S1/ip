package task;

public interface CSVable<T> {

    public String toCSV();

    public T fromCSV(String csv);

}
