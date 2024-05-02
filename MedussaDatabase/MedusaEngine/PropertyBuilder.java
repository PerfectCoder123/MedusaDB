package MedussaDatabase.MedusaEngine;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyBuilder {

    private PropertyBuilder() {

    }
    public static Properties buildProperty(String path) {
        Properties property = new Properties();
        try {
            property.load(new FileInputStream(path));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return property;
    }

}
