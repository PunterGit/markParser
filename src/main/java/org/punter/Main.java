package org.punter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {
        //путь к архиву файлов
        String path = "D:/Projects/Parser/src/main/resources/source_archive.zip";
        //имена фалов, распакованных из архива
        ArrayList<String> filesNames = UnzipArchive.unzip(path);
        //список меток для второго метода парсинга
        String[] userStrMarks = {"mark01","mark17","mark23","mark35","markFV","markFX","markFT"};
        System.out.println(FromMarksToJSON.firstVersionParse(filesNames));
        System.out.println(FromMarksToJSON.secondVersionParse(filesNames, userStrMarks));
        System.out.println(FromMarksToJSON.thirdVersionParse(filesNames));
        //записываем в файлы, json строки
        try (FileWriter file = new FileWriter("D:/Projects/Parser/src/main/resources/FirstVer.json")) {
            file.write(FromMarksToJSON.firstVersionParse(filesNames));
            file.flush();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        try (FileWriter file = new FileWriter("D:/Projects/Parser/src/main/resources/SecondVer.json")) {
            file.write(FromMarksToJSON.secondVersionParse(filesNames, userStrMarks));
            file.flush();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        try (FileWriter file = new FileWriter("D:/Projects/Parser/src/main/resources/ThirdVer.json")) {
            file.write(FromMarksToJSON.thirdVersionParse(filesNames));
            file.flush();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
