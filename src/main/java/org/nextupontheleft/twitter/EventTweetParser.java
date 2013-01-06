/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.nextupontheleft.twitter;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.nextupontheleft.domain.Approved;
import org.nextupontheleft.domain.Event;
import org.nextupontheleft.domain.Tweeter;
import twitter4j.HashtagEntity;
import twitter4j.Status;
import twitter4j.URLEntity;
import twitter4j.User;

import java.util.Date;

/**
 *
 * @author john
 */
public class EventTweetParser {

	private DateParser dateParser = new DateParser();
    private TimeParser timeParser = new TimeParser();
    private DurationParser durationParser = new DurationParser();
    private AreaParser areaProvider = new AreaParser();

	public Event interpretTweet(Status tweet) throws TweetParsingException {
        if(tweet.getURLEntities().length < 1) {
            throw new TweetParsingException(TweetParsingErrorCode.NO_URL);
        }
		return interpretTweet(tweet.getId(), tweet.getText(), tweet.getUser(), tweet.getURLEntities(), tweet.getHashtagEntities());
	}

	private Event interpretTweet(long tweetId, String tweetText, User tweeter, URLEntity[] urls, HashtagEntity[] tags) throws TweetParsingException {
		EventDetails eventDetails = getEventDetails(tweetText);
        String tagsStr = "";
        for(HashtagEntity tag : tags) {
            tagsStr += tag.getText() + " ";
        }
		return new Event(tweetId, eventDetails.startDate, eventDetails.endDate, eventDetails.postalArea,
                 LinkToHtmlConverter.replaceLinksWithHtml(eventDetails.text, urls), tagsStr, new Tweeter(tweeter.getId(), tweeter.getScreenName(), tweeter.getName(), Approved.N), Approved.U);
	}

	private EventDetails getEventDetails(String tweetContent) throws TweetParsingException {

        String[] parts = tweetContent.split("\\s+");
		if(parts.length < 5) {
            throw new TweetParsingException(TweetParsingErrorCode.NOT_ENOUGH_PARTS);
        }
        String dateString = parts[1];
		String timeString = parts[2];
        String durationString = parts[3];
		String postalAreaString = parts[4];
		String remaining = "";
        for(int i = 5; i < parts.length; i++) {
            remaining += parts[i] + " ";
        }
        LocalDate date = dateParser.parse(dateString);
        LocalTime time = timeParser.parse(timeString);
        DateTime startDate = date.toDateTime(time);
		DateTime endDate = getDateWithAddedDuration(startDate, durationString);
        areaProvider.loadAreas();
        String postalArea = areaProvider.parse(postalAreaString);
			return new EventDetails(startDate.toDate(), endDate.toDate(), postalArea, remaining);
	}

    private DateTime getDateWithAddedDuration(DateTime date, String durationStr) throws TweetParsingException {
       int minutes = durationParser.getDuration(durationStr);
       return date.plusMinutes(minutes);
    }

	private static class EventDetails {

		private Date startDate;
        private Date endDate;
		private String postalArea;
		private String text;

		public EventDetails(Date startDate, Date endDate, String postalArea, String text) {
			this.startDate = startDate;
            this.endDate = endDate;
			this.text = text;
			this.postalArea = postalArea;
		}
	}
}
