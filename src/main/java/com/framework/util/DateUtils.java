package com.framework.util;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import org.apache.commons.beanutils.BasicDynaBean;
import org.apache.commons.beanutils.BasicDynaClass;
import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.beanutils.DynaClass;
import org.apache.commons.beanutils.DynaProperty;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Minutes;

public class DateUtils {
	public static final DateUtils instance = new DateUtils();

	private DateUtils() {
	}

	public static final String[] MONTHS_LONG_EN;
	public static final String[] MONTHS_LONG_ID;
	public static final String[] MONTHS_SHORT_EN;
	public static final String[] MONTHS_SHORT_ID;
	public static final String[] DAYS_LONG_EN;
	public static final String[] DAYS_SHORT_EN;
	public static final String[] DAYS_LONG_ID;
	public static final String[] DAYS_SHORT_ID;
	public static final String DATE_PROP = "date";
	public static final String MONTH_PROP = "month";
	public static final String DAY_PROP = "day";
	public static final String VALUE_PROP = "value";
	public static final String EXT_FORMAT = "yyyy-MM-dd";
	public static final String EXT_HHMM_FORMAT = "yyyy-MM-dd hh:mm";
	public static final String DEFAULT_FORMAT = "dd-MMM-yyyy";
	public static final String LONG_FORMAT = "dd MMMM yyyy";
	public static final String DEFAULT_TIME_FORMAT = "HH:mm:ss";
	public static final String DEFAULT_12HOUR_TIME_FORMAT = "hh:mm:ss a";
	public static final String DEFAULT_TIMESTAMP_FORMAT = "dd-MMM-yyyy HH:mm:ss";
	public static final String NOSECOND_TIMESTAMP_FORMAT = "dd-MMM-yyyy HH:mm";
	public static final String SHIPMONTH_FORMAT = "yyyyMM";
	public static final String EARLIEST = " 00:00";
	public static final String LATEST = " 23:59";
	public static final String EXCEL_DATETIME_FORMAT = "dd/MM/yyyy HH:mm:ss";
	public static final String NUMBER_DATE_FORMAT = "yyyyMMdd";
	public static final String NOTIME_NUMBER_DATE_FORMAT = "yyyyMMdd HH:mm";
	public static final String TIMEZONE_UTC = "UTC";

	/**
	 *   给一个日期加上给定的天数、月数、年数，根据加上后日期结果返回不同格式的日期类型。
	 *   如：秒为0时，返回"yyyy-MM-dd HH:mm"、时分秒都为0时，返回"yyyy-MM-dd"、否则返回"yyyy-MM-dd HH:mm:ss"格式的日期。
	 * @param date
	 * @param days
	 * @param months
	 * @param years
	 * @return
	 */
	public static java.util.Date add(java.util.Date date, int days, int months,
			int years) {
		if (date == null) {
			return null;
		}
		GregorianCalendar greg = getGregorianCalendar(date);

		greg.add(1, years);
		greg.add(2, months);
		greg.add(5, days);
		return gregCalToDate(greg);
	}
    
   /**
   * 给一个日期加上给定的天数，根据加上后的结果返回不同格式的日期类型
   * @param date
   * @param days
   * @return
   */
	public static java.util.Date addDay(java.util.Date date, int days) {
		if (date == null) {
			return null;
		}
		GregorianCalendar greg = getGregorianCalendar(date);

		greg.add(5, days);
		return gregCalToDate(greg);
	}
    /**
     * 给一个日期加上给定的小时数，根据加上后的结果返回不同格式的日期类型。
     * @param date
     * @param hours
     * @return
     */
	public static java.util.Date addHour(java.util.Date date, int hours) {
		if (date == null) {
			return null;
		}
		GregorianCalendar greg = getGregorianCalendar(date);

		greg.add(11, hours);
		return gregCalToDate(greg);
	}
    /**
     * 给一个日期加上给定的分钟数，根据加上后的结果返回不同格式的日期类型
     * @param date
     * @param minutes
     * @return
     */
	public static java.util.Date addMinute(java.util.Date date, int minutes) {
		if (date == null) {
			return null;
		}
		GregorianCalendar greg = getGregorianCalendar(date);

		greg.add(12, minutes);
		return gregCalToDate(greg);
	}
    
	/**
	 *  给一个日期加上给定的月数，根据加上后的结果返回不同格式的日期类型
	 * @param date
	 * @param months
	 * @return
	 */
	public static java.util.Date addMonth(java.util.Date date, int months) {
		if (date == null) {
			return null;
		}
		GregorianCalendar greg = getGregorianCalendar(date);

		greg.add(2, months);
		return gregCalToDate(greg);
	}
   
	/**
	 *  给一个日期加上给定的秒数，根据加上后的结果返回不同格式的日期类型
	 * @param date
	 * @param seconds
	 * @return
	 */
	public static java.util.Date addSecond(java.util.Date date, int seconds) {
		if (date == null) {
			return null;
		}
		GregorianCalendar greg = getGregorianCalendar(date);

		greg.add(13, seconds);
		return gregCalToDate(greg);
	}

	/**
	 * 给一个日期加上给定的年数，根据加上后的结果返回不同格式的日期类型。
	 * @param date
	 * @param years
	 * @return
	 */
	public static java.util.Date addYear(java.util.Date date, int years) {
		if (date == null) {
			return null;
		}
		GregorianCalendar greg = getGregorianCalendar(date);
		greg.add(1, years);
		return gregCalToDate(greg);
	}
    
	
	protected static Time assembleTime(Calendar calendar) {
		return new Time(calendar.getTimeInMillis());
	}
    /**
     * 按照提供的时、分、秒、毫秒值替换给出日期的时、分、秒、毫秒值。
     * @param hour
     * @param minute
     * @param second
     * @param miliSecond
     * @return
     */
	public static Time assembleTime(int hour, int minute, int second,
			int miliSecond) {
		Calendar timeCalendar = Calendar.getInstance();
		timeCalendar.setTimeInMillis(0L);
		timeCalendar.set(11, hour);
		timeCalendar.set(12, minute);
		timeCalendar.set(13, second);
		timeCalendar.set(14, miliSecond);
		return assembleTime(timeCalendar);
	}

	protected static Timestamp assembleTimestamp(Calendar calendar) {
		return new Timestamp(calendar.getTimeInMillis());
	}

	public static java.util.Date assembleUtilDate(java.sql.Date date, int hour,
			int minute, int second, int miliSecond) {
		return assembleUtilDate(date,
				assembleTime(hour, minute, second, miliSecond));
	}

	/**
	 *   把Time类型的时、分、秒、毫秒值替换掉日期类型的时、分、秒、毫秒值。
	 * @param date
	 * @param time
	 * @return
	 */
	public static java.util.Date assembleUtilDate(java.sql.Date date, Time time) {
		if (date == null) {
			return null;
		}
		Calendar dateCalendar = Calendar.getInstance();
		dateCalendar.setTime(date);
		if (time != null) {
			Calendar timeCalendar = Calendar.getInstance();
			timeCalendar.setTime(time);
			dateCalendar.set(11, timeCalendar.get(11));
			dateCalendar.set(12, timeCalendar.get(12));
			dateCalendar.set(13, timeCalendar.get(13));
			dateCalendar.set(14, timeCalendar.get(14));
		}
		return dateCalendar.getTime();
	}
   
	/**
	 * 转换java.sql.Date为java.util.Date
	 * @param inDate
	 * @return
	 */
	public static java.util.Date convertSqltoUtilDate(java.sql.Date inDate) {
		return new java.util.Date(inDate.getTime());
	}
    
	/**
	 *  转换时间戳为java.sql.Date。
	 * @param dateTime
	 * @return
	 */
	public static java.sql.Date convertTimestamptoSqlDate(Timestamp dateTime) {
		if (dateTime == null) {
			return null;
		}
		return new java.sql.Date(dateTime.getTime());
	}
    /**
     *  转换java.util.Date为java.sql.Date。
     * @param inDate
     * @return
     */
	public static java.sql.Date convertUtiltoSqlDate(java.util.Date inDate) {
		if (inDate == null) {
			return null;
		}
		if ((inDate instanceof java.sql.Date)) {
			return (java.sql.Date) inDate;
		}
		return new java.sql.Date(inDate.getTime());
	}
    /**
     *  转换java.util.Date为时间戳。
     * @param inDate
     * @return
     */
	public static Timestamp convertUtiltoTimestamp(java.util.Date inDate) {
		if (inDate == null) {
			return null;
		}
		if ((inDate instanceof Timestamp)) {
			return (Timestamp) inDate;
		}
		return new Timestamp(inDate.getTime());
	}
     
	/**
	 *  转换日期类型为String。按照默认格式"yyyy-MM-dd"。
	 * @param date
	 * @return
	 */
	public static String format(java.util.Date date) {
		return format(date, false);
	}

	public static String format(java.util.Date date, boolean uppercase) {
		return format(date, getDefaultLocale(), DateUtils.EXT_FORMAT, uppercase);
	}

	public static String format(java.util.Date date, Locale locale) {
		return format(date, locale, DateUtils.EXT_FORMAT, false);
	}

	public static String format(java.util.Date date, Locale locale,
			boolean uppercase) {
		return format(date, locale, DateUtils.EXT_FORMAT, uppercase);
	}

	public static String format(java.util.Date date, Locale locale,
			String pattern, boolean uppercase) {
		return format(date, locale, pattern, uppercase, false);
	}

	public static String format(java.util.Date date, Locale locale,
			String pattern, boolean uppercase, boolean timezone) {
		if ((date == null) || (locale == null) || (pattern == null)) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(pattern, locale);
		if (timezone) {
			// /sdf.setTimeZone(CxcUserUtils.getLoginUserTimeZone());
		}
		if (uppercase) {
			return sdf.format(date).toUpperCase();
		}
		return sdf.format(date);
	}

	public static String format(java.util.Date date, String pattern) {
		return format(date, pattern, false);
	}

	public static String format(java.util.Date date, String pattern,
			boolean uppercase) {
		return format(date, getDefaultLocale(), pattern, uppercase);
	}

	public static String format(Long date) {
		return format(date, false);
	}

	public static String format(Long date, boolean uppercase) {
		return format(date == null ? null
				: new java.util.Date(date.longValue()), getDefaultLocale(),
				DateUtils.EXT_FORMAT, uppercase);
	}

	public static String format(Long date, Locale locale) {
		return format(date == null ? null
				: new java.util.Date(date.longValue()), locale,
				DateUtils.EXT_FORMAT, false);
	}

	public static String format(Long date, Locale locale, String pattern) {
		return format(date, locale, pattern, false);
	}

	public static String format(Long date, Locale locale, String pattern,
			boolean uppercase) {
		return format(date == null ? null
				: new java.util.Date(date.longValue()), locale, pattern,
				uppercase);
	}

	public static String format(Long date, String pattern) {
		return format(date, pattern, false);
	}

	public static String format(Long date, String pattern, boolean uppercase) {
		return format(date == null ? null
				: new java.util.Date(date.longValue()), getDefaultLocale(),
				pattern, uppercase);
	}

	public static String format(Object date, Locale locale,
			String initialFormat, String expectedFormat) {
		if ((date == null) || (locale == null) || (initialFormat == null)
				|| (expectedFormat == null)) {
			return null;
		}
		String string = null;
		SimpleDateFormat fAwal = new SimpleDateFormat(initialFormat, locale);
		SimpleDateFormat fHasil = new SimpleDateFormat(expectedFormat, locale);
		fAwal.setLenient(false);
		try {
			string = fHasil.format(fAwal.parse(date.toString()));
		} catch (Exception e) {
			string = null;
		}
		return string;
	}

	public static String format(Object date, String initialFormat,
			String expectedFormat) {
		return format(date, getDefaultLocale(), initialFormat, expectedFormat);
	}

	public static Timestamp parseStringToTimestamp(String date, String format)
			throws ParseException {
		if (date == null || "".equals(date))
			return null;
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date da = sdf.parse(date);
		date = df1.format(da);
		return Timestamp.valueOf(date);
	}

	public static String formatDate(boolean isLongString, java.util.Date date) {
		Calendar cal = GregorianCalendar.getInstance();
		cal.setTime(date);
		int year = cal.get(1);
		int month = cal.get(2);
		int day = cal.get(5);
		return new StringBuilder()
				.append(org.apache.commons.lang3.StringUtils.leftPad(
						String.valueOf(day), 2, '0'))
				.append(" ")
				.append(isLongString ? MONTHS_LONG_EN[month]
						: MONTHS_SHORT_EN[month]).append(" ").append(year)
				.toString();
	}

	public static String formatDate(boolean isLongMonth,
			java.util.Date startDate, java.util.Date endDate) {
		String result = "";
		Calendar startCalendar = GregorianCalendar.getInstance();
		Calendar endCalendar = GregorianCalendar.getInstance();
		startCalendar.setTime(startDate);
		endCalendar.setTime(endDate);
		int startMonth = startCalendar.get(2);
		int startDay = startCalendar.get(5);
		int shortYear = startCalendar.get(1);
		int endMonth = endCalendar.get(2);
		int endDay = endCalendar.get(5);
		int endYear = endCalendar.get(1);
		int maximumDayOfMonth = endCalendar.getActualMaximum(5);
		String startMonthName = isLongMonth ? MONTHS_LONG_EN[startMonth]
				: MONTHS_SHORT_EN[startMonth];
		String endMonthName = isLongMonth ? MONTHS_LONG_EN[endMonth]
				: MONTHS_SHORT_EN[endMonth];

		if (shortYear == endYear) {
			if (startMonth == endMonth) {
				if ((startDay == 1) && (endDay == maximumDayOfMonth))
					result = new StringBuilder().append(startMonthName)
							.append(" ").append(shortYear).toString();
				else if ((startDay != 1) || (endDay != maximumDayOfMonth)) {
					if (startDay == endDay)
						result = new StringBuilder().append(startDay)
								.append(" ").append(startMonthName).append(" ")
								.append(shortYear).toString();
					else {
						result = new StringBuilder().append(startDay)
								.append(" - ").append(endDay).append(" ")
								.append(startMonthName).append(" ")
								.append(shortYear).toString();
					}
				}
			} else if ((startDay == 1) && (endDay == maximumDayOfMonth))
				result = new StringBuilder().append(startMonthName)
						.append(" - ").append(endMonthName).append(" ")
						.append(shortYear).toString();
			else if ((startDay != 1) || (endDay != maximumDayOfMonth)) {
				result = new StringBuilder().append(startDay).append(" ")
						.append(startMonthName).append(" - ").append(endDay)
						.append(" ").append(endMonthName).append(" ")
						.append(shortYear).toString();
			}

		} else if (shortYear != endYear) {
			if (startMonth == endMonth) {
				if ((startDay == 1) && (endDay == maximumDayOfMonth))
					result = new StringBuilder().append(startMonthName)
							.append(" ").append(shortYear).append(" - ")
							.append(endMonthName).append(" ").append(endYear)
							.toString();
				else if ((startDay != 1) || (endDay != maximumDayOfMonth)) {
					result = new StringBuilder().append(startDay).append(" ")
							.append(startMonthName).append(" ")
							.append(shortYear).append(" - ").append(endDay)
							.append(" ").append(endMonthName).append(" ")
							.append(endYear).toString();
				}

			} else if ((startDay == 1) && (endDay == maximumDayOfMonth))
				result = new StringBuilder().append(startMonthName).append(" ")
						.append(shortYear).append(" - ").append(endMonthName)
						.append(" ").append(endYear).toString();
			else if ((startDay != 1) || (endDay != maximumDayOfMonth)) {
				result = new StringBuilder().append(startDay).append(" ")
						.append(startMonthName).append(" ").append(shortYear)
						.append(" - ").append(endDay).append(" ")
						.append(endMonthName).append(" ").append(endYear)
						.toString();
			}

		}

		return result;
	}

	public static String formatDate(java.util.Date date) throws ParseException {
		return formatDate(date, DateUtils.EXT_FORMAT);
	}

	public static String formatDate(java.util.Date date, String dateFormat)
			throws ParseException {
		return formatDate(date, dateFormat, false);
	}

	public static String formatDate(java.util.Date date, String dateFormat,
			boolean uppercase) throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat(dateFormat);
		String formattedDate = df.format(date);
		if (uppercase) {
			formattedDate = formattedDate.toUpperCase();
		}
		return formattedDate;
	}

	public static java.util.Date formatWithEarliest(java.util.Date inDate) {
		java.util.Date retVal = valueOf(
				new StringBuilder()
						.append(format(inDate, DateUtils.EXT_FORMAT))
						.append(" 00:00").toString(), "dd-MMM-yyyy HH:mm");
		return retVal;
	}

	public static java.util.Date formatWithLatest(java.util.Date inDate) {
		java.util.Date retVal = valueOf(
				new StringBuilder()
						.append(format(inDate, DateUtils.EXT_FORMAT))
						.append(" 23:59").toString(), "dd-MMM-yyyy HH:mm");
		return retVal;
	}

	public static int[] getAll(java.util.Date date) {
		if (date == null) {
			return null;
		}
		int[] retVal = { getYear(date), getMonth(date), getDate(date),
				getHour(date), getMinute(date), getSecond(date) };

		return retVal;
	}

	public static int[] getAll(Long date) {
		return getAll(date == null ? null
				: new java.util.Date(date.longValue()));
	}

	public static java.util.Date getCurrentDate() {
		return new java.util.Date(System.currentTimeMillis());
	}

	public static List<DynaBean> getCurrentMonthDates() {
		return getDates(DateFormat.CURRENCT_MONTH_DATE);
	}

	public static java.sql.Date getCurrentSqlDate() {
		return new java.sql.Date(System.currentTimeMillis());
	}

	public static Timestamp getCurrentTimeStamp() {
		return new Timestamp(System.currentTimeMillis());
	}

	public static int getDate(java.util.Date inDate) {
		Calendar aCal = GregorianCalendar.getInstance();
		aCal.setTime(inDate);
		return aCal.get(5);
	}

	public static int getDate(Long date) {
		return getDate(date == null ? null : new java.util.Date(
				date.longValue()));
	}

	public static Timestamp getDateAfter(java.util.Date dt, int number,
			String dateOrMonth, boolean ignoreHoliday) {
		Calendar cal = GregorianCalendar.getInstance();
		cal.setTime(dt);
		if (dateOrMonth.equalsIgnoreCase("M"))
			cal.add(2, number);
		else {
			cal.add(5, number);
		}
		if (ignoreHoliday) {
			return convertUtiltoTimestamp(cal.getTime());
		}
		return getNextWorkingday(new Timestamp(cal.getTime().getTime()),
				dateOrMonth);
	}

	public static Timestamp getDateBefore(java.util.Date dt, int number,
			String dateOrMonth, boolean ignoreHoliday) {
		Calendar cal = GregorianCalendar.getInstance();
		cal.setTime(dt);
		if (dateOrMonth.equalsIgnoreCase("M"))
			cal.add(2, -number);
		else {
			cal.add(5, -number);
		}
		if (ignoreHoliday) {
			return convertUtiltoTimestamp(cal.getTime());
		}

		return getPreWorkingday(new Timestamp(cal.getTime().getTime()),
				dateOrMonth);
	}

	public static List<DynaBean> getDates() {
		return getDates(DateFormat.DATE);
	}

	private static List<DynaBean> getDates(DateFormat mode) {
		List list = null;
		DynaProperty[] properties = null;
		DynaClass dynaClass = null;
		DynaBean bean = null;
		try {
			properties = new DynaProperty[] {
					new DynaProperty(mode.getLabel()),
					new DynaProperty("value") };

			dynaClass = new BasicDynaClass(mode.name(), BasicDynaBean.class,
					properties);
			list = new ArrayList(mode.getSize());
			int length = mode.getSize();
			String[] labels = mode.getLabels();
			String[] values = mode.getValues();

			for (int i = 0; i < length; i++) {
				bean = dynaClass.newInstance();
				bean.set("value", values[i]);
				bean.set(mode.getLabel(), labels[i]);
				list.add(bean);
			}
		} catch (Exception ex) {

		}
		return list;
	}

	private static int getDayInt(String dayin) {
		int dayInt = 0;
		if (dayin.equalsIgnoreCase(DAYS_LONG_EN[1])) {
			dayInt = 2;
		}
		if (dayin.equalsIgnoreCase(DAYS_LONG_EN[2])) {
			dayInt = 3;
		}
		if (dayin.equalsIgnoreCase(DAYS_LONG_EN[3])) {
			dayInt = 4;
		}
		if (dayin.equalsIgnoreCase(DAYS_LONG_EN[4])) {
			dayInt = 5;
		}
		if (dayin.equalsIgnoreCase(DAYS_LONG_EN[5])) {
			dayInt = 6;
		}
		if (dayin.equalsIgnoreCase(DAYS_LONG_EN[6])) {
			dayInt = 7;
		}
		if (dayin.equalsIgnoreCase(DAYS_LONG_EN[0])) {
			dayInt = 1;
		}
		return dayInt;
	}

	public static Integer getDayinYear() {
		if (isLeap()) {
			return Integer.valueOf(366);
		}
		return Integer.valueOf(365);
	}

	public static List<DynaBean> getDays() {
		return getDates(DateFormat.DAY_EN);
	}

	private static Locale getDefaultLocale() {
		Locale locale = null;
		try {
			locale = Locale.CHINA;
		} catch (IllegalStateException ex) {
			locale = Locale.CHINA;
		}
		return locale;
	}

	public static int getDifferenceDays(java.util.Date startDate,
			java.util.Date endDate) {
		return getDifferenceDays(startDate, endDate, true);
	}

	public static int getDifferenceDays(java.util.Date startDate,
			java.util.Date endDate, boolean truncateTime) {
		if (truncateTime) {
			Calendar cal = Calendar.getInstance();

			cal.setTime(startDate);
			cal.set(9, 0);
			cal.set(10, 0);
			cal.set(12, 0);
			cal.set(13, 0);
			cal.set(14, 0);
			startDate = new Timestamp(cal.getTimeInMillis());

			cal.setTime(endDate);
			cal.set(9, 0);
			cal.set(10, 0);
			cal.set(12, 0);
			cal.set(13, 0);
			cal.set(14, 0);
			endDate = new Timestamp(cal.getTimeInMillis());
		}
		DateTime d1 = new DateTime(startDate);
		DateTime d2 = new DateTime(endDate);
		Days days = Days.daysBetween(d1, d2);
		int noOfDays = days.getDays();
		return noOfDays;
	}

	public static int getDifferenceMinutes(java.util.Date startDate,
			java.util.Date endDate) {
		DateTime d1 = new DateTime(startDate);
		DateTime d2 = new DateTime(endDate);
		Minutes minutes = Minutes.minutesBetween(d1, d2);
		int noOfMinutes = minutes.getMinutes();

		return noOfMinutes;
	}

	public static java.util.Date getEndDate(int shipmonthnumber) {
		int year = shipmonthnumber / 100;
		int month = shipmonthnumber % 100 - 1;
		Calendar now = GregorianCalendar.getInstance();

		now.set(1, year);
		now.set(2, month);
		now.set(5, 1);
		now.add(2, 1);
		now.add(5, -1);
		return now.getTime();
	}

	public static String getFormattedCurrentDate() throws ParseException {
		return getFormattedCurrentDate(DateUtils.EXT_FORMAT);
	}

	public static String getFormattedCurrentDate(String dateFormat)
			throws ParseException {
		return format(getCurrentTimeStamp(), dateFormat);
	}

	private static GregorianCalendar getGregorianCalendar(java.util.Date date) {
		return getGregorianCalendar(getYear(date), getMonth(date),
				getDate(date), getHour(date), getMinute(date), getSecond(date));
	}

	private static GregorianCalendar getGregorianCalendar(int year, int month,
			int day, int hour, int min, int sec) {
		return new GregorianCalendar(year, month - 1, day, hour, min, sec);
	}

	public static int getHour(java.util.Date date) {
		if (date == null) {
			return 0;
		}
		return Integer.valueOf(format(date, "HH")).intValue();
	}

	public static int getHour(Long date) {
		return getHour(date == null ? null : new java.util.Date(
				date.longValue()));
	}

	public static DateUtils getInstance() {
		return instance;
	}

	public static Timestamp getLastday(java.util.Date adate, String dayin) {
		Calendar cal = Calendar.getInstance();

		cal.setTime(adate);
		while (cal.get(7) != getDayInt(dayin)) {
			cal.add(5, -1);
		}
		return convertUtiltoTimestamp(cal.getTime());
	}

	public static int getMinute(java.util.Date date) {
		if (date == null) {
			return 0;
		}
		return Integer.valueOf(format(date, "mm")).intValue();
	}

	public static int getMinute(Long date) {
		return getMinute(date == null ? null : new java.util.Date(
				date.longValue()));
	}

	public static int getMonth(java.util.Date inDate) {
		Calendar aCal = GregorianCalendar.getInstance();

		aCal.setTime(inDate);
		return aCal.get(2) + 1;
	}

	public static int getMonth(Long date) {
		return getMonth(date == null ? null : new java.util.Date(
				date.longValue()));
	}

	public static int getMonthFromLongName(String longName) {
		for (int i = 0; i < MONTHS_LONG_EN.length; i++) {
			String mName = MONTHS_LONG_EN[i];
			if (mName.equalsIgnoreCase(longName)) {
				return i + 1;
			}
		}
		return 0;
	}

	public static int getMonthFromShortName(String shortName) {
		for (int i = 0; i < MONTHS_SHORT_EN.length; i++) {
			String mName = MONTHS_SHORT_EN[i];
			if (mName.equalsIgnoreCase(shortName)) {
				return i + 1;
			}
		}
		return 0;
	}

	public static int getMonthYearFromText(String input) {
		int monthYear = 0;
		String month = input.substring(0, 3);
		String year = input.substring(3, input.length());
		int monthNum = getMonthFromShortName(month);
		int yearNum = getYearFromShortform(year);
		monthYear = yearNum * 100 + monthNum;
		return monthYear;
	}

	public static Timestamp getNextday(java.util.Date adate, String dayin) {
		Calendar cal = Calendar.getInstance();

		cal.setTime(adate);
		while (cal.get(7) != getDayInt(dayin)) {
			cal.add(5, 1);
		}
		return convertUtiltoTimestamp(cal.getTime());
	}

	public static java.util.Date getNextMonthEndDate(int shipmonthnumber) {
		int year = shipmonthnumber / 100;
		int month = shipmonthnumber % 100 - 1;
		Calendar now = GregorianCalendar.getInstance();

		now.set(1, year);
		now.set(2, month);
		now.set(5, 1);
		now.add(2, 2);
		now.add(5, -1);
		return now.getTime();
	}

	private static Timestamp getNextWorkingday(Timestamp adate, String flags) {
		while (isHoliday(adate)) {
			adate = getDateAfter(adate, 1, flags, false);
		}
		return adate;
	}

	public static Timestamp getPreWorkingday(Timestamp adate, String flags) {
		while (isHoliday(adate)) {
			adate = getDateBefore(adate, 1, flags, false);
		}
		return adate;
	}

	public static int getSecond(java.util.Date date) {
		if (date == null) {
			return 0;
		}
		return Integer.valueOf(format(date, "ss")).intValue();
	}

	public static int getSecond(Long date) {
		return getSecond(date == null ? null : new java.util.Date(
				date.longValue()));
	}

	public static java.sql.Date getSqlDate(java.util.Date sqldate) {
		return new java.sql.Date(sqldate.getTime());
	}

	public static java.util.Date getStartDate(int shipmonthnumber) {
		int year = shipmonthnumber / 100;
		int month = shipmonthnumber % 100 - 1;
		Calendar now = GregorianCalendar.getInstance();

		now.set(1, year);
		now.set(2, month);
		now.set(5, 1);
		return now.getTime();
	}

	/*
	 * public static synchronized List<TimeZoneUTC> getTimezonelist() throws
	 * Exception { if ((timezonelist == null) || (timezonelist.isEmpty())) {
	 * timezonelist = TimeZoneUTC.getAvaibleTimeZoneUTC(); } return
	 * timezonelist; }
	 * 
	 * public static String getTimeZoneUTC(TimeZone timeZone) throws Exception {
	 * String timeZoneUTC = null;
	 * 
	 * if (timeZone != null) { List list = TimeZoneUTC.getAvaibleTimeZoneUTC();
	 * TimeZoneUTC timeZoneUtc = new TimeZoneUTC(timeZone.getID()); int index =
	 * list.indexOf(timeZoneUtc);
	 * 
	 * if (index > -1) { TimeZoneUTC newTimeZoneUtc =
	 * (TimeZoneUTC)list.get(index); timeZoneUTC = newTimeZoneUtc.getUtc(); } }
	 * return timeZoneUTC; }
	 */
	private static long getUTCCurrent() {
		Calendar cal = Calendar.getInstance();

		long UTC = cal.getTimeInMillis() - cal.get(15) - cal.get(16);

		return UTC;
	}

	public static java.util.Date getUTCCurrentDate() {
		return new java.util.Date(getUTCCurrent());
	}

	public static java.sql.Date getUTCCurrentSqlDate() {
		return new java.sql.Date(getUTCCurrent());
	}

	public static Timestamp getUTCCurrentTimeStamp() {
		return new Timestamp(getUTCCurrent());
	}

	public static int getYear(java.util.Date inDate) {
		Calendar aCal = GregorianCalendar.getInstance();

		aCal.setTime(inDate);
		return aCal.get(1);
	}

	public static int getYear(Long date) {
		return getYear(date == null ? null : new java.util.Date(
				date.longValue()));
	}

	public static int getYearFromShortform(String shortform) {
		int year = 0;
		try {
			java.util.Date date = new SimpleDateFormat("yy", Locale.ENGLISH)
					.parse(shortform);
			Calendar cal = GregorianCalendar.getInstance();
			cal.setTime(date);
			year = cal.get(1);
		} catch (Exception ex) {
			// logger.error(ex);
		}
		return year;
	}

	public static int getYearMonth(java.util.Date inDate) {
		Calendar aCal = GregorianCalendar.getInstance();

		aCal.setTime(inDate);
		int aMonth = aCal.get(2) + 1;
		int aYear = aCal.get(1);

		return aYear * 100 + aMonth;
	}

	private static java.util.Date gregCalToDate(GregorianCalendar greg) {
		int year = greg.get(1);
		int month = greg.get(2) + 1;
		int day = greg.get(5);
		int hour = greg.get(11);
		int min = greg.get(12);
		int sec = greg.get(13);

		if ((hour == 0) && (min == 0) && (sec == 0))
			return valueOf(year, month, day);
		if (sec == 0) {
			return valueOf(year, month, day, hour, min);
		}
		return valueOf(year, month, day, hour, min, sec);
	}

	private static boolean isHoliday(java.util.Date date) {
		boolean isHoliday = false;
		GregorianCalendar calendar = null;
		int dayOfWeek = 0;

		if (date != null) {
			calendar = new GregorianCalendar(TimeZone.getDefault());

			calendar.setTime(date);
			dayOfWeek = calendar.get(7);

			if ((dayOfWeek == 7) || (dayOfWeek == 1)) {
				isHoliday = true;
			}
		}
		return isHoliday;
	}

	public static boolean isLeap() {
		Calendar cal = Calendar.getInstance();

		return isLeap(cal.get(1));
	}

	public static boolean isLeap(int year) {
		Calendar cal = Calendar.getInstance();

		cal.set(1, year);
		return cal.getActualMaximum(6) > 365;
	}

	public static int lastDayOf(java.util.Date date) {
		if (date == null) {
			return 0;
		}
		GregorianCalendar greg = getGregorianCalendar(date);

		return greg.getActualMaximum(5);
	}

	public static Timestamp mergeDate(java.util.Date targetDate,
			java.util.Date sourceDate) {
		long milliSeconds;
		milliSeconds = null != targetDate ? (milliSeconds = targetDate
				.getTime()) : 0L;
		Calendar targetCalendar = Calendar.getInstance();
		targetCalendar.setTimeInMillis(milliSeconds);
		Calendar sourceCalendar = Calendar.getInstance();
		sourceCalendar.setTime(sourceDate);
		targetCalendar.set(sourceCalendar.get(1), sourceCalendar.get(2),
				sourceCalendar.get(5));

		return assembleTimestamp(targetCalendar);
	}

	public static Time mergeHour(java.util.Date targetDate, Integer sourceHour) {
		long milliSeconds;
		milliSeconds = null != targetDate ? (milliSeconds = targetDate
				.getTime()) : 0L;
		Calendar targetCalendar = Calendar.getInstance();
		targetCalendar.setTimeInMillis(milliSeconds);
		targetCalendar.set(11, sourceHour.intValue());
		return assembleTime(targetCalendar);
	}

	public static Time mergeMinute(java.util.Date targetDate,
			Integer sourceMinute) {
		long milliSeconds;
		milliSeconds = null != targetDate ? (milliSeconds = targetDate
				.getTime()) : 0L;
		Calendar targetCalendar = Calendar.getInstance();
		targetCalendar.setTimeInMillis(milliSeconds);
		targetCalendar.set(12, sourceMinute.intValue());
		return assembleTime(targetCalendar);
	}

	public static Timestamp mergeTime(java.util.Date targetDate,
			java.util.Date sourceDate) {
		long milliSeconds;
		milliSeconds = null != targetDate ? (milliSeconds = targetDate
				.getTime()) : 0L;
		Calendar targetCalendar = Calendar.getInstance();
		targetCalendar.setTimeInMillis(milliSeconds);
		Calendar sourceCalendar = Calendar.getInstance();
		sourceCalendar.setTime(sourceDate);
		targetCalendar.set(11, sourceCalendar.get(11));
		targetCalendar.set(12, sourceCalendar.get(12));
		targetCalendar.set(13, sourceCalendar.get(13));
		targetCalendar.set(14, sourceCalendar.get(14));
		return assembleTimestamp(targetCalendar);
	}

	public static java.util.Date setDay(java.util.Date date, int day) {
		GregorianCalendar calendar = getGregorianCalendar(date);

		if (day < 1) {
			day = 1;
		} else {
			int lastDay = calendar.getActualMaximum(5);

			if (day > lastDay) {
				day = lastDay;
			}
		}
		calendar.set(5, day);
		return gregCalToDate(calendar);
	}

	public static java.util.Date trunc(java.util.Date date) {
		if (date == null) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(DateUtils.EXT_FORMAT);

		return java.sql.Date.valueOf(sdf.format(date));
	}

	public static java.util.Date trunc(java.util.Date date, String truncFormat)
			throws Exception {
		if (date == null) {
			return null;
		}
		String yyyy = format(date, "yyyy");
		String MM = format(date, "MM");
		String dd = format(date, "dd");
		String HH = format(date, "HH");
		String mm = format(date, "mm");
		String ss = "00";

		if (truncFormat.equalsIgnoreCase("yyyy")) {
			MM = "01";
			dd = "01";
			HH = "00";
			mm = "00";
		} else if (truncFormat.equals("MM")) {
			dd = "01";
			HH = "00";
			mm = "00";
		} else if (truncFormat.equalsIgnoreCase("dd")) {
			HH = "00";
			mm = "00";
		} else if (truncFormat.equalsIgnoreCase("hh")) {
			mm = "00";
		} else if (!truncFormat.equals("mm")) {
			throw new Exception(new StringBuilder().append("Format (")
					.append(truncFormat)
					.append(") untuk proses TRUNC tidak dikenal !!").toString());
		}
		return valueOf(new StringBuilder().append(yyyy).append("-").append(MM)
				.append("-").append(dd).append(" ").append(HH).append(":")
				.append(mm).append(":").append(ss).toString(),
				"yyyy-MM-dd HH:mm:ss");
	}

	public static java.util.Date trunc(String truncFormat) throws Exception {
		return trunc(getCurrentTimeStamp(), truncFormat);
	}

	public static Timestamp valueOf(int year, int month, int day) {
		return valueOf(new StringBuilder().append(Integer.toString(year))
				.append("-").append(Integer.toString(month)).append("-")
				.append(Integer.toString(day)).toString(), "yyyy-MM-dd");
	}

	public static Timestamp valueOf(int year, int month, int day, int hour,
			int min) {
		return valueOf(
				new StringBuilder().append(Integer.toString(year)).append("-")
						.append(Integer.toString(month)).append("-")
						.append(Integer.toString(day)).append(" ")
						.append(Integer.toString(hour)).append(":")
						.append(Integer.toString(min)).toString(),
				"yyyy-MM-dd HH:mm");
	}

	public static Timestamp valueOf(int year, int month, int day, int hour,
			int min, int sec) {
		return valueOf(
				new StringBuilder().append(Integer.toString(year)).append("-")
						.append(Integer.toString(month)).append("-")
						.append(Integer.toString(day)).append(" ")
						.append(Integer.toString(hour)).append(":")
						.append(Integer.toString(min)).append(":")
						.append(Integer.toString(sec)).toString(),
				"yyyy-MM-dd HH:mm:ss");
	}

	public static Timestamp valueOf(String date) {
		return valueOf(date, getDefaultLocale(), DateUtils.EXT_FORMAT);
	}

	public static Timestamp valueOf(String date, Locale locale) {
		return valueOf(date, locale, DateUtils.EXT_FORMAT);
	}

	public static Timestamp valueOf(String date, Locale locale, String pattern) {
		Timestamp timestamp = null;
		SimpleDateFormat sdf = null;
		try {
			if ((date != null) && (locale != null) && (pattern != null)) {
				sdf = new SimpleDateFormat(pattern, locale);

				sdf.setLenient(false);
				timestamp = new Timestamp(sdf.parse(date).getTime());
			}
		} catch (Exception ex) {

		}
		return timestamp;
	}

	public static Timestamp valueOf(String date, String pattern) {
		return valueOf(date, getDefaultLocale(), pattern);
	}

	static {
		DateFormatSymbols dateFormatSymbolsEN = DateFormatSymbols
				.getInstance(Locale.ENGLISH);
		DateFormatSymbols dateFormatSymbolsID = DateFormatSymbols
				.getInstance(new Locale("id", ""));
		String[] values = null;

		values = dateFormatSymbolsEN.getMonths();
		MONTHS_LONG_EN = Arrays.copyOf(values, values.length - 1);
		values = dateFormatSymbolsEN.getShortMonths();
		MONTHS_SHORT_EN = StringUtil.toUpperCase(Arrays.copyOf(values,
				values.length - 1));
		values = dateFormatSymbolsEN.getWeekdays();
		DAYS_LONG_EN = Arrays.copyOfRange(values, 1, values.length);
		values = dateFormatSymbolsEN.getShortWeekdays();
		DAYS_SHORT_EN = StringUtil.toUpperCase(Arrays.copyOfRange(values, 1,
				values.length));
		values = dateFormatSymbolsID.getMonths();
		MONTHS_LONG_ID = Arrays.copyOf(values, values.length - 1);
		values = dateFormatSymbolsID.getShortMonths();
		MONTHS_SHORT_ID = StringUtil.toUpperCase(Arrays.copyOf(values,
				values.length - 1));
		values = dateFormatSymbolsID.getWeekdays();
		DAYS_LONG_ID = Arrays.copyOfRange(values, 1, values.length);
		values = dateFormatSymbolsID.getShortWeekdays();
		DAYS_SHORT_ID = StringUtil.toUpperCase(Arrays.copyOfRange(values, 1,
				values.length));

		// timezonelist = new ArrayList();
	}

	static enum DateFormat {
		CURRENCT_MONTH_DATE(null, null, "date"), DATE(null, null, "date"), MONTH_LONG_EN(
				DateUtils.MONTHS_LONG_EN, null, "month"), MONTH_SHORT_EN(
				DateUtils.MONTHS_SHORT_EN, null, "month"), MONTH_EN(
				DateUtils.MONTHS_LONG_EN, DateUtils.MONTHS_SHORT_EN, "month"), DAY_LONG_EN(
				DateUtils.DAYS_LONG_EN, null, "day"), DAY_SHORT_EN(
				DateUtils.DAYS_SHORT_EN, null, "day"), DAY_EN(
				DateUtils.DAYS_LONG_EN, DateUtils.DAYS_SHORT_EN, "day"), MONTH_LONG_ID(
				DateUtils.MONTHS_LONG_ID, null, "month"), MONTH_SHORT_ID(
				DateUtils.MONTHS_SHORT_ID, null, "month"), MONTH_ID(
				DateUtils.MONTHS_LONG_ID, DateUtils.MONTHS_SHORT_ID, "month"), DAY_LONG_ID(
				DateUtils.DAYS_LONG_ID, null, "day"), DAY_SHORT_ID(
				DateUtils.DAYS_SHORT_ID, null, "day"), DAY_ID(
				DateUtils.DAYS_LONG_ID, DateUtils.DAYS_SHORT_ID, "day");

		private final String[] labels;
		private final String[] values;
		private final String label;

		private DateFormat(String[] labels, String[] values, String label) {
			this.labels = labels;
			this.values = values;
			this.label = label;
		}

		public String getLabel() {
			return this.label;
		}

		public String[] getLabels() {
			String[] labelsResult = null;

			if (this.labels != null) {
				labelsResult = this.labels;
			} else {
				int length = getSize();
				labelsResult = new String[length];

				for (int i = 0; i < length; i++) {
					labelsResult[i] = org.apache.commons.lang3.StringUtils
							.leftPad(String.valueOf(i + 1), 2, '0');
				}
			}

			return labelsResult;
		}

		public int getSize() {
			int size = 0;

			if (this == DATE)
				size = 31;
			else if (this == CURRENCT_MONTH_DATE)
				size = DateUtils.lastDayOf(DateUtils.getCurrentDate());
			else if (this.labels != null)
				size = this.labels.length;
			else if (this.values != null) {
				size = this.values.length;
			}
			return size;
		}

		public String[] getValues() {
			String[] returnValues = null;

			if (this.values != null) {
				returnValues = this.values;
			} else {
				int length = getSize();
				returnValues = new String[length];

				for (int i = 0; i < length; i++) {
					returnValues[i] = String.valueOf(i + 1);
				}
			}
			return returnValues;
		}
	}
}
