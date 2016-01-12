package kashperska.reader;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class FileReader {

    public static List<String> readFromFile(String filePath, String encoding) throws IOException {
        return FileUtils.readLines(new File(filePath), encoding);
    }
}
