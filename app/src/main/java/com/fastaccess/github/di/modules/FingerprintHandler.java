package com.fastaccess.github.di.modules;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.hardware.fingerprint.FingerprintManager;
import android.media.Image;
import android.os.Build;
import android.os.CancellationSignal;
import android.support.v4.content.ContextCompat;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * This class is that provides us with access to
 * the fingerprint hardware. Authentication Call back
 * helps us get all errors connected with the success of the tasks
 * @author  akshayejh
 * @author  IoannisVougias
 */

@TargetApi(Build.VERSION_CODES.M)
public class FingerprintHandler extends FingerprintManager.AuthenticationCallback {

    private Context context;

    /**
     * This method is an empty constructor
     * @param context Its the context that we are passing
     */
    public FingerprintHandler(Context context){

        this.context = context;

    }
    /**
     * This method autenticates the fingerprint that is used
     * in the figerprint manager
     * The use of a CryptoObject in a Fingerprint Authentication context is to know if
     * a new fingerprint was added since last time the user authenticated via fingerprint.
     * @param fingerprintManager A class that coordinates access to the fingerprint hardware.
     * @param cryptoObject A wrapper class for the crypto objects supported by FingerprintManager.
     */
    public void startAuth(FingerprintManager fingerprintManager, FingerprintManager.CryptoObject cryptoObject){

        CancellationSignal cancellationSignal = new CancellationSignal();
        fingerprintManager.authenticate(cryptoObject, cancellationSignal, 0, this, null);

    }
    /**
     * This method updates the UI when there was an error in the authentication
     * Called when an unrecoverable error has been encountered and the operation is complete.
     * @param errorCode An integer identifying the error message
     * @param errString A human-readable error string that is shown in UI
     */
    @Override
    public void onAuthenticationError(int errorCode, CharSequence errString) {

        this.update("There was an Auth Error. " + errString, false);

    }
    /**
     * This method updates the UI when the authentication failed
     * Called when a fingerprint is valid but not recognized.
     * e.g the fingerprint the user provides is wrong
     */
    @Override
    public void onAuthenticationFailed() {

        this.update("Auth Failed. ", false);

    }
    /**
     * This method updates the UI and provides error message
     * Called when a recoverable error has been encountered during authentication.
     * e.g if the user has not properly scanned his/her finger
     * @param helpCode  An integer identifying the error message
     * @param helpString  the help message
     */
    @Override
    public void onAuthenticationHelp(int helpCode, CharSequence helpString) {

        this.update("Error: " + helpString, false);

    }

    /**
     * This method updates the UI and provides message if
     * the authentication is successful
     * Called when a fingerprint is recognized.
     * @param result Container for callback data from
     *               FingerprintManager#authenticate(CryptoObject, CancellationSignal, int, AuthenticationCallback, Handler).
     */
    @Override
    public void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult result) {

        this.update("You can now access the app.", true);

    }
    /**
     * This method updates the UI
     * @param s the message that will be displayed
     * @param b boolean parameter than is false when there is an error
     *          and true when there is no error
     */
    private void update(String s, boolean b) {

        TextView paraLabel = (TextView) ((Activity)context).findViewById(R.id.paraLabel);
        ImageView imageView = (ImageView) ((Activity)context).findViewById(R.id.fingerprintImage);

        paraLabel.setText(s);

        if(!b){

            paraLabel.setTextColor(ContextCompat.getColor(context, R.color.colorAccent));

        } else {

            paraLabel.setTextColor(ContextCompat.getColor(context, R.color.colorPrimary));
            imageView.setImageResource(R.mipmap.action_done);

        }

    }
}