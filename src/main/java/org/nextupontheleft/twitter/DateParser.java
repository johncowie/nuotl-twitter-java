package org.nextupontheleft.twitter;

import org.joda.time.DateTimeConstants;
import org.joda.time.LocalDate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class DateParser {

    private LocalDate now;

    public DateParser() {
        this.now = new LocalDate(new Date());
    }

    public DateParser(Date now) {
        this.now = new LocalDate(now);
    }

    public DateParser(LocalDate now) {
        this.now = now;
    }

    private DateInterpreter[] dateInterpreters = {
        new TodayParser(),
        new TomorrowParser(),
        new DayOfTheWeekParser(),
        new FullDateParser()
    };

    public LocalDate parse(String dateString) throws TweetParsingException {
        for(DateInterpreter dateInterpreter : dateInterpreters) {
            try {
                return dateInterpreter.parse(dateString);
            } catch(ParseException e) {
                // Do nothing
            }
        }
        throw new TweetParsingException(TweetParsingErrorCode.DATE_ERROR);
    }
    
    private class TodayParser implements DateInterpreter {

        String[] synonyms = {"today", "2day"};

        @Override
        public LocalDate parse(String dateString) throws ParseException {
            for(String synonym : synonyms) {
                if(dateString.equalsIgnoreCase(synonym)) {
                    return now;
                }
            }
            throw new ParseException("Can not parse as today", 0);
        }

    }
    private class TomorrowParser implements DateInterpreter {
        String[] synonyms = {"tomorrow", "tomorro", "2morro", "2morrow", "2moro"};

        public LocalDate parse(String dateString) throws ParseException {

            for(String synonym : synonyms) {
                if(dateString.equalsIgnoreCase(synonym)) {
                    return now.plusDays(1);
                }
            }
            throw new ParseException("Can not parse as tomorrow", 0);
        }
    }
    private class DayOfTheWeekParser implements DateInterpreter {

        private Map<String, Integer> dayOfWeekMap = new HashMap<String, Integer>();

        public DayOfTheWeekParser() {
            dayOfWeekMap.put("monday", DateTimeConstants.MONDAY);
            dayOfWeekMap.put("tuesday", DateTimeConstants.TUESDAY);
            dayOfWeekMap.put("wednesday", DateTimeConstants.WEDNESDAY);
            dayOfWeekMap.put("thursday", DateTimeConstants.THURSDAY);
            dayOfWeekMap.put("friday", DateTimeConstants.FRIDAY);
            dayOfWeekMap.put("saturday", DateTimeConstants.SATURDAY);
            dayOfWeekMap.put("sunday", DateTimeConstants.SUNDAY);
        }

        @Override
        public LocalDate parse(String dateString) throws ParseException {
            if(dateString.length() >= 3) {
                for(String day : dayOfWeekMap.keySet()) {
                    if(day.toUpperCase().startsWith(dateString.toUpperCase())) {
                        return getNext(dayOfWeekMap.get(day));
                    }
                }
            }
            throw new ParseException("Unable to parse as day of week.", 0);
        }

        public LocalDate getNext(int dayId) {
            if (now.getDayOfWeek() >= dayId) {
                now = now.plusWeeks(1);
            }
            return now.withDayOfWeek(dayId);
        }
    }
    private class FullDateParser implements DateInterpreter {
        private SimpleDateFormat dateParser = new SimpleDateFormat("dd/MM/yyyy");
        
		@Override
        public LocalDate parse(String dateString) throws ParseException {
            return new LocalDate(dateParser.parse(dateString));
        }
    }
    interface DateInterpreter {
        public LocalDate parse(String dateString) throws ParseException;
    }
}
