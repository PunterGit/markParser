package org.punter;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MarksTest {
    private Marks mark;
    @Before
    public void setUp() throws Exception {
        mark = new Marks ("mark17",72);
    }

    @Test
    public void getName() {
        Assert.assertEquals("mark17",this.mark.getName());
    }

    @Test
    public void setName() {
        mark.setName("mark18");
        Assert.assertEquals("mark18",this.mark.getName());
    }

    @Test
    public void getCount() {
        Assert.assertEquals(72,this.mark.getCount());
    }

    @Test
    public void setCount() {
        mark.setCount(33);
        Assert.assertEquals(33,this.mark.getCount());
    }

    @Test
    public void testToString() {
        Assert.assertEquals("Marks{name='mark17', count=72}",this.mark.toString());
    }
}
