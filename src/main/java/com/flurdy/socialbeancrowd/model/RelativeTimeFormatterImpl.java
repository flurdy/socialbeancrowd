package com.flurdy.socialbeancrowd.model;


import org.joda.time.DateTime;
import org.joda.time.Period;
import org.joda.time.format.PeriodFormatter;
import org.joda.time.format.PeriodFormatterBuilder;

public class RelativeTimeFormatterImpl implements RelativeTimeFormatter {

    private final PeriodFormatter secondsFormatter = new PeriodFormatterBuilder()
            .appendSeconds().appendSuffix(" second ago"," seconds ago").toFormatter();
    private final PeriodFormatter minutesFormatter = new PeriodFormatterBuilder()
            .appendMinutes().appendSuffix(" minute ago"," minutes ago").toFormatter();
    private final PeriodFormatter hoursFormatter = new PeriodFormatterBuilder()
            .appendHours().appendSuffix(" hour ago"," hours ago").toFormatter();
    private final PeriodFormatter daysFormatter = new PeriodFormatterBuilder()
            .appendDays().appendSuffix(" day ago"," days ago").toFormatter();
    private final PeriodFormatter weeksFormatter = new PeriodFormatterBuilder()
            .appendWeeks().appendSuffix(" week ago"," weeks ago").toFormatter();
    private final PeriodFormatter monthsFormatter = new PeriodFormatterBuilder()
            .appendMonths().appendSuffix(" month ago"," months ago").toFormatter();
    private final PeriodFormatter yearsFormatter = new PeriodFormatterBuilder()
            .appendYears().appendSuffix(" year ago"," years ago").toFormatter();

    public String format(DateTime timestamp) {
        final Period relativePeriod = new Period(timestamp,DateTime.now());
        if(DateTime.now().minusSeconds(1).isBefore(timestamp)){
            return "now";
        } else if(DateTime.now().minusMinutes(1).isBefore(timestamp)){
            return secondsFormatter.print(relativePeriod);
        } else if(DateTime.now().minusHours(1).isBefore(timestamp)){
            return minutesFormatter.print(relativePeriod);
        } else if(DateTime.now().minusDays(1).isBefore(timestamp)){
            return hoursFormatter.print(relativePeriod);
        } else if(DateTime.now().minusWeeks(1).isBefore(timestamp)){
            return daysFormatter.print(relativePeriod);
        } else if(DateTime.now().minusMonths(1).isBefore(timestamp)){
            return weeksFormatter.print(relativePeriod);
        } else if(DateTime.now().minusYears(1).isBefore(timestamp)){
            return monthsFormatter.print(relativePeriod);
        } else {
            return yearsFormatter.print(relativePeriod);
        }
    }

}
