package com.framework.util;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Constructor;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.text.MessageFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerKey;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 工具类
 * 
 * 
 * 
 * 
 */
public class Utils {
	public static class Mapper {
		private Map<Object, Object> result;

		public Mapper(Map<Object, Object> result) {
			this.result = result;
		}

		public Mapper put(Object key, Object value) {
			result.put(key, value);
			return this;
		}

		public Map<Object, Object> getResult() {
			return result;
		}
	}

	private static DecimalFormat decimalFormat;
	private static ClassLoader loader;
	private static final Logger logger;

	static {
		NumberFormat numberFormat = NumberFormat
				.getNumberInstance(Locale.ENGLISH);
		decimalFormat = (DecimalFormat) numberFormat;
		decimalFormat.applyPattern("#.##");
		loader = Thread.currentThread().getContextClassLoader();
		if (loader == null) {
			loader = Utils.class.getClassLoader();
		}
		logger = LoggerFactory.getLogger(Utils.class);
	}

	public static String getContextPath() {
		return String.valueOf(SimpleCache.get("context"));
	}

	public static String subString(String target, int length) {
		if (target != null && target.length() > length) {
			return target.substring(0, length) + "...";
		} else {
			return target;
		}
	}
    
	/**
	 * 根据文件名获取文件的前缀
	 * @param fileName
	 * @return
	 */
	public static String getExtension(String fileName) {
		String[] temp = fileName.split("\\.");
		return temp[temp.length - 1];
	}
 
	/**
	 * 根据资源的路径称获取该资源的绝对路径
	 * @param name
	 * @return
	 */
	public static URL getResource(String name) {
		return loader.getResource(name);
	}
    
	/**
	 * 根据资源路径获取字节流
	 * @param name
	 * @return
	 */
	public static InputStream getResourceAsStream(String name) {
		return loader.getResourceAsStream(name);
	}
    
	/**
	 * 根据资源路径获取字符流
	 * @param name
	 * @return
	 */
	public Reader getResourceAsReader(String name) {
		return new InputStreamReader(getResourceAsStream(name));
	}
    /**
     * 字符串首字母大写
     * @param target
     * @return
     */
	public static String capitalize(String target) {
		if (target == null || target.length() == 0) {
			return target;
		}
		StringBuilder sb = new StringBuilder(target.length());
		sb.append(Character.toUpperCase(target.charAt(0)));
		sb.append(target.substring(1));
		return sb.toString();
	}
    
	/**
	 * 将字符串根据utf-8解码
	 * @param target
	 * @return
	 */
	public static String decode(String target) {
		if (target != null) {
			try {
				target = URLDecoder.decode(target, "utf-8");
			} catch (UnsupportedEncodingException e) {
				// Swallow
			}
		}
		return target;
	}

	public static String decode(String target, int time) {
		for (int i = 0; i < time; i++) {
			target = decode(target);
		}
		return target;
	}
    /***
     * 拼接跳转路径和查询条件
     * @param currentPath
     * @param queryString
     * @return
     */
	public static String spliceURI(String currentPath, String queryString) {
		if (isEmpty(currentPath)) {
			return currentPath;
		}
		if (!currentPath.startsWith("/")) {
			currentPath = "/" + currentPath;
		}
		if (isNotEmpty(queryString)) {
			return currentPath + "?" + queryString;
		} else {
			return currentPath;
		}
	}
    /**
     * 将字符串以utf-8编码
     * @param target
     * @return
     */
	public static String encode(String target) {
		if (target != null) {
			try {
				target = URLEncoder.encode(target, "utf-8");
			} catch (UnsupportedEncodingException e) {
				// Swallow
			}
		}
		return target;
	}

	public static String encode(String target, int time) {
		for (int i = 0; i < time; i++) {
			target = encode(target);
		}
		return target;
	}

	public static List<String> asList(Object[] target) {
		List<String> list = new ArrayList<String>();
		for (Object obj : target) {
			list.add(String.valueOf(obj));
		}
		return list;
	}
    
	/**
	 * 把给定的字符串里含有regex子字符串进行高亮显示。
	 * @param target
	 * @param regex
	 * @return
	 */
	public static String htmlHighlight(String target, String regex) {
		Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
		Matcher match = pattern.matcher(target);
		StringBuilder sb = new StringBuilder();
		int bi = 0, ei = 0;
		while (match.find()) {
			if (match.group().trim().equals("")) {
				continue;
			} else {
				ei = match.start();
				sb.append(target.substring(bi, ei));
				sb.append("<em>");
				sb.append(match.group());
				sb.append("</em>");
				bi = match.end();
			}
		}
		sb.append(target.substring(bi));
		return sb.toString();
	}
    /**
     * 将html中德转义字符解码
     * @param target
     * @return
     */
	public static String decodeHTML(String target) {
		Map<String, Character> map = new HashMap<String, Character>();
		map.put("&lt;", '<');
		map.put("&gt;", '>');
		map.put("&amp;", '&');
		map.put("&quot;", '"');
		Pattern pattern = Pattern
				.compile("&lt;|&gt;|&amp;|&quot;|&#[0-9]{1,5};");
		Matcher match = pattern.matcher(target);
		while (match.find()) {
			String key = match.group();
			if (map.containsKey(key)) {
				target = target.replace(key, String.valueOf(map.get(key)));
			} else {
				target = target.replace(key, key.substring(2));
			}
		}
		return target;
	}

	public static String byteDesc(long len) {
		double val = 0.0;
		String ending = "";
		if (len < 1024) {
			val = len;
			ending = "B";
		} else if (len < 1024 * 1024) {
			val = (1.0 * len) / 1024;
			ending = " KB";
		} else if (len < 1024 * 1024 * 1024) {
			val = (1.0 * len) / (1024 * 1024);
			ending = " MB";
		} else if (len < 1024L * 1024 * 1024 * 1024) {
			val = (1.0 * len) / (1024 * 1024 * 1024);
			ending = " GB";
		} else if (len < 1024L * 1024 * 1024 * 1024 * 1024) {
			val = (1.0 * len) / (1024L * 1024 * 1024 * 1024);
			ending = " TB";
		} else {
			val = (1.0 * len) / (1024L * 1024 * 1024 * 1024 * 1024);
			ending = " PB";
		}
		return decimalFormat.format(val) + ending;
	}
    /**
     * 对字符串进行MD5加密
     * @param str
     * @param full
     * @return
     */
	public static String getMD5(String str, boolean full) {
		byte[] input = str.getBytes();
		char[] hexchr = "0123456789abcdef".toCharArray();
		char[] outstr = new char[32];
		String strout = null;
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(input);
			byte[] output = md.digest();
			int k = 0;
			for (int i = 0; i < output.length; i++) {
				outstr[k++] = hexchr[output[i] >>> 4 & 0xf];
				outstr[k++] = hexchr[output[i] & 0xf];
			}
			strout = new String(outstr);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		if (full) {
			return strout;
		} else {
			return strout.substring(8, 24);
		}
	}
    /**
     * 判断对象是否为空，为空时返回true
     * @param target
     * @return
     */
	public static boolean isEmpty(Object target) {
		if (target == null) {
			return true;
		} else if (target instanceof String) {
			return String.valueOf(target).trim().equals("");
		} else if (target instanceof Collection<?>) {
			return ((Collection<?>) target).isEmpty();
		} else if (target instanceof Object[]) {
			return ((Object[]) target).length == 0;
		} else {
			return false;
		}
	}

	public static boolean isNotEmpty(Object target) {
		return !isEmpty(target);
	}

	public static boolean isShort(Object target) {
		try {
			Short.parseShort(String.valueOf(target));
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	public static boolean isNotShort(Object target) {
		return !isShort(target);
	}

	public static boolean isInt(Object target) {
		try {
			Integer.parseInt(String.valueOf(target));
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	public static boolean isNotInt(Object target) {
		return !isInt(target);
	}

	public static boolean isLong(Object target) {
		try {
			Long.parseLong(String.valueOf(target));
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	public static boolean isNotLong(Object target) {
		return !isLong(target);
	}

	public static boolean isFloat(Object target) {
		try {
			Float.parseFloat(String.valueOf(target));
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	public static boolean isNotFloat(Object target) {
		return !isFloat(target);
	}

	public static boolean isDouble(Object target) {
		try {
			Double.parseDouble(String.valueOf(target));
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	public static boolean isNotDouble(Object target) {
		return !isDouble(target);
	}

	public static String getCharSequence(Object target) {
		return getCharSequence(target, "");
	}

	public static String getCharSequence(Object target, String def) {
		if (isEmpty(target)) {
			return def;
		} else {
			return String.valueOf(target);
		}
	}

	public static short getShort(Object target) {
		return getShort(target, 0);
	}

	public static short getShort(Object target, int def) {
		if (isNotShort(target)) {
			return Short.parseShort(String.valueOf(def));
		} else {
			return Short.parseShort(String.valueOf(target));
		}
	}

	public static int getInt(Object target) {
		return getInt(target, 0);
	}

	public static int getInt(Object target, int def) {
		if (isNotInt(target)) {
			return def;
		} else {
			return Integer.parseInt(String.valueOf(target));
		}
	}

	public static long getLong(Object target) {
		return getLong(target, 0);
	}

	public static long getLong(Object target, long def) {
		if (isNotLong(target)) {
			return def;
		} else {
			return Long.parseLong(String.valueOf(target));
		}
	}

	public static float getFloat(Object target) {
		return getFloat(target, 0.0f);
	}

	public static float getFloat(Object target, float def) {
		if (isNotFloat(target)) {
			return def;
		} else {
			return Float.parseFloat(String.valueOf(target));
		}
	}

	public static Double getDouble(Object target) {
		return getDouble(target, 0.0);
	}

	public static double getDouble(Object target, double def) {
		if (isNotDouble(target)) {
			return def;
		} else {
			return Double.parseDouble(String.valueOf(target));
		}
	}
    
	/**
	 * email的验证
	 * @param email
	 * @return
	 */
	public static boolean isValidEmail(String email) {
		String regexp = "^([a-zA-Z0-9_\\.\\-\\+])+\\@(([a-zA-Z0-9\\-])+\\.)+([a-zA-Z0-9]{2,4})+$";
		return email.matches(regexp);
	}

	public static String makePath(String... paths) {
		String sep = System.getProperty("file.separator");
		StringBuilder sb = new StringBuilder();
		for (String path : paths) {
			if (isEmpty(path)) {
				continue;
			}
			sb.append(path);
			sb.append(sep);
		}
		String path = sb.toString();
		path = path.replaceAll("\\" + sep + "+", "\\" + sep);
		path = path.substring(0, path.length() - 1);
		return path;
	}

	public static String getRealPath(String... paths) {
		String saveDir = String.valueOf(SimpleCache.get("SaveDir"));
		String[] temp = new String[paths.length + 1];
		temp[0] = saveDir;
		for (int i = 0; i < paths.length; i++) {
			temp[i + 1] = paths[i];
		}
		return Utils.makePath(temp);
	}

	public static String makeURI(String... paths) {
		String URI = makePath(paths);
		URI = URI.replaceAll("\\\\+", "/").replaceAll("/{2,}", "/");
		return URI;
	}

	public static String formatDate(Date date, String pattern) {
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		return format.format(date);
	}

	public static String getNow(String pattern) {
		return formatDate(new Date(), pattern);
	}

	public static String join(List<?> target, String connector) {
		StringBuilder sb = new StringBuilder();
		Iterator<?> itr = target.iterator();
		while (itr.hasNext()) {
			sb.append(itr.next());
			if (itr.hasNext()) {
				sb.append(connector);
			}
		}
		return sb.toString();
	}

	public static String join(Object[] target, String connector) {
		StringBuilder sb = new StringBuilder();
		int length = target.length;
		for (int i = 0; i < length; i++) {
			sb.append(target[i]);
			if ((i + 1) != length) {
				sb.append(connector);
			}
		}
		return sb.toString();
	}

	public static Mapper getHashMapper() {
		Mapper mapper = new Mapper(new HashMap<Object, Object>());
		return mapper;
	}

	/**
	 * 用来完成两个对象之间某些属性值的拷贝操作，拷贝时，如果发现源对象中的属性值为空， 则将目标对象中的属性值拷贝给它，否则，不进行拷贝操作
	 * 
	 * @param source
	 *            源对象
	 * @param target
	 *            目标对象
	 * @param properties
	 *            拷贝属性
	 */

	public static void copyProperties(Object source, Object target, String... properties) 
	{
		if (isEmpty(source) || isEmpty(target) || properties.length == 0) 
		{
			return;
		}
		for (String property : properties) {
			
			String readMethod = "get" + capitalize(property);
			String writeMethod = "set" + capitalize(property);
			Class<?> sourceCls = source.getClass();
			Class<?> targetCls = target.getClass();
			try
			{
				Object tresult = targetCls.getDeclaredMethod(readMethod).invoke(target);
				Object sresult = sourceCls.getDeclaredMethod(readMethod).invoke(source);
				if (isNotEmpty(tresult) && isEmpty(sresult))
				{
					Class<?> resultCls = null;
					if (tresult instanceof java.math.BigInteger) 
					{
						resultCls = Long.class;
						tresult = getLong(tresult);
					} 
					else if (tresult instanceof java.math.BigDecimal) 
					{
						resultCls = Double.class;
						tresult = getDouble(tresult);
					} 
					else if ((tresult instanceof java.sql.Date)
							|| (tresult instanceof java.sql.Time)
							|| (tresult instanceof java.sql.Timestamp))
					{
						resultCls = Date.class;
						tresult = parseDate(tresult.toString(),
								"yyyy-MM-dd hh:mm");
					} 
					else
					{
						resultCls = tresult.getClass();
					}
					sourceCls.getDeclaredMethod(writeMethod, resultCls).invoke(source, tresult);
				}
			} 
			catch (Exception e)
			{
				logger.error("属性拷贝失败", e);
			}
		}
	}

	/**
	 * 检验某个对象是否包含某些必须的属性值
	 */

	public static boolean isIntegral(Object target, String... properties) {
		if (isEmpty(target) || properties.length == 0) {
			return false;
		}
		boolean isIntegral = true;
		for (String property : properties) {
			String readMethod = "get" + capitalize(property);
			Class<?> targetCls = target.getClass();
			try {
				Object result = targetCls.getDeclaredMethod(readMethod).invoke(
						target);
				if (Utils.isEmpty(result)) {
					isIntegral = false;
					break;
				}
			} catch (Exception e) {
				logger.error("完整性检测失败", e);
			}
		}
		return isIntegral;
	}

	public static Map<Object, Object> getConvertMap(Object obj,
			String... properties) {
		if (isEmpty(obj) || properties.length == 0) {
			return null;
		}
		Class<?> cls = obj.getClass();
		Map<Object, Object> map = new HashMap<Object, Object>();
		for (String key : properties) {
			String method = "get" + capitalize(key);
			try {
				map.put(key, cls.getDeclaredMethod(method).invoke(obj));
			} catch (Exception e) {
				// Swallow
			}
		}
		return map;
	}

	public static Map<String, String> parseParam(String sequence) {
		if (Utils.isEmpty(sequence)) {
			return null;
		}
		Map<String, String> param = new HashMap<String, String>();
		String[] items = sequence.split("%26");
		for (String item : items) {
			String[] aa = item.split("%3D");
			if (aa.length >= 2) {
				param.put(aa[0], aa[1].trim());
			}
		}
		return param;
	}

	public static Object newInstance(Class<?> cls, Object... parameters) {
		if (isEmpty(cls) || isEmpty(parameters)) {
			return null;
		}
		Class<?>[] parameterTypes = getParameterTypes(cls, parameters);
		try {
			return cls.getConstructor(parameterTypes).newInstance(parameters);
		} catch (Exception e) {
			logger.error("初始化实例失败", e);
		}
		return null;
	}

	public static Class<?>[] getParameterTypes(Class<?> cls,
			Object... parameters) {
		Constructor<?>[] constructors = cls.getConstructors();
		for (Constructor<?> constructor : constructors) {
			Class<?>[] parameterTypes = constructor.getParameterTypes();
			if (parameterTypes.length != parameters.length) {
				continue;
			}
			boolean isMatch = true;
			for (int i = 0; i < parameters.length; i++) {
				Object obj = parameters[i];
				if (obj != null) {
					Class<?> expectClass = parameterTypes[i];
					if (obj instanceof java.math.BigInteger) {
						if (expectClass != Long.class) {
							isMatch = false;
							break;
						} else {
							parameters[i] = getLong(obj);
						}
					} else if (obj instanceof java.math.BigDecimal) {
						if (expectClass != Double.class) {
							isMatch = false;
							break;
						} else {
							parameters[i] = getDouble(obj);
						}
					} else if ((obj instanceof java.sql.Date)
							|| (obj instanceof java.sql.Time)
							|| (obj instanceof java.sql.Timestamp)) {
						if (expectClass != Date.class) {
							isMatch = false;
							break;
						} else {
							parameters[i] = parseDate(obj.toString(),
									"yyyy-MM-dd hh:mm");
						}
					} else {
						if (obj.getClass() != expectClass) {
							isMatch = false;
							break;
						}
					}
				}
			}
			if (isMatch) {
				return parameterTypes;
			}
		}
		return null;
	}

	public static List<Object> parseResultAsList(Class<?> target,
			List<Object[]> results) {
		List<Object> colls = new ArrayList<Object>();
		if (isNotEmpty(results)) {
			for (Object[] row : results) {
				Object obj = newInstance(target, row);
				if (obj != null) {
					colls.add(newInstance(target, row));
				}
			}
		}
		return colls;
	}

	public static List<Map<String, Object>> parseResultAsListMap(
			List<Object[]> results, String... keys) {
		return parseResultAsListMap(results, null, keys);
	}

	public static List<Map<String, Object>> parseResultAsListMap(
			List<Object[]> results, Map<String, String> patternMap,
			String... keys) {
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		if (isNotEmpty(results) && isNotEmpty(keys)) {
			for (Object[] row : results) {
				if (row.length < keys.length) {
					continue;
				}
				Map<String, Object> map = new HashMap<String, Object>();
				for (int i = 0; i < keys.length; i++) {
					String key = keys[i];
					String pattern = null;
					if (patternMap != null
							&& (pattern = patternMap.get(key)) != null) {
						MessageFormat format = new MessageFormat(pattern);
						map.put(key, format.format(new Object[] { row[i] }));
					} else {
						map.put(keys[i], row[i]);
					}
				}
				listMap.add(map);
			}
		}
		return listMap;
	}


	public static Date parseDate(String target, String pattern) {
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		try {
			return format.parse(target);
		} catch (ParseException e) {
			// Swallow
		}
		return null;
	}


	public static boolean isJasperFile(String fileName) {
		if (fileName.lastIndexOf(".jasper") != -1) {
			return true;
		}
		return false;
	}

	public static String getSaveName(String fileName) {
		String ext = Utils.getExtension(fileName);
		fileName = fileName + new Date().getTime();
		return getMD5(fileName, false) + "." + ext;
	}

	public static String getJasperName(String fileName) {
		return fileName;
	}

	/**
	 * 
	 * Quartz作业分配的相关操作
	 * 
	 */
	
	public static void secheduleJob(Class<? extends Job> jobClass,
			String jobKey, String triggerKey, String cronExpression,
			JobDataMap data) throws SchedulerException {
		Scheduler scheduler = null;
		if (SimpleCache.get("JobScheduler") != null) {
			scheduler = (Scheduler) SimpleCache.get("JobScheduler");
		} else {
			scheduler = StdSchedulerFactory.getDefaultScheduler();
			SimpleCache.put("JobScheduler", scheduler);
		}
		JobDetail job = newJob(jobClass).usingJobData(data)
				.withIdentity(jobKey).build();
		Trigger trigger = newTrigger().withIdentity(triggerKey).startNow()
				.withSchedule(cronSchedule(cronExpression)).build();
		scheduler.scheduleJob(job, trigger);
	}

	public static void rescheduleJob(String triggerKey, String cronExpression)
			throws SchedulerException {
		Scheduler scheduler = (Scheduler) SimpleCache.get("JobScheduler");
		Trigger trigger = newTrigger().withIdentity(triggerKey).startNow()
				.withSchedule(cronSchedule(cronExpression)).build();
		scheduler.rescheduleJob(TriggerKey.triggerKey(triggerKey), trigger);
	}

	public static void pauseTrigger(String triggerKey)
			throws SchedulerException {
		Scheduler scheduler = (Scheduler) SimpleCache.get("JobScheduler");
		scheduler.pauseTrigger(TriggerKey.triggerKey(triggerKey));
	}

	public static void resumeTrigger(String triggerKey)
			throws SchedulerException {
		Scheduler scheduler = (Scheduler) SimpleCache.get("JobScheduler");
		scheduler.resumeTrigger(TriggerKey.triggerKey(triggerKey));
	}

	public static void removeJob(String jobKey) throws SchedulerException {
		Scheduler scheduler = (Scheduler) SimpleCache.get("JobScheduler");
		JobKey jk = JobKey.jobKey(jobKey);
		if (scheduler.checkExists(jk)) {
			scheduler.deleteJob(jk);
		}
	}

	public static boolean hasExistJob(String jobKey) {
		Scheduler scheduler = (Scheduler) SimpleCache.get("JobScheduler");
		try {
			return scheduler.checkExists(JobKey.jobKey(jobKey));
		} catch (SchedulerException e) {
			return true;
		}
	}
}