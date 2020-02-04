package org.punter;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class UnzipArchiveTest {
    private UnzipArchive unzipArchive;
    private ArrayList<String> filesNames;
    @Before
    public void setUp() throws Exception {
        unzipArchive = new UnzipArchive();
        filesNames = new ArrayList<>();
        filesNames.add("source01.csv");
        filesNames.add("source02.csv");
        filesNames.add("source03.csv");
    }

    @Test
    public void unzip() {
        Assert.assertEquals(filesNames, UnzipArchive.unzip("D:/Projects/Parser/src/main/resources/source_archive.zip"));
    }
}
