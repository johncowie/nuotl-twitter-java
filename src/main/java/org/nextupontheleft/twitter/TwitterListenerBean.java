package org.nextupontheleft.twitter;

import org.apache.log4j.Logger;
import org.nextupontheleft.mongo.MongoCache;
import twitter4j.FilterQuery;
import twitter4j.TwitterException;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.conf.PropertyConfiguration;

import javax.annotation.PreDestroy;
import java.io.FileInputStream;

public class TwitterListenerBean {

    private static final Logger logger = Logger.getLogger(TwitterListenerBean.class);
    private PropertyConfiguration configuration;
    private PersistenceStatusListener statusListener = new PersistenceStatusListener();
    private TweetRecoverer tweetRecoverer = new TweetRecoverer();
    private MongoCache mapper = new MongoCache();


    private TwitterStream twitterStream;

    public TwitterListenerBean() throws Exception {
        this.configuration = new PropertyConfiguration(new FileInputStream("/Users/jcowie/Desktop/twitter4j.properties"));
        startUp();
    }

    public void startUp() throws TwitterException {
        twitterStream = new TwitterStreamFactory(configuration).getInstance();
        logger.debug("Starting recovery...");
        tweetRecoverer.recoverTweets(mapper.getMaxTweetId());
        logger.debug("Finishing recovery...");
        logger.debug("Starting up");
        twitterStream.addListener(statusListener);
        long userId = twitterStream.getId();
        long[] follows = {userId};
        FilterQuery filter = new FilterQuery(follows);
        twitterStream.filter(filter);
    }

    @PreDestroy
    public void shutDown() {
        twitterStream.cleanUp();
        twitterStream.shutdown();
    }
}
