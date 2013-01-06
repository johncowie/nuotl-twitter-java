package org.nextupontheleft.twitter;

import org.apache.log4j.Logger;
import org.nextupontheleft.domain.Approved;
import org.nextupontheleft.domain.Tweeter;
import org.nextupontheleft.mongo.MongoCache;
import org.nextupontheleft.mongo.MongoDB;
import twitter4j.FilterQuery;
import twitter4j.TwitterException;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.conf.PropertyConfiguration;

import java.io.FileInputStream;

public class TwitterListenerBean {

    private static final Logger logger = Logger.getLogger(TwitterListenerBean.class);
    private PropertyConfiguration configuration;
    private PersistenceStatusListener statusListener;
    private TweetRecoverer tweetRecoverer;
    private MongoCache mapper;


    private TwitterStream twitterStream;

    public TwitterListenerBean() throws Exception {
        this.configuration = new PropertyConfiguration(new FileInputStream("/Users/john/Dropbox/twitter4j.properties"));
        this.mapper = new MongoCache();
        TweetProcessor tweetProcessor = new TweetProcessor(this.mapper, new TwitterResponder(this.configuration));
        this.statusListener = new PersistenceStatusListener(tweetProcessor);
        this.tweetRecoverer = new TweetRecoverer(tweetProcessor, this.configuration);
    }

    public void startUp() throws TwitterException {
        twitterStream = new TwitterStreamFactory(configuration).getInstance();
        logger.debug("Starting recovery...");
        // tweetRecoverer.recoverTweets(mapper.getMaxTweetId());
        logger.debug("Finishing recovery...");
        logger.debug("Starting up");
        twitterStream.addListener(statusListener);
        long userId = twitterStream.getId();
        long[] follows = {userId};
        FilterQuery filter = new FilterQuery(follows);
        twitterStream.filter(filter);
    }

    public void shutDown() {
        twitterStream.cleanUp();
        twitterStream.shutdown();
    }

    public static void main(String[] args) throws Exception {
        Tweeter tweeter = new Tweeter(578502168, "johncowiedev", "John Cowie", Approved.Y);
        MongoDB.getInstance().save(tweeter);
        TwitterListenerBean bean = new TwitterListenerBean();
        bean.startUp();
    }
}
