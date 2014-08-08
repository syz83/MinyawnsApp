package com.minyawns.minyawnsapp;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SignupActivity extends Activity {

	private Button mSignupButton;
	private Button mSignupWithFacebookButton;
	private TextView mAlreadyHaveAccountTextView;
	private EditText mEmailField;
	private EditText mPasswordField;
	private EditText mFirstNameField;
	private EditText mLastNameField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_signup);
        
        mSignupButton.findViewById(R.id.signup_button);
        mSignupWithFacebookButton.findViewById(R.id.signup_with_facebook_button);
        mAlreadyHaveAccountTextView.findViewById(R.id.already_have_account_text_view);
        mEmailField.findViewById(R.id.signup_email_field);
        mPasswordField.findViewById(R.id.signup_password_field);
        mFirstNameField.findViewById(R.id.signup_first_name_field);
        mLastNameField.findViewById(R.id.signup_last_name_field);
    }
    
}
