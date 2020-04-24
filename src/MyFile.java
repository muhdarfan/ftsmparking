import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class MyFile {
    public static void main(String[] args) {
        String nakBuang = "noob";
        boolean flag1 = RemoveTextFromFile("file.txt", nakBuang);

        if(flag1) {
            System.out.println("test");
        } else {
            System.out.println("ada masalah sis");
        }
    }

    private static boolean RemoveTextFromFile(String path, String word) {
        try {
            File inputFile = new File(path);
            File tempFile = new File("temp.txt");

            Scanner fileIn = new Scanner(inputFile);
            String currentLine;

            PrintWriter pw = new PrintWriter(tempFile);

            while (fileIn.hasNextLine()) {
                currentLine = fileIn.nextLine();
                if (currentLine.trim().equals(word.trim()))
                    continue;

                pw.println(currentLine);
            }

            fileIn.close();
            pw.close();

            inputFile.delete();
            tempFile.renameTo(new File(path));

            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return false;
    }
}
