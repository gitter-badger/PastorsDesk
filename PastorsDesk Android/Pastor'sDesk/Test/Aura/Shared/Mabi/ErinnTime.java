//
// Translated by CS2J (http://www.cs2j.com): 3/22/2015 3:22:43 AM
//

package Aura.Shared.Mabi;

import Aura.Shared.Mabi.ErinnTime;
import CS2JNet.System.DateTimeSupport;
import CS2JNet.System.IntegerSupport;
import java.util.Calendar;
import java.util.Date;

// Copyright (c) Aura development team - Licensed under GNU GPL
// For more information, see license file in the main folder
/**
* Wrapper around DateTime, to calculate the current time in Erinn.
*/
public class ErinnTime   
{
    /**
    * 1,500ms (1.5 seconds)
    */
    public static final long TicksPerMinute = 15000000;
    /**
    * 90,000ms (1.5 minutes)
    */
    public static final long TicksPerHour = TicksPerMinute * 60;
    /**
    * Erinn months in English, starting on Imbolic (Sunday).
    */
    protected static final String[] Months = new String[]{ "Imbolic", "Alban Eiler", "Baltane", "Alban Heruin", "Lughnasadh", "Alban Elved", "Samhain" };
    /**
    * Release of KR.
    */
    protected static final Date BeginOfTime = DateTimeSupport.parse("2004-06-22");
    /**
    * Erinn hour of this instance.
    */
    private int __Hour;
    public int getHour() {
        return __Hour;
    }

    public void setHour(int value) {
        __Hour = value;
    }

    /**
    * Erinn minute of this instance.
    */
    private int __Minute;
    public int getMinute() {
        return __Minute;
    }

    public void setMinute(int value) {
        __Minute = value;
    }

    /**
    * Erinn year of this instance.
    */
    private int __Year;
    public int getYear() {
        return __Year;
    }

    public void setYear(int value) {
        __Year = value;
    }

    /**
    * Erinn month of this instance.
    */
    private int __Month;
    public int getMonth() {
        return __Month;
    }

    public void setMonth(int value) {
        __Month = value;
    }

    /**
    * Erinn day of this instance.
    */
    private int __Day;
    public int getDay() {
        return __Day;
    }

    public void setDay(int value) {
        __Day = value;
    }

    /**
    * DateTime object used by this instance.
    */
    private Date __DateTime;
    public Date getDateTime() {
        return __DateTime;
    }

    public void setDateTime(Date value) {
        __DateTime = value;
    }

    /**
    * Time stamp for this Erinn date (Format: yyyymdd).
    */
    public int getDateTimeStamp() throws Exception {
        return (this.getYear() * 1000 + this.getMonth() * 100 + this.getDay());
    }

    /**
    * Returns a new MabiTime instance based on the current time.
    */
    public static ErinnTime getNow() throws Exception {
        return new ErinnTime();
    }

    /**
    * Returns true if the Erinn hour of this instance is between 6:00pm and 5:59am.
    */
    public boolean getIsNight() throws Exception {
        return (this.getHour() >= 18 || this.getHour() < 6);
    }

    /**
    * Returns true if it's not night, duh.
    */
    public boolean getIsDay() throws Exception {
        return !this.getIsNight();
            ;
    }

    /**
    * Returns true if time of this instance is 0:00am.
    */
    public boolean getIsMidnight() throws Exception {
        return (this.getHour() == 0 && this.getMinute() == 0);
    }

    /**
    * Returns true if time of this instance is 6:00am.
    */
    public boolean getIsDawn() throws Exception {
        return (this.getHour() == 6 && this.getMinute() == 0);
    }

    /**
    * Returns true if time of this instance is 6:00pm.
    */
    public boolean getIsDusk() throws Exception {
        return (this.getHour() == 18 && this.getMinute() == 0);
    }

    public ErinnTime() throws Exception {
        this(Calendar.getInstance().getTime());
    }

    public ErinnTime(Date dt) throws Exception {
        this.setDateTime(dt);
        this.setHour((int)((this.getDateTime().Ticks / TicksPerHour) % 24));
        this.setMinute((int)((this.getDateTime().Ticks / TicksPerMinute) % 60));
        // Based on the theory that 1 year (1 week realtime) consists of
        // 7 months (7 days) with 40 days (1440 / 36 min) each.
        this.setYear((int)Math.Floor((this.getDateTime().Ticks - BeginOfTime.Ticks) / TicksPerMinute / 60 / 24 / 280f));
        this.setMonth((int)this.getDateTime().DayOfWeek + 1);
        this.setDay((int)Math.Floor((this.getDateTime().Hour * 60 + this.getDateTime().Minute) / 36f));
    }

    /**
    * Returns the DateTime for last Saturday at 12:00.
    * 
    *  @return
    */
    public Date getLastSaturday() throws Exception {
        /* [UNSUPPORTED] 'var' as type is unsupported "var" */ lastSaturday = getDateTime().MinValue;
        if (this.getDateTime().DayOfWeek == DayOfWeek.Saturday)
            lastSaturday = (this.getDateTime().Hour < 12) ? DateTimeSupport.add(this.getDateTime(),Calendar.DAY_OF_YEAR,-7) : this.getDateTime();
        else
            lastSaturday = DateTimeSupport.add(this.getDateTime(),Calendar.DAY_OF_YEAR,-((int)this.getDateTime().DayOfWeek) - 1); 
        lastSaturday = lastSaturday.Date.AddHours(12);
        return lastSaturday;
    }

    /**
    * Returns a string with the Erinn time of this instance in AM/PM.
    * 
    *  @return
    */
    public String toString() {
        try
        {
            return this.toString("y-M-dd HH:mm");
        }
        catch (RuntimeException __dummyCatchVar0)
        {
            throw __dummyCatchVar0;
        }
        catch (Exception __dummyCatchVar0)
        {
            throw new RuntimeException(__dummyCatchVar0);
        }
    
    }

    /**
    * Returns a string with the Erinn time of this instance.
    * 
    *  @param format 
    *  @return
    */
    public String toString(String format) throws Exception {
        Int32 h12 = this.getHour() % 12;
        if (this.getHour() == 12)
            h12 = 12;
         
        format = format.replace("ampm", (IntegerSupport.mkString(h12, "00") + ":" + IntegerSupport.mkString(this.getMinute(), "00") + (this.getHour() < 12 ? " A.M." : " P.M.")));
        format = format.replace("hh", IntegerSupport.mkString(h12, "00"));
        format = format.replace("h", String.valueOf(h12));
        format = format.replace("HH", IntegerSupport.mkString(this.getHour(), "00"));
        format = format.replace("H", String.valueOf(this.getHour()));
        format = format.replace("mm", IntegerSupport.mkString(this.getMinute(), "00"));
        format = format.replace("m", String.valueOf(this.getMinute()));
        format = format.replace("yyyy", IntegerSupport.mkString(this.getYear(), "0000"));
        format = format.replace("yyy", IntegerSupport.mkString(this.getYear(), "000"));
        format = format.replace("yy", IntegerSupport.mkString(this.getYear(), "00"));
        format = format.replace("y", IntegerSupport.mkString(this.getYear(), "0"));
        format = format.replace("MMMM", Months[this.getMonth() - 1]);
        format = format.replace("MM", IntegerSupport.mkString(this.getMonth(), "00"));
        format = format.replace("M", String.valueOf(this.getMonth()));
        format = format.replace("dd", IntegerSupport.mkString(this.getDay(), "00"));
        format = format.replace("d", String.valueOf(this.getDay()));
        format = format.replace("tt", (this.getHour() < 12 ? "AM" : "PM"));
        format = format.replace("t", (this.getHour() < 12 ? "A" : "P"));
        return format;
    }

}


