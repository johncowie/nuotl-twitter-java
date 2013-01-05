package org.nextupontheleft.twitter;

import org.apache.log4j.Logger;
import org.nextupontheleft.domain.*;
import org.nextupontheleft.mongo.MongoCache;
import twitter4j.Status;

/**
 *
 * @author john
 */
public class TweetProcessor {

    private static final Logger logger = Logger.getLogger(TweetProcessor.class);

	private MongoCache cache = new MongoCache();
	private EventTweetParser eventInterpreter = new EventTweetParser();
	private TwitterResponder twitterResponder = new TwitterResponder();

	public void processTweet(Status tweet) {
        logger.debug("Processing tweet: " + tweet);
        Tweeter currentTweeter = cache.getTweeter(tweet.getUser().getId());
		try {
			if (tweet.getUser().getId() == twitterResponder.getTwitterAccountId()) {
				// ignore tweets from next up on the left - just add to database
                EventResponse response = new EventResponse(tweet.getId(), tweet.getInReplyToStatusId(), tweet.getText());
                cache.addEventResponse(response);
                return;
			}
		} catch (Exception e) {
			return;
		}
		TweetParsingErrorCode error = TweetParsingErrorCode.SUCCESS;
		try {
			Event event = eventInterpreter.interpretTweet(tweet);
            cache.addEvent(event);
            if(currentTweeter == null || currentTweeter.getApproved() == null || currentTweeter.getApproved() != Approved.Y) {
                throw new TweetParsingException(TweetParsingErrorCode.UNAUTHORISED_USER);
            }
		} catch (TweetParsingException e) {
			error = e.getErrorCode();
            Tweeter newTweeter = new Tweeter(tweet.getUser().getId(), tweet.getUser().getScreenName(), 
                tweet.getUser().getName(), Approved.N);
            FailedEvent failedEvent = new FailedEvent(tweet.getId(), tweet.getText(), newTweeter);
            cache.addFailedEvent(failedEvent);
		}
		this.twitterResponder.replyToTweet(tweet, error);
	}
	
}
