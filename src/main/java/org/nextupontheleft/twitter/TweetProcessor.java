package org.nextupontheleft.twitter;

import org.apache.log4j.Logger;
import org.nextupontheleft.mongo.NuotlCache;
import twitter4j.Status;

/**
 *
 * @author john
 */
public class TweetProcessor {

    private static final Logger logger = Logger.getLogger(TweetProcessor.class);

    private FeatureTweetProcessor featureTweetProcessor;
    private EventTweetProcessor eventTweetProcessor;
	private TwitterResponder twitterResponder;

    public TweetProcessor(NuotlCache cache, TwitterResponder twitterResponder) {
        this.featureTweetProcessor = new FeatureTweetProcessor(cache, twitterResponder);
        this.eventTweetProcessor = new EventTweetProcessor(cache, twitterResponder);
        this.twitterResponder = twitterResponder;
    }

	public void processTweet(Status tweet) {
        logger.debug("Processing tweet: " + tweet);
        if (tweet.getUser().getId() == twitterResponder.getTwitterAccountId()) {
            return;
        }
        if(this.featureTweetProcessor.isFeatureTweet(tweet)) {
            this.featureTweetProcessor.processTweet(tweet);
        } else {
            this.eventTweetProcessor.processTweet(tweet);
        }

	}
	
}
