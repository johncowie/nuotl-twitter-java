package org.nextupontheleft.twitter;

import twitter4j.*;
import twitter4j.conf.Configuration;

/**
 *
 * @author john
 */
public class TweetRecoverer {

    private TweetProcessor tweetProcessor;
    
    private Twitter twitter;

    public TweetRecoverer(TweetProcessor tweetProcessor, Configuration configuration) {
        this.twitter = new TwitterFactory(configuration).getInstance();
        this.tweetProcessor = tweetProcessor;
    }
    
    /**
     * TODO: Check whether there is a race condition - this code needs to be run before any other tweets are received.
     * Maybe use the recover in the startup of the listener?
     */
    public void recoverTweets(long lastTweetId) throws TwitterException {
        // use twitter account to search through pages of tweets until one is found that is <= lastTweetId
        System.out.println("Last Tweet ID: " + lastTweetId);
        int pageId = 1;
        long currentTweetId = Long.MAX_VALUE;
        while(currentTweetId > lastTweetId) {
            System.out.println("Page " + pageId + ": ");
            ResponseList<Status> statuses = twitter.getMentions(new Paging(pageId));
            if(statuses == null || statuses.isEmpty()) {
                break;
            }
            for(Status status : statuses) {
                currentTweetId = status.getId(); 
                if(currentTweetId > lastTweetId) {
                    this.tweetProcessor.processTweet(status);
                } else {
                    break;
                }
            }
            pageId++;
        }
        
    }
    
}
