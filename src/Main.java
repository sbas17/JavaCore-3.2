import java.io.*;
import java.sql.Array;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {
    public static void main (String[] args) {
        GameProgress one = new GameProgress(98, 5, 79, 251.3);
        GameProgress two = new GameProgress(33, 42, 123, 895.0);
        GameProgress three = new GameProgress(100, 55, 200, 1000.0);


        saveGame("E://NWork/Netology/JavaCore/_3.1/Games/savegames/save1.dat", one);
        saveGame("E://NWork/Netology/JavaCore/_3.1/Games/savegames/save2.dat", two);
        saveGame("E://NWork/Netology/JavaCore/_3.1/Games/savegames/save3.dat", three);

        ArrayList<String> listOfObjects = new ArrayList<>();
        listOfObjects.add("E://NWork/Netology/JavaCore/_3.1/Games/savegames/save1.dat");
        listOfObjects.add("E://NWork/Netology/JavaCore/_3.1/Games/savegames/save2.dat");
        listOfObjects.add("E://NWork/Netology/JavaCore/_3.1/Games/savegames/save3.dat");


        zipFiles("E://NWork/Netology/JavaCore/_3.1/Games/savegames/zipfile.zip", listOfObjects);


    }

    public static void saveGame(String path, GameProgress object) {
        try (FileOutputStream fos = new FileOutputStream(path);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(object);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void zipFiles (String path, ArrayList<String> listOfObjects) {
        for (int i = 0; i < listOfObjects.size(); i++) {
            try (ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(path));
                 FileInputStream fis = new FileInputStream(listOfObjects.get(i))) {
                ZipEntry entry = new ZipEntry("packed" + i  );
                zos.putNextEntry(entry);
                byte[] buffer = new byte[fis.available()];
                fis.read(buffer);
                zos.write(buffer);
                zos.closeEntry();
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}
