package com.sullivankw.blackjackhelper;

import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ConversionUnitTest {

    @Test
    public void fromEnumTest() {

        String advice = HandAdvice.fromEnum("SPLIT_IF_DOUBLE_ALLOWED_OR_HIT");
        Assert.assertEquals("SPLIT IF DOUBLE ALLOWED OR HIT", advice);

        advice = HandAdvice.fromEnum("SURRENDER_IF_ALLOWED_OR_STAND");
        Assert.assertEquals("SURRENDER IF ALLOWED OR STAND", advice);

        advice = HandAdvice.fromEnum("SURRENDER_IF_ALLOWED_OR_STAND");
        Assert.assertNotEquals("SURRENDER_IF_ALLOWED_OR_STAND", advice);

    }
}