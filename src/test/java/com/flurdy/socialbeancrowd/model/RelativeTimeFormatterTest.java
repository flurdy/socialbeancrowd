package com.flurdy.socialbeancrowd.model;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RelativeTimeFormatterTest {

    private RelativeTimeFormatter formatter;

    @Before
    public void setUp(){
        formatter = new RelativeTimeFormatterImpl();
    }

    @Test
    public void whenFormat_givenNow_thenTextMatch() throws Exception {

        final String formattedTime = formatter.format(DateTime.now());

        assertEquals("now",formattedTime);
    }

    @Test
    public void whenFormat_given10MilliSecondsAgo_thenTextMatch() throws Exception {

        final String formattedTime = formatter.format(DateTime.now().minusMillis(19));

        assertEquals("now",formattedTime);
    }

    @Test
    public void whenFormat_given1SecondAgo_thenTextMatch() throws Exception {

        final String formattedTime = formatter.format(DateTime.now().minusSeconds(1));

        assertEquals("1 second ago",formattedTime);
    }

    @Test
    public void whenFormat_given10SecondsAgo_thenTextMatch() throws Exception {

        final String formattedTime = formatter.format(DateTime.now().minusSeconds(10));

        assertEquals("10 seconds ago",formattedTime);
    }

    @Test
    public void whenFormat_given1MinuteAgo_thenTextMatch() throws Exception {

        final String formattedTime = formatter.format(DateTime.now().minusMinutes(1));

        assertEquals("1 minute ago",formattedTime);
    }

    @Test
    public void whenFormat_given10MinutesAgo_thenTextMatch() throws Exception {

        final String formattedTime = formatter.format(DateTime.now().minusMinutes(10));

        assertEquals("10 minutes ago",formattedTime);
    }

    @Test
    public void whenFormat_given1HourAgo_thenTextMatch() throws Exception {

        final String formattedTime = formatter.format(DateTime.now().minusHours(1));

        assertEquals("1 hour ago",formattedTime);
    }

    @Test
    public void whenFormat_given10HoursAgo_thenTextMatch() throws Exception {

        final String formattedTime = formatter.format(DateTime.now().minusHours(10));

        assertEquals("10 hours ago",formattedTime);
    }

    @Test
    public void whenFormat_given1DayAgo_thenTextMatch() throws Exception {

        final String formattedTime = formatter.format(DateTime.now().minusDays(1));

        assertEquals("1 day ago",formattedTime);
    }

    @Test
    public void whenFormat_give2DaysAgo_thenTextMatch() throws Exception {

        final String formattedTime = formatter.format(DateTime.now().minusDays(2));

        assertEquals("2 days ago",formattedTime);
    }

    @Test
    public void whenFormat_give3DaysAgo_thenTextMatch() throws Exception {

        final String formattedTime = formatter.format(DateTime.now().minusDays(3));

        assertEquals("3 days ago",formattedTime);
    }

    @Test
    public void whenFormat_give6DaysAgo_thenTextMatch() throws Exception {

        final String formattedTime = formatter.format(DateTime.now().minusDays(6));

        assertEquals("6 days ago",formattedTime);
    }

    @Test
    public void whenFormat_give7DaysAgo_thenTextMatch() throws Exception {

        final String formattedTime = formatter.format(DateTime.now().minusDays(7));

        assertEquals("1 week ago",formattedTime);
    }

    @Test
    public void whenFormat_given10DaysAgo_thenTextMatch() throws Exception {

        final String formattedTime = formatter.format(DateTime.now().minusDays(10));

        assertEquals("1 week ago",formattedTime);
    }

    @Test
    public void whenFormat_given20DaysAgo_thenTextMatch() throws Exception {

        final String formattedTime = formatter.format(DateTime.now().minusDays(20));

        assertEquals("2 weeks ago",formattedTime);
    }

    @Test
    public void whenFormat_given40DaysAgo_thenTextMatch() throws Exception {

        final String formattedTime = formatter.format(DateTime.now().minusDays(40));

        assertEquals("1 month ago",formattedTime);
    }

}
