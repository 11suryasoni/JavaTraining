package OOPS;
import java.io.*;
import java.nio.file.*;
import java.util.zip.*;

public class Zipfile {

    private static void zipFiles(File[] filePaths) {
        try {
            String zipFileName = "my.zip";

            FileOutputStream fos = new FileOutputStream(zipFileName);
            ZipOutputStream zos = new ZipOutputStream(fos);

            for (File aFile : filePaths) {
                System.out.println(aFile+"   .......     ");
                zos.putNextEntry(new ZipEntry(new File(aFile.toURI()).getName()));
                byte[] bytes = Files.readAllBytes(Paths.get(aFile.toURI()));
                zos.write(bytes, 0, bytes.length);
                zos.closeEntry();
            }

            zos.close();

        } catch (FileNotFoundException ex) {
            System.err.println("A file does not exist: " + ex);
        } catch (IOException ex) {
            System.err.println("I/O error: " + ex);
        }
    }

    public static void main(String[] args) {
        File file = new File(System.getProperty("user.dir") + "Directory1");
        File[] allFiles = file.listFiles();

        assert allFiles != null;
        zipFiles(allFiles);
    }
}