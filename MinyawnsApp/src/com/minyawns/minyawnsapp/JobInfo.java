package com.minyawns.minyawnsapp;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.os.Parcel;
import android.os.Parcelable;

public class JobInfo implements Parcelable {
	
	public static final Parcelable.Creator<JobInfo> CREATOR = new Parcelable.Creator<JobInfo>() {
		@Override
        public JobInfo createFromParcel(Parcel in) {
            return new JobInfo(in);
        }

		@Override
        public JobInfo[] newArray(int size) {
            return new JobInfo[size];
        }
    };
	
	private Calendar mStartTime;	// dates in form [year, month (0 indexing), day, hour(24 hour), minute]
	private Calendar mEndTime;
	private Calendar mAppDueDate;
	private String mTitle;
	private String mLocationName;
	private double mWage;
	private String mJobDescription;
	private String mEmployerName;
	private String mEmployerAddress;
	private String[] mTags;
	
	public jobInfo() {
		
		/* Constructor 
		 * The idea is that the main activity will pass this one long string,
		 * or whatever it pulls from the minyawns wordpress and this constructor
		 * will sort the information into the various fields */
		
	}
	
	public JobInfo(Parcel in) {
		mStartTime = (Calendar) in.readSerializable();
		mEndTime = (Calendar) in.readSerializable();
		mAppDueDate = (Calendar) in.readSerializable();
		mTitle = in.readString();
		mLocationName = in.readString();
		mWage = in.readDouble();
		mJobDescription = in.readString();
		mEmployerName = in.readString();
		mEmployerAddress = in.readString();
		in.readStringArray(mTags);
	}

	
	// Returns a double of the total earnings rounded to the penny 
	public double getTotalEarnings() {
		double duration = (mEndTime.get(Calendar.MINUTE) - mStartTime.get(Calendar.MINUTE)) / 60.0;
		duration += mEndTime.get(Calendar.HOUR) - mStartTime.get(Calendar.HOUR);
		duration += (mEndTime.get(Calendar.DAY_OF_YEAR) - mStartTime.get(Calendar.DAY_OF_YEAR)) * 24.0;
		double result = duration * mWage;
		return roundToPenny(result);
	}
	
	// Returns a Calendar of the start time
	public Calendar getStartTime() {
		return mStartTime;
	}

	// Returns a Calendar of the end time
	public Calendar getEndTime() {
		return mEndTime;
	}
	
	// Returns a Calendar of the application due date
	public Calendar getAppDueDate() {
		return mAppDueDate;
	}

	// Returns a string of the title of the job
	public String getTitle() {
		return mTitle;
	}

	// Returns a string of the name of the job location
	public String getLocationName() {
		return mLocationName;
	}

	// Returns a double the wage rounded to pennys
	public double getWage() {
		return roundToPenny(mWage);
	}

	// Returns a string of the job description
	public String getJobDescription() {
		return mJobDescription;
	}

	// Returns a string of the employers name
	public String getEmployerName() {
		return mEmployerName;
	}

	// Returns a string of the Employers address
	public String getEmployerAddress() {
		return mEmployerAddress;
	}

	// Returns a string array of the tags
	public String[] getTags() {
		return mTags;
	}


	// Returns the start date and time in a string of the form Mon Aug 25 10:30 AM 2014
	public String getFullStringStartTime() {
		return getStringTime(mStartTime, "EEE MMM d, h:mm a yyyy");
	}
	
	// Returns the end date and time in a string of the form Mon Aug 25 10:30 AM 2014
	public String getFullStringEndTime() {
		return getStringTime(mEndTime, "EEE MMM d, h:mm a yyyy");	
	}
	
	// Returns the start time of the job in the form 11:10 AM
	public String getStringStartTimeOnly() {
		return getStringTime(mStartTime, "h:mm a");	
	}

	// Returns the end time of the job in the form 11:10 AM
	public String getStringEndTimeOnly() {
		return getStringTime(mEndTime, "h:mm a");	
	}
	
	// Returns the start time of the job in the form Mon Aug 25 11:10 AM
	public String getStringStartTime() {
		return getStringTime(mStartTime, "EEE MMM d, h:mm a");	
	}
	
	// Returns the end time of the job in the form Mon Aug 25 11:10 AM
	public String getStringEndTime() {
		return getStringTime(mEndTime, "EEE MMM d, h:mm a");	
	}
	
	// Returns the start month of the job in a 3-letter abbreviation
	public String getStartMonth() {
		return getStringTime(mStartTime, "MMM");
	}
	
	// Returns the end month of the job in a 3-letter abbreviation
	public String getEndMonth() {
		return getStringTime(mEndTime, "MMM");
	}
	
	public boolean isAppOpen() {
		Date currentTime = Calendar.getInstance().getTime();
		Date appDueDate = mAppDueDate.getTime();
		return appDueDate.before(currentTime);
	}
	
	// Takes a Calendar and a string of the desired date format as dictated by the SimpleDateFormat API
	// and returns a string of the date stored in the calendar in the format prescribed
	private String getStringTime(Calendar c, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(c.getTime());
	}

	// Returns the same double passed, just rounded to the 100's
	private double roundToPenny(double n) {
		return ((double) Math.round(n * 100)) / 100;
	}
	
	@Override
	public int describeContents() {
		return 0;
	}
	
	@Override
	public void writeToParcel(Parcel out, int flags) {
		out.writeSerializable(mStartTime);
		out.writeSerializable(mEndTime);
		out.writeSerializable(mAppDueDate);
		out.writeString(mTitle);
		out.writeString(mLocationName);
		out.writeDouble(mWage);
		out.writeString(mJobDescription);
		out.writeString(mEmployerName);
		out.writeString(mEmployerAddress);
		out.writeStringArray(mTags);
		
	}
	
	
	public String[] getJobDetails() {
		String[] result = new String[3];
		result[0] = this.getStringStartTime() + " to " + this.getStringEndTimeOnly();
		result[1] = mLocationName;
		result[2] = getWageDetailed();
		return result;
	}
	
	private String getWageDetailed() {
		String result = "Wage: ";
		result += addZeros(mWage) + " per hour, " + addZeros(this.getTotalEarnings()) + " total";
		return result;
	}
	
	private String addZeros(double x) {
		String result = "";
		int n = (int) Math.round(x * 100);
		if(n % 100 == 0) {
			result += n / 100 + ".00";
		} else if(n % 10 == 0) {
			result += n / 100.0 + "0";
		} else {
			result += n / 100.0 + "";
		}
		return result;
	}
}


















