package com.sullivankw.blackjackhelper;

import com.sullivankw.blackjackhelper.jar.BlackjackHelperServiceException;
import com.sullivankw.blackjackhelper.jar.HandHelperService;

import org.junit.Assert;
import org.junit.Test;

public class HandHelperServiceTest {

    /**
     * Testing HandHelperServiceTest
     * **/

    @Test
    public void handHelperServiceReturnBlackjackTest() throws BlackjackHelperServiceException {

       String advice =  HandHelperService.getHandHelperService().
               getHandHelp(true, "eight","ace", "king");

        Assert.assertEquals("BLACKJACK", advice);

        String advice2 =  HandHelperService.getHandHelperService().
                getHandHelp(false, "eight","ace", "king");

        Assert.assertEquals("BLACKJACK", advice2);

    }

    @Test
    public void otherHandHelperServiceTest() throws BlackjackHelperServiceException {

        String advice =  HandHelperService.getHandHelperService().
                getHandHelp(true, "five","four", "king");

        Assert.assertEquals("STAY", advice);

        advice =  HandHelperService.getHandHelperService().
                getHandHelp(true, "five","eight", "eight");

        Assert.assertEquals("SPLIT", advice);
    }
}
