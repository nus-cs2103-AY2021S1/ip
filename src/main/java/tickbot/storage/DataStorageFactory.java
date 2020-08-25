package tickbot.storage;

public class DataStorageFactory {
    private static class Singleton {
        static DataStorage instance = new DataStorage();
    }

    private DataStorageFactory() { }

    public static DataStorage getInstance() {
        return Singleton.instance;
    }
}