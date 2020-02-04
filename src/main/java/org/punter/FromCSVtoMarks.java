package org.punter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
//класс перевода данных формата CSV в объекты класса Marks
public class FromCSVtoMarks {

    public static List<Marks> parse(String csvName) throws IOException {
        //Загружаем строки из файла
        List<Marks> marks = new ArrayList<Marks>();
        List<String> fileLines = Files.readAllLines(Paths.get(csvName));
        //идём по всем строкам файла
        for (String fileLine : fileLines) {
            fileLine = fileLine.toLowerCase();
            //пропускаем всё, что после #
            if(fileLine.indexOf("#") != -1){
                fileLine = fileLine.substring(0,fileLine.indexOf("#"));
            }
            //дробим строку по запятым
            String[] splitedText = fileLine.split(",");
            //записываем в список, каждый атрибут, разделённый запятой
            ArrayList<String> columnList = new ArrayList<String>();
            for (int i = 0; i < splitedText.length; i++) {
                columnList.add(splitedText[i]);
            }
            if(columnList.size()==2){
                Marks mark = new Marks(columnList.get(0),Integer.parseInt(columnList.get(1)));
                marks.add(mark);
            }
        }
        //возвращаем список объектов класса Marks
        return marks;
    }
}
