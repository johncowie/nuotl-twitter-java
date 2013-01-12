package org.nextupontheleft.twitter;

import org.apache.log4j.Logger;
import org.nextupontheleft.domain.FeatureTweet;
import org.nextupontheleft.mongo.NuotlCache;
import twitter4j.HashtagEntity;
import twitter4j.Status;

public class FeatureTweetProcessor {

    private static final Logger logger = Logger.getLogger(FeatureTweetProcessor.class);

    private NuotlCache cache;
    private TwitterResponder responder;

    public FeatureTweetProcessor(NuotlCache cache, TwitterResponder responder) {
        this.cache = cache;
        this.responder = responder;
    }

    public boolean isFeatureTweet(Status tweet) {
        for(HashtagEntity hashtag : tweet.getHashtagEntities()) {
            if(hashtag.getText().toLowerCase().equals("feature")) {
                return true;
            }
        }
        return false;
    }

    public void processTweet(Status tweet) {
        logger.debug("PROCESSING FEATURE TWEET: " + tweet);
        this.cache.addFeature(new FeatureTweet(tweet));
        this.responder.replyToTweet(tweet, "Cheers for the feature request!");
    }

}
