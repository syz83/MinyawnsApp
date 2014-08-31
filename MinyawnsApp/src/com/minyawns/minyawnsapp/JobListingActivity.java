package com.minyawns.minyawnsapp;

import java.util.Calendar;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class JobListingActivity extends Activity {
	
	private JobInfo mJob;
	private TextView mDayOfMonth;
	private TextView mMonthOfYear;
	private TextView mJobTitle;
	private TextView mJobTime;
	private TextView mJobLocation;
	private TextView mJobWage;
	private TextView mJobDescription;
	private TextView mEmployerName;
	private TextView mEmployerAddress;
	private TextView mTags;
	private TextView mApplicationOpenOrClosed;
	private TextView mDaysLeftToApply;
	private Button mApplyButton;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_job_listing);
		Intent i = getIntent();
		mJob = (JobInfo) i.getParcelableExtra("JobInfo");
		mDayOfMonth.findViewById(R.id.day_of_month_textview);
		mMonthOfYear.findViewById(R.id.month_of_year_textview);
		mJobTitle.findViewById(R.id.job_title_textview);
		mJobTime.findViewById(R.id.job_time_textview);
		mJobLocation.findViewById(R.id.job_location_textview);
		mJobWage.findViewById(R.id.job_wage_textview);
		mJobDescription.findViewById(R.id.job_description_textview);
		mEmployerName.findViewById(R.id.employer_name_textview);
		mEmployerAddress.findViewById(R.id.employer_address_textview);
		mTags.findViewById(R.id.job_tags_textview);
		mApplicationOpenOrClosed.findViewById(R.id.application_open_or_closed_textview);
		mDaysLeftToApply.findViewById(R.id.days_left_to_apply_textview);
		mApplyButton.findViewById(R.id.apply_button);
		mDayOfMonth.setText("" + mJob.getStartTime().get(Calendar.DAY_OF_MONTH));
		mMonthOfYear.setText(capitalize(mJob.getStartMonth()));
		mJobTitle.setText(mJob.getTitle());
		String[] details = mJob.getJobDetails();
		mJobTime.setText(details[0]);
		mJobLocation.setText(details[1]);
		mJobWage.setText(details[2]);
		mJobDescription.setText(mJob.getJobDescription());
		mEmployerName.setText(mJob.getEmployerName());
		mEmployerAddress.setText(mJob.getEmployerAddress());
		String tags = "";
		for(String s: mJob.getTags()) {
			tags += s + ", ";
		}
		mTags.setText(tags);
		if (mJob.isAppOpen()) {
			mApplicationOpenOrClosed.setTextColor(Color.parseColor("#88D816"));
			mApplicationOpenOrClosed.setText("Application Open");
			int days;
			Calendar now = Calendar.getInstance();
			Calendar due = mJob.getAppDueDate();
			if (now.get(Calendar.YEAR) == due.get(Calendar.YEAR)) {
				days = due.get(Calendar.DAY_OF_YEAR) - now.get(Calendar.DAY_OF_YEAR);
			} else {
				days = (365 - due.get(Calendar.DAY_OF_YEAR)) + now.get(Calendar.DAY_OF_YEAR);
			}
			mDaysLeftToApply.setText(days + " days left to apply");
			mApplyButton.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					
					// Write something to turn in the users application for the Job 
					
				}
			});
		} else {
			mApplicationOpenOrClosed.setTextColor(Color.parseColor("#FC252C"));
			mApplicationOpenOrClosed.setText("Application Closed");
			mDaysLeftToApply.setText(null);
			// Figure out way to grey out button
		}
	}
	
	private String capitalize(String s) {
		int i = 0;
		String result = "";
		while (i < s.length()) {
			int a = (int) s.charAt(i);
			if (a >= 97 && a < 122) {
				result += (char) (s.charAt(i) - 32);
			} else {
				result += "" + s.charAt(i);
			}
			i++;
		}
		return result;
	}
}












