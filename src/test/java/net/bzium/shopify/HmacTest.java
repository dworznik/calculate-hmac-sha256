package net.bzium.shopify;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by dwuziu on 02/05/17.
 */
public class HmacTest {

    /**
     * Test Webhook HMAC based on Ruby implementation at https://help.shopify.com/api/getting-started/webhooks#verify-webhook
     * @throws Exception
     */
    @Test
    public void calculateHmac() throws Exception {
        assertEquals(Hmac.calculateHmac("my data", "my secret"), "BJ8yvk8zppggVSmBjBtnbUYMnLL2kBRX0BKmZGEnrjc=");
    }
}