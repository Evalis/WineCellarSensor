package com.example.winecellarsensor;

import com.example.winecellarsensor.model.DateConverter;

import org.junit.Test;

import java.sql.Timestamp;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DateConverterTest {

    private long milliseconds = 1576706434993l;

    @Test
    public void timestampToDateConverterReturnsTrue(){
        assertTrue(DateConverter.toDate(milliseconds).getTime()==milliseconds);
    }

    @Test
    public void emptyTimestampToDateConverterIsEquals(){
        assertEquals(DateConverter.toDate(null),null);
    }

    @Test
    public void longToTimestampConverterReturnsTrue(){
        assertTrue(DateConverter.toTimestamp(new Date(milliseconds))==milliseconds);
    }

    @Test
    public void emptyLongToTimestampConverterReturnsTrue(){
        Date date = new Date(milliseconds);
        assertTrue(DateConverter.toTimestamp(null)==null);
    }

}
