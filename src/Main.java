import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {
    public static void main(String[] args) {
        GameProgress gameProgress1 = new GameProgress(80, 4, 55, 10.5);
        GameProgress gameProgress2 = new GameProgress(100, 7, 49,50);
        GameProgress gameProgress3 = new GameProgress(90, 5, 30, 30);

        File dirGunRunner = new File("C://Games//savegames", "GunRunner");
        dirGunRunner.mkdir();

        String adress1 ="C://Games//savegames//GunRunner//save1.dat";
        String adress2 ="C://Games//savegames//GunRunner//save2.dat";
        String adress3 ="C://Games//savegames//GunRunner//save3.dat";

        List<String> files = new ArrayList<>();
        files.add(adress1);
        files.add(adress2);
        files.add(adress3);

        saveGame(adress1,gameProgress1);
        saveGame(adress2,gameProgress2);
        saveGame(adress3,gameProgress3);

        zipFiles("C://Games//savegames//GunRunner//saves.zip", files);

        fileDelete(files.get(0));
        fileDelete(files.get(1));
        fileDelete(files.get(2));

    }

    public static void saveGame (String fileName, GameProgress gameProgress) {
        try (FileOutputStream fos = new FileOutputStream(fileName); ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(gameProgress);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void zipFiles(String zipFile, List<String> list) {
        try (ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(zipFile))) {
            for (String list2 : list ) {
                try (FileInputStream fis = new FileInputStream(list2)) {
                    ZipEntry entry = new ZipEntry(list2);
                    zos.putNextEntry(entry);
                    byte[] buffer = new byte[fis.available()];
                    fis.read(buffer);
                    zos.write(buffer);
                    zos.closeEntry();
                } catch (Exception e1) {
                    System.out.println(e1.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void fileDelete (String file) {
        Path path = Paths.get(file);
        try {
            Files.delete(path);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

}
