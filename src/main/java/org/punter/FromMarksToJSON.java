package org.punter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FromMarksToJSON {
    //первый метод парсинга
    public static String firstVersionParse(ArrayList<String> filesNames) throws IOException {
        List<Marks> allMarks = new ArrayList<>(); //список, в котормом будут хранится все объекты, вычлененные из CSV файла
        //временная переменная
        Marks commonMark;
        String jsonStr = "{";
        //идём по каждому файлу
        for(String filesName:filesNames){
            //идём по списку объектов, которые мы достали из файла
            for(Marks mark : FromCSVtoMarks.parse("D:/Projects/Parser/src/main/resources/"+filesName)){
                //ищем, есть-ли в этом списке объект, с таким-же именем метки, если нет, то commonMark = null
                commonMark = allMarks.stream().filter(a -> a.getName().equals(mark.getName()))
                        .findFirst().orElse(null);
                //если в списке наши существующую метку, то добавляем к ней счётчик, если нет, то добавляем объект в список
                if(commonMark != null){
                    commonMark.setCount(commonMark.getCount()+mark.getCount());
                }else {
                    allMarks.add(mark);
                }
            }
        }
        //из итогового списка объектов, переносим в json строку
        for(Marks mark : allMarks){
            jsonStr+= "\""+mark.getName()+"\":"+mark.getCount() +",";
        }
        jsonStr=jsonStr.substring(0,jsonStr.length()-1);
        jsonStr+="}";
        //возвращаем json строку, обработанных данных
        return jsonStr;
    }
    //второй метод парсинга
    public static String secondVersionParse(ArrayList<String> filesNames,String[] userStrMarks) throws IOException {
        List<Marks> allMarks = new ArrayList<>();
        List<Marks> userMarks = new ArrayList<>();
        Marks commonMark;
        String jsonStr = "{";
        for(String userStrMark:userStrMarks){
            userMarks.add(new Marks(userStrMark,0));
        }
        for(String filesName:filesNames){
            for(Marks mark : FromCSVtoMarks.parse("D:/Projects/Parser/src/main/resources/"+filesName)){
                commonMark = allMarks.stream().filter(a -> a.getName().equals(mark.getName())).findFirst().orElse(null);
                if(commonMark != null){
                    commonMark.setCount(commonMark.getCount()+mark.getCount());
                }else {
                    allMarks.add(mark);
                }
            }
        }
        //проходим по пользовательским меткам и параллельно записываем в json строку
        for(Marks mark : userMarks){
            commonMark = allMarks.stream().filter(a -> a.getName().equals(mark.getName())).findFirst().orElse(null);
            if(commonMark != null){
                mark.setCount(commonMark.getCount());
                jsonStr += "\""+mark.getName()+"\":"+mark.getCount() +",";
            }else {
                jsonStr += "\""+mark.getName()+"\":null,";
            }
        }
        jsonStr = jsonStr.substring(0,jsonStr.length()-1);
        jsonStr += "}";
        return jsonStr;
    }
    //третий метод парсинга
    public static String thirdVersionParse(ArrayList<String> filesNames) throws IOException {
        List<Marks> allMarks = new ArrayList<>(); //список, в котормом будут хранится все объекты, вычлененные из CSV файла
        Marks commonMark;
        String jsonStr = "{";
        for(String filesName:filesNames){
            allMarks.addAll(FromCSVtoMarks.parse("D:/Projects/Parser/src/main/resources/" + filesName));
        }
        //сортируем массив объектов по убыванию
        Collections.sort(allMarks, Collections.reverseOrder(Marks.COMPARE_BY_COUNT));
        for (Marks mark:allMarks){
            //пропускаем метки, с счётчиком = 0
            if(mark.getCount()>0){
                //вставляем название метки и начало массива
                jsonStr += mark.getName()+":[";
                commonMark = allMarks.stream().filter(a -> a.getName().equals(mark.getName())).findFirst().orElse(null);
                while (commonMark!=null){
                    //идём по всем меткам с одинаковым именем, суммируем в одну и приравниваем к нулю другие
                    jsonStr += commonMark.getCount() +",";
                    commonMark.setCount(0);
                    commonMark = allMarks.stream().filter(a -> a.getName().equals(mark.getName())&&a.getCount()>0).findFirst().orElse(null);
                }
                jsonStr=jsonStr.substring(0,jsonStr.length()-1);
                jsonStr+="],";
            }
        }
        jsonStr=jsonStr.substring(0,jsonStr.length()-1);
        jsonStr+="}";
        return jsonStr;
    }

}
