import java.io.*;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {
    public static void main (String[] args) throws FileNotFoundException {
        GameProgress one = new GameProgress(98, 5, 79, 251.3);
        GameProgress two = new GameProgress(33, 42, 123, 895.0);
        GameProgress three = new GameProgress(100, 55, 200, 1000.0);


        saveGame("E://NWork/Netology/JavaCore/_3.1/Games/savegames/save1.dat", one);
        saveGame("E://NWork/Netology/JavaCore/_3.1/Games/savegames/save2.dat", two);
        saveGame("E://NWork/Netology/JavaCore/_3.1/Games/savegames/save3.dat", three);


        List<String> listOfZipFiles = new ArrayList<>();
        listOfZipFiles.add("E://NWork/Netology/JavaCore/_3.1/Games/savegames/save1.dat");
        listOfZipFiles.add("E://NWork/Netology/JavaCore/_3.1/Games/savegames/save2.dat");
        //listOfZipFiles.add("E://NWork/Netology/JavaCore/_3.1/Games/savegames/save3.dat");


        zipFiles("E://NWork/Netology/JavaCore/_3.1/Games/savegames/zipfile.zip", listOfZipFiles);

        File save3 = new File("E://NWork/Netology/JavaCore/_3.1/Games/savegames/save3.dat");
        if (save3.delete()) {
            System.out.println("Удален файл " + save3);
        }
    }

    public static void saveGame(String path, GameProgress object) {
        try (FileOutputStream fos = new FileOutputStream(path);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(object);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void zipFiles (String path, List<String> listOfObjects) throws FileNotFoundException {
        try (ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(path))) {
            for (int i = 0; i < listOfObjects.size(); i++) {
                try (FileInputStream fis = new FileInputStream(listOfObjects.get(i))) {
                    ZipEntry entry = new ZipEntry("save" + (i + 1) + ".dat");
                    zos.putNextEntry(entry);
                    byte[] buffer = new byte[fis.available()];
                    fis.read(buffer);
                    zos.write(buffer);
                    zos.closeEntry();
                } catch (Exception ex) {
                    throw new FileNotFoundException();
                }
            }
        } catch (Exception ex) {
            throw new FileNotFoundException();
        }

    }
}
