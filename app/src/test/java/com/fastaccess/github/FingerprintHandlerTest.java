package com.fastaccess.github;

import android.app.Activity;
import android.content.Context;
import android.hardware.fingerprint.FingerprintManager;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


/**
 * Unit tests for FingerprintHandlerTest class
 */
public class FingerprintHandlerTest {
    private Context context;
    FingerprintHandler fingerprintHandler = new FingerprintHandler(context);


    /**
     *  Test normal behaviour of update method
     */
    @Test
    public void update() {
        Assert.assertEquals(true,fingerprintHandler.update("You can now access the repository",true));
    }
}
