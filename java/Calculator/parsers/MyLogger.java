package parsers;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MyLogger {
    private final FileWriter writer;

    public MyLogger(String filename) throws IOException {
        String filePrefix = "C:\\Users\\User\\IdeaProjects\\Calculator\\src\\parsers\\";
        SimpleDateFormat formatter = new SimpleDateFormat("dd:MM:yyyy::HH:mm:ss");
        Date date = new Date();
        String fileName = filePrefix + filename;
        File logFile = new File(fileName);
        if (!logFile.exists())
            logFile.createNewFile();
        writer = new FileWriter(logFile, true);
        writer.write(formatter.format(date) + "\n");
    }

    public void logging(String record) throws IOException {
        writer.write(record + "\n");
    }

    public void logging(Command com) throws IOException {
        if (com.getType() != null)
            writer.write(com.getType() + " executed\n");
    }

    public void close() throws IOException {
        writer.close();
    }
}
