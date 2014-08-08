package com.minyawns.minyawnsapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends Activity {
	
	private Button mLoginButton;
	private Button mFacebookLoginButton;
	private TextView mForgotPasswordTextView;
	private TextView mDontHaveAccountTextView;
	private EditText mEmailField;
	private EditText mPasswordField;
	private String email;
	private String password;

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_login);
        
        mLoginButton = (Button) findViewById(R.id.login_button);
        mFacebookLoginButton = (Button) findViewById(R.id.login_with_facebook_button);
        mForgotPasswordTextView = (TextView) findViewById(R.id.forgot_password_text_view);
        mDontHaveAccountTextView = (TextView) findViewById(R.id.dont_have_an_account_text_view);
        mEmailField = (EditText) findViewById(R.id.login_email_field);
        mPasswordField = (EditText) findViewById(R.id.login_password_field);
        email = "";
        password = "";
        
        mLoginButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				//figure out how to talk to minyawns wordpress and implement
				
			}
		});
        
        mFacebookLoginButton.setOnClickListener(new View.OnClickListener() {
        	
        	@Override
        	public void onClick(View v) {
        		
        		//Facebook Login stuff (need to research)
        		
        	}
        });
		
        //Opens forgot password page
		mForgotPasswordTextView.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Intent i = new Intent(LoginActivity.this, ForgotPasswordActivity.class);
				startActivity(i);
				
			}
		});
		
		// Opens Signup page
		mDontHaveAccountTextView.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				// Implement telling SignupActivity whatever has been typed into email of password fields here
				Intent i = new Intent(LoginActivity.this, SignupActivity.class);
				startActivity(i);
			}
		});
		
		// Sets email field to what the user inputs
		mEmailField.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence c, int start, int before, int count) {
				email = c.toString();
			}
			
			@Override
			public void beforeTextChanged (CharSequence c, int start, int before, int count) {
				
			}
			
			@Override
			public void afterTextChanged (Editable c) {
				
			}
		});
		
		// Sets password field to what the user inputs
		mPasswordField.addTextChangedListener(new TextWatcher() {
			public void onTextChanged(CharSequence c, int start, int before, int count) {
				password = c.toString();
			}
			
			public void beforeTextChanged (CharSequence c, int start, int before, int count) {
				
			}
			
			public void afterTextChanged (Editable c) {
				
			}
		});
    }
}
