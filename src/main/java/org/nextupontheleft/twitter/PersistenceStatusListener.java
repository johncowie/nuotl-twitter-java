/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.nextupontheleft.twitter;

import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;

/**
 *
 * @author john
 */
public class PersistenceStatusListener implements StatusListener {

	private TweetProcessor processor = new TweetProcessor();
	
	public void onStatus(Status status) {
		System.out.println("RECEIVE: " + status);
		this.processor.processTweet(status);
	}

	public void onDeletionNotice(StatusDeletionNotice sdn) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	public void onTrackLimitationNotice(int i) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	public void onScrubGeo(long l, long l1) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	public void onException(Exception excptn) {
		excptn.printStackTrace();
	}
	
}
