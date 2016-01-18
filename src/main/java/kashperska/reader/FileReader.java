package kashperska.reader;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

public class FileReader {

    public List<String> readFromFile(String filePath, Charset encoding) throws IOException {
        return FileUtils.readLines(new File(filePath), encoding);
    }
}
