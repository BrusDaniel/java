package app;

import uml.ObjectPlus;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class DataStore {

    private static final String FILE_NAME = "system_data.bin";

    public static void save(AppData appData) throws Exception {
        if (appData == null) throw new IllegalArgumentException("App data is required!");

        ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream(FILE_NAME));

        ObjectPlus.writeExtents(stream);
        stream.writeObject(appData);

        stream.close();
    }

    public static AppData load() throws Exception {
        ObjectInputStream stream = new ObjectInputStream(new FileInputStream(FILE_NAME));

        ObjectPlus.readExtents(stream);
        AppData appData = (AppData) stream.readObject();

        stream.close();

        return appData;
    }
}