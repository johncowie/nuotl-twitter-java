/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.nextupontheleft.twitter;

import twitter4j.*;
import twitter4j.conf.Configuration;

public class TwitterResponder {

	private Twitter twitter;
	private Long twitterId;

	public TwitterResponder(Configuration configuration) throws Exception {
		twitter = new TwitterFactory(configuration).getInstance();
		twitterId = twitter.getId();
	}
	
	public long getTwitterAccountId() {
		return twitterId;
	}
	
	public void replyToTweet(Status tweet, String text) {
		String message = "@" + tweet.getUser().getScreenName() + " " + text + " " + constructTweetUrl(tweet);
		System.out.println("Attempting to send: " + message);
		StatusUpdate statusUpdate = new StatusUpdate(message);
		statusUpdate.inReplyToStatusId(tweet.getId());
		try {
            twitter.updateStatus(statusUpdate);
		} catch(TwitterException e) {
			e.printStackTrace();
		}
	}
        
    private String constructTweetUrl(Status tweet) {
        return "http://twitter.com/" + tweet.getUser().getId() + "/status/" + tweet.getId();
    }
	
}
