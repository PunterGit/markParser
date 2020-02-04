package org.punter;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class FromCSVtoMarksTest {
    private  List<Marks> marks;

    @Before
    public void setUp() throws Exception {
        marks = new ArrayList<>();
        marks.add(new Marks("mark17",24));
        marks.add(new Marks("mark35",567));
        marks.add(new Marks("markft",309));
        marks.add(new Marks("mark17",24));
    }

    @Test
    public void parse() throws IOException {
        Assert.assertEquals(marks, FromCSVtoMarks.parse("D:/Projects/Parser/src/main/resources/source01.csv"));
    }
}
