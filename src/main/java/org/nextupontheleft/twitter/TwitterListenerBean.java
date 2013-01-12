package org.nextupontheleft.twitter;

import org.apache.log4j.Logger;
import org.nextupontheleft.mongo.MongoCache;
import twitter4j.TwitterException;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.conf.PropertyConfiguration;

import java.io.FileInputStream;
import java.util.Timer;
import java.util.TimerTask;

public class TwitterListenerBean {

    private static final boolean RESPOND = true;

    private static final Logger logger = Logger.getLogger(TwitterListenerBean.class);
    private PropertyConfiguration configuration;
    private PersistenceStatusListener statusListener;
    private TweetRecoverer tweetRecoverer;
    private MongoCache mapper;

    private static Timer timer;

    private TwitterStream twitterStream;

    public TwitterListenerBean(FileInputStream credentials) throws Exception {
        this.configuration = new PropertyConfiguration(credentials);
        this.mapper = new MongoCache();
        TweetProcessor tweetProcessor = new TweetProcessor(this.mapper, new TwitterResponder(this.configuration, RESPOND));
        this.statusListener = new PersistenceStatusListener(tweetProcessor);
        // this.tweetRecoverer = new TweetRecoverer(tweetProcessor, this.configuration);
    }

    public void startUp() throws TwitterException {
        twitterStream = new TwitterStreamFactory(configuration).getInstance();
//        logger.debug("Starting recovery...");
        // tweetRecoverer.recoverTweets(mapper.getMaxTweetId());
//        logger.debug("Finishing recovery...");
//        logger.debug("Starting up");
        twitterStream.addListener(statusListener);
        twitterStream.user();
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                logger.debug("Keep Alive");
            }
        }, 0, 5000);
    }

    public void shutDown() {
        // twitterStream.cleanUp();
        twitterStream.shutdown();
    }

    public static void main(String[] args) throws Exception {
        if(args.length == 0) {
            System.out.println("Need to supply path of credentials properties file as argument");
            System.exit(0);
        }
        final TwitterListenerBean bean = new TwitterListenerBean(new FileInputStream(args[0]));
        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                logger.info("Shutting down...");
                bean.shutDown();
            }
        });
        bean.startUp();
    }
}
