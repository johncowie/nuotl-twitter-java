package org.nextupontheleft.twitter;

/**
 *
 * @author john
 */
public class TweetParsingException extends Exception {

    private TweetParsingErrorCode errorCode;
    
    public TweetParsingException(TweetParsingErrorCode errorCode) {
        this.errorCode = errorCode;
    }
    
    public TweetParsingErrorCode getErrorCode() {
        return this.errorCode;
    }
}
