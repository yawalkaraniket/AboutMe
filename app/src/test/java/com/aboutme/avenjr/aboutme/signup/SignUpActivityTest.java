package com.aboutme.avenjr.aboutme.signup;

import com.aboutme.avenjr.aboutme.activity.SignUpActivity;

import org.junit.Test;

import static junit.framework.Assert.assertTrue;

/**
 * Created by AvenjR on 15/5/18.
 */

public class SignUpActivityTest {
    SignUpActivity mSignUpActivity = new SignUpActivity();

    @Test
    public void itWillFailsIfConfirmPasswordNotMatchesWithOriginalPassword(){
        assertTrue("password field not matches the required matches",mSignUpActivity.verifyRenterPassword("SimpleTest","SimpleTest"));
    }
}
