package org.example.util;

import org.junit.Assert;
import org.junit.Test;

/**
 * Email Check Util
 */
public class EmailCheckUtilTest {

    @Test
    public void testIsValidEmail_Normal() {
        Assert.assertTrue(EmailCheckUtil.isValidEmail("user123@gmail.com"));
        Assert.assertTrue(EmailCheckUtil.isValidEmail("test@example.com"));
        Assert.assertTrue(EmailCheckUtil.isValidEmail("user@domain.com"));
        Assert.assertTrue(EmailCheckUtil.isValidEmail("user@domain.co.in"));
        Assert.assertTrue(EmailCheckUtil.isValidEmail("user.name@domain.com"));
        Assert.assertTrue(EmailCheckUtil.isValidEmail("username@yahoo.corporate.in"));
    }

    @Test
    public void testIsValidEmail_Invalid() {
        Assert.assertFalse(EmailCheckUtil.isValidEmail("abc"));
        Assert.assertFalse(EmailCheckUtil.isValidEmail("123"));
        Assert.assertFalse(EmailCheckUtil.isValidEmail("111@111"));
        Assert.assertFalse(EmailCheckUtil.isValidEmail("123.com"));
        Assert.assertFalse(EmailCheckUtil.isValidEmail("123@com"));
        Assert.assertFalse(EmailCheckUtil.isValidEmail("invalid.email@.com"));
        Assert.assertFalse(EmailCheckUtil.isValidEmail(".username@gmail.com"));
        Assert.assertFalse(EmailCheckUtil.isValidEmail("username@gmail.com."));
        Assert.assertFalse(EmailCheckUtil.isValidEmail("username@gmail..com"));
        Assert.assertFalse(EmailCheckUtil.isValidEmail("username@gmail.c"));
        Assert.assertFalse(EmailCheckUtil.isValidEmail("username@gmail.corporate"));
        Assert.assertFalse(EmailCheckUtil.isValidEmail("invalid.email@.com"));
    }
}

