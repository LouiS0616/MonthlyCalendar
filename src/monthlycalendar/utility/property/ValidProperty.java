package monthlycalendar.utility.property;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;


class ValidProperty extends Property {
    ValidProperty(String propName, Path path) throws PropertyFileNotFoundException {
        propName_ = propName;

        properties_ = new Properties();
        try {
            properties_.load(Files.newInputStream(path));
        }
        catch(IOException e) {
            throw new PropertyFileNotFoundException(propName);
        }
    }

    @Override
    public String getProperty(String key) throws PropertyNotFoundException {
        String ret = properties_.getProperty(key);

        if(ret == null) {
            throw new PropertyNotFoundException(propName_, key);
        }
        return ret;
    }


    //
    private final Properties properties_;
    private final String propName_;
}
