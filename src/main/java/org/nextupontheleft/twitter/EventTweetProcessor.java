package org.nextupontheleft.twitter;

import org.nextupontheleft.domain.Approved;
import org.nextupontheleft.domain.Event;
import org.nextupontheleft.domain.Tweeter;
import org.nextupontheleft.mongo.NuotlCache;
import twitter4j.Status;

/**
 * Created with IntelliJ IDEA.
 * User: john
 * Date: 09/01/2013
 * Time: 22:59
 * To change this template use File | Settings | File Templates.
 */
public class EventTweetProcessor {

    private NuotlCache cache;
    private TwitterResponder responder;
    private EventParser parser;

    public EventTweetProcessor(NuotlCache cache, TwitterResponder responder) {
        this.cache = cache;
        this.responder = responder;
        this.parser = new EventParser();
    }

    public void processTweet(Status tweet) {
        Tweeter currentTweeter = cache.getTweeter(tweet.getUser().getId());
        TweetParsingErrorCode error = TweetParsingErrorCode.SUCCESS;
        try {
            Event event = parser.interpretTweet(tweet);
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
		this.responder.replyToTweet(tweet, error.getMessage());
    }



}
