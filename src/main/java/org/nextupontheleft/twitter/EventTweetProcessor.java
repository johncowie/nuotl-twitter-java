package org.nextupontheleft.twitter;

import org.apache.log4j.Logger;
import org.nextupontheleft.domain.Approved;
import org.nextupontheleft.domain.Event;
import org.nextupontheleft.domain.Tweeter;
import org.nextupontheleft.mongo.NuotlCache;
import twitter4j.Status;

public class EventTweetProcessor {

    private static final Logger logger = Logger.getLogger(EventTweetProcessor.class);

    private NuotlCache cache;
    private TwitterResponder responder;
    private EventParser parser;

    public EventTweetProcessor(NuotlCache cache, TwitterResponder responder) {
        this.cache = cache;
        this.responder = responder;
        this.parser = new EventParser(cache);
    }

    public void processTweet(Status tweet) {
        Tweeter currentTweeter = cache.getTweeter(tweet.getUser().getId());
        TweetParsingErrorCode error = TweetParsingErrorCode.SUCCESS;
        try {
            Tweeter tweeter = new Tweeter(tweet.getUser().getId(), tweet.getUser().getScreenName(), tweet.getUser().getName(), Approved.N);
            if(currentTweeter != null) {
                tweeter.setApproved(currentTweeter.getApproved());
            }
            cache.addTweeter(tweeter);
            if(tweeter.getApproved() != Approved.Y) {
                throw new TweetParsingException(TweetParsingErrorCode.UNAUTHORISED_USER);
            }
            Event event = parser.interpretTweet(tweet);
            cache.addEvent(event);
        } catch (TweetParsingException e) {
            error = e.getErrorCode();
            logger.debug(String.format("PARSING ERROR: %s", error.getMessage()));
            // Tweeter newTweeter = new Tweeter(tweet.getUser().getId(), tweet.getUser().getScreenName(),
            // tweet.getUser().getName(), Approved.N);
            // FailedEvent failedEvent = new FailedEvent(tweet.getId(), tweet.getText(), newTweeter);
            // cache.addFailedEvent(failedEvent);
        }
		this.responder.replyToTweet(tweet, error.getMessage());
    }



}
