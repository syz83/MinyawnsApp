package com.minyawns.minyawnsapp;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ForgotPasswordActivity extends Activity {
	
	private EditText mEmailField;
	private Button mResetPassword;
	private String email;
	
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_signup);
        
        mEmailField.findViewById(R.id.forgot_password_page_email_field);
        mResetPassword.findViewById(R.id.reset_password_button);
        
        //Sets email field to user input
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
        
        //Send email to wordpress to send reset password email
        mResetPassword.setOnClickListener(new View.OnClickListener() {
        	
        	@Override
        	public void onClick(View v) {
        		
        		//Figure out how to talk to wordpress blog
        		
        	}
        	
        });
        
    }
	
	
	
}