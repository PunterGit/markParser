package org.punter;

import java.util.Comparator;

public class Marks {
    public Marks(String name, int count){
        this.name = name;
        this.count = count;
    }
    //имя метки и счётчик метки
    public String name;
    public int count;
    //getter-ы и setter-ы
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "Marks{" +
                "name='" + name + '\'' +
                ", count=" + count +
                '}';
    }
    //компоратор, для сортировки объектов
    public static final Comparator<Marks> COMPARE_BY_COUNT = new Comparator<Marks>() {
        @Override
        public int compare(Marks lhs, Marks rhs) {
            return lhs.getCount() - rhs.getCount();
        }
    };
}
