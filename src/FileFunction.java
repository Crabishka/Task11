import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileFunction {

    public static String readStringFromFile(String filename) throws FileNotFoundException {
        StringBuilder stringBuilder = new StringBuilder();
        Scanner scanner = new Scanner(new File(filename), "UTF-8");
        while (scanner.hasNextLine()) {
            stringBuilder.append(scanner.nextLine() + "\n");
        }
        return stringBuilder.toString();
    }

    public static void writeTextIntoFile(String filename, String text) throws IOException {
        FileWriter fileWriter = new FileWriter(filename, false);
        fileWriter.append(text);
        fileWriter.close();
    }
}
