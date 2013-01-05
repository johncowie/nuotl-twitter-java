/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.nextupontheleft.twitter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.PropertyConfiguration;

import javax.annotation.PostConstruct;

/**
 *
 * @author john
 */
@Component
public class TwitterResponder {

    @Autowired
    private PropertyConfiguration configuration;

	private Twitter twitter;
	private Long twitterId;

	@PostConstruct
	public void init() throws TwitterException {
		twitter = new TwitterFactory(configuration).getInstance();
		twitterId = twitter.getId();
	}
	
	public long getTwitterAccountId() {
		return twitterId;
	}
	
	public void replyToTweet(Status tweet, TweetParsingErrorCode errorCode) {

//		String message = "@" + tweet.getUser().getScreenName() + " " + errorCode.getMessage() + " " + constructTweetUrl(tweet);
//		System.out.println("Attempting to send: " + message);
//		StatusUpdate statusUpdate = new StatusUpdate(message);
//		statusUpdate.inReplyToStatusId(tweet.getId());
//		try {
//            if(this.configFlagProvider.isFlagEnabled(ConfigFlagId.RESPOND_TO_TWEETS)) {
//                twitter.updateStatus(statusUpdate);
//            }
//		} catch(TwitterException e) {
//			e.printStackTrace();
//		}
	}
        
        private String constructTweetUrl(Status tweet) {
            return "http://twitter.com/" + tweet.getUser().getId() + "/status/" + tweet.getId();
        }
	
}
