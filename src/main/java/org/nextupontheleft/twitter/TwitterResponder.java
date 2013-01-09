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
    private boolean respondingEnabled;


	public TwitterResponder(Configuration configuration, boolean respondingEnabled) throws Exception {
		twitter = new TwitterFactory(configuration).getInstance();
		twitterId = twitter.getId();
        this.respondingEnabled = respondingEnabled;
	}
	
	public long getTwitterAccountId() {
		return twitterId;
	}
	
	public void replyToTweet(Status tweet, String text) {
		String message = "@" + tweet.getUser().getScreenName() + " " + text + " " + constructTweetUrl(tweet);
        if(respondingEnabled) {
            System.out.println("Attempting to send: " + message);
            StatusUpdate statusUpdate = new StatusUpdate(message);
            statusUpdate.inReplyToStatusId(tweet.getId());
            try {
                twitter.updateStatus(statusUpdate);
            } catch(TwitterException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Responding is disabled so didn't send the following message: [" + message + "]");
        }
	}
        
    private String constructTweetUrl(Status tweet) {
        return "http://twitter.com/" + tweet.getUser().getId() + "/status/" + tweet.getId();
    }
	
}
