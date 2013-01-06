package org.nextupontheleft.twitter;

import org.apache.log4j.Logger;
import org.nextupontheleft.domain.Approved;
import org.nextupontheleft.domain.Event;
import org.nextupontheleft.domain.EventResponse;
import org.nextupontheleft.domain.Tweeter;
import org.nextupontheleft.mongo.NuotlCache;
import twitter4j.Status;

/**
 *
 * @author john
 */
public class TweetProcessor {

    private static final Logger logger = Logger.getLogger(TweetProcessor.class);

	private NuotlCache cache;
	private EventTweetParser eventInterpreter;
	private TwitterResponder twitterResponder;

    public TweetProcessor(NuotlCache cache, TwitterResponder twitterResponder) {
        this.cache = cache;
        this.eventInterpreter = new EventTweetParser();
        this.twitterResponder = twitterResponder;
    }

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
            Tweeter tweeter = new Tweeter(tweet.getUser().getId(), tweet.getUser().getScreenName(), tweet.getUser().getName(), Approved.N);
            if(currentTweeter != null) {
                tweeter.setApproved(currentTweeter.getApproved());
            }
            cache.addTweeter(tweeter);
            if(currentTweeter == null || currentTweeter.getApproved() == null || currentTweeter.getApproved() != Approved.Y) {
                throw new TweetParsingException(TweetParsingErrorCode.UNAUTHORISED_USER);
            }
		} catch (TweetParsingException e) {
			error = e.getErrorCode();
            System.out.println(String.format("PARSING ERROR: %s", error.getMessage()));
            // Tweeter newTweeter = new Tweeter(tweet.getUser().getId(), tweet.getUser().getScreenName(),
                // tweet.getUser().getName(), Approved.N);
            // FailedEvent failedEvent = new FailedEvent(tweet.getId(), tweet.getText(), newTweeter);
            // cache.addFailedEvent(failedEvent);
		}
		this.twitterResponder.replyToTweet(tweet, error);
	}
	
}
