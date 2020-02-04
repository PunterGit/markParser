package org.punter;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class FromMarksToJSONTest {
    private ArrayList<String> filesNames;
    private FromMarksToJSON fromMarksToJSON;
    private String[] userStrMarks;

    @Before
    public void setUp() throws Exception {
        fromMarksToJSON = new FromMarksToJSON();
        filesNames = new ArrayList<>();
        userStrMarks = new String[]{"mark01","mark17","mark23","mark35","markFV","markFX","markFT"};
        filesNames.add("source01.csv");
        filesNames.add("source02.csv");
        filesNames.add("source03.csv");
    }

    @Test
    public void firstVersionParse() throws IOException {
        Assert.assertEquals("{\"mark17\":72,\"mark35\":1436,\"markft\":508,\"mark01\":1550,\"markfv\":105}", FromMarksToJSON.firstVersionParse(filesNames));
    }

    @Test
    public void secondVersionParse() throws IOException {
        Assert.assertEquals("{\"mark01\":1550,\"mark17\":72,\"mark23\":null,\"mark35\":1436,\"markFV\":null,\"markFX\":null,\"markFT\":null}", FromMarksToJSON.secondVersionParse(filesNames,userStrMarks));
    }

    @Test
    public void thirdVersionParse() throws IOException {
        Assert.assertEquals("{mark35:[789,567,30,25,14,11],mark01:[541,501,341,144,23],markft:[309,98,56,45],markfv:[90,8,6,1],mark17:[24,24,24]}", FromMarksToJSON.thirdVersionParse(filesNames));
    }
}
