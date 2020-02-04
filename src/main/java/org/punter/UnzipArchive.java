package org.punter;

import java.io.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
//распаковка архива
public class UnzipArchive {
    public static ArrayList<String> unzip(String path) {

        File file = new File(path);
        //если файла не существует или не может быть прочитан
        if (!file.exists() || !file.canRead()) {
            System.out.println("File cannot be read!");
            return null;
        }
        //список для хранения имён распакованных файлов
        ArrayList<String> filesNames = new ArrayList<>();
        try {
            ZipFile zip = new ZipFile(path);
            Enumeration entries = zip.entries();
            //пока все элементы не кончатся, распаковываем архив
            while (entries.hasMoreElements()) {
                ZipEntry entry = (ZipEntry) entries.nextElement();
                filesNames.add(entry.getName());
                //если элемент является директорией, то создаёт папку, иначе распаковывает файл
                if (entry.isDirectory()) {
                    new File(file.getParent(), entry.getName()).mkdirs();
                } else {
                    write(zip.getInputStream(entry),
                            new BufferedOutputStream(new FileOutputStream(
                                    new File(file.getParent(), entry.getName()))));
                }
            }

            zip.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //возвращаем список имён файлов
        return filesNames;
    }
    //метод распаковки
    private static void write(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[1024];
        int len;
        while ((len = in.read(buffer)) >= 0)
            out.write(buffer, 0, len);
        out.close();
        in.close();
    }
}
