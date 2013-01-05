package org.nextupontheleft.twitter;

/**
 *
 * @author john
 */
public class DurationParser {
    
    private enum Duration {
        D(1440),
        H(60),
        M(1);
        
        private int multiplier;
        
        private Duration(int multiplier) {
            this.multiplier = multiplier;
        }
        
        public int inMinutes(double quantity) {
            return (int)Math.ceil(quantity * multiplier);
        }
    }
    
    public int getDuration(String string) throws TweetParsingException {
        int l = string.length();        
        String period = string.substring(l-1, l);
        String quantity = string.substring(0, l-1);
        try {
            Duration dur = Duration.valueOf(period.toUpperCase());
            double q = Double.parseDouble(quantity);
            return dur.inMinutes(q);
        } catch(IllegalArgumentException e) {
            throw new TweetParsingException(TweetParsingErrorCode.DURATION_ERROR);
        }
    }
    
}
