package core;

import java.util.List;

public class DataStructure {
    private static DataStructure instance;
    private List<String> list = null;

    public synchronized static DataStructure getInstance() {
        if (instance == null) {
            instance = new DataStructure();
        }
        return instance;
    }
}
