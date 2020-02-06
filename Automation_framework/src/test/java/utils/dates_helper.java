package utils;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class dates_helper {

	public boolean check_HashMap(HashMap<String, Boolean> mp) {
		boolean final_result = true;
		Iterator it = mp.entrySet().iterator();

		while (it.hasNext()) {
			HashMap.Entry pair = (HashMap.Entry) it.next();
			if (pair.getValue().toString() == "false") {
				final_result = false;
				break;
			}
			// System.out.println(pair.getKey() + " = " + pair.getValue());
			it.remove(); // avoids a ConcurrentModificationException

		}
		return final_result;
	}

	public static List<String> get_date_as_list(String dates) {
		List<String> dates1 = new ArrayList<String>();
		String str[] = dates.split("\n");
		dates1 = Arrays.asList(str);

		return dates1;
	}

	public int get_results_count(String dates) {
		List<String> dates1 = new ArrayList<String>();
		String str[] = dates.split("\n");
		dates1 = Arrays.asList(str);
		return dates1.size();
	}

	public static HashMap<String, Boolean> check_dates(List<String> dates1, String format) {
		HashMap<String, Boolean> results = new HashMap<>();

		switch (format) {
		case "yyyy-MMMMM":
			for (String s : dates1) {
				boolean result = validate_custom_Date_format1(s.trim(), format);
				results.put(s, result);
			}
			break;
		case "yyyy-dd-MMMMM":
			for (String s : dates1) {
				boolean result = validate_custom_Date_format2(s.trim(), format);
				results.put(s, result);
			}
			break;
		case "MMMMM":
			for (String s : dates1) {
				boolean result = validate_custom_Date_format3(s.trim(), format);
				results.put(s, result);
			}
			break;
		case "yy-MMMMM":
			for (String s : dates1) {
				boolean result = validate_custom_Date_format1(s.trim(), format);
				results.put(s, result);
			}
			break;
		case "yy-MMMMM-d":
			for (String s : dates1) {
				boolean result = validate_custom_Date_format5(s.trim(), format);
				results.put(s, result);
			}
			break;
		default:
			for (String s : dates1) {
				boolean result = validateDate_format(s.trim(), format);
				results.put(s, result);
			}
			break;
		}
		return results;
	}

	public static boolean validateDate_format(String dateToValidate, String dateFromat) {
		Date date2 = null;
		Boolean is_date_valid = false;

		if (dateToValidate == null) {
			return false;
		}

		SimpleDateFormat sdf = new SimpleDateFormat(dateFromat);
		sdf.setLenient(false);

		try {

			// if not valid, it will throw ParseException
			Date date = sdf.parse(dateToValidate);
			System.out.println(date);

		} catch (ParseException e) {

			e.printStackTrace();
			return false;
		}

		return true;
	}

	public static boolean validate_custom_Date_format1(String dateToValidate, String dateFromat) {

		String str[] = dateToValidate.split("-");
		System.out.println(str.length);
		if (dateToValidate == null || !str[1].matches("[a-zA-Z]+")) {
			return false;
		} else {

			SimpleDateFormat sdf = new SimpleDateFormat(dateFromat);
			sdf.setLenient(false);

			try {

				// if not valid, it will throw ParseException
				Date date = sdf.parse(dateToValidate);
				System.out.println(date);

			} catch (ParseException e) {

				e.printStackTrace();
				return false;
			}

			return true;
		}
	}

	// custom date format 2
	public static boolean validate_custom_Date_format2(String dateToValidate, String dateFromat) {

		String str[] = dateToValidate.split("-");
		System.out.println(str.length);
		if (dateToValidate == null || !str[2].matches("[a-zA-Z]+")) {
			return false;
		} else {

			SimpleDateFormat sdf = new SimpleDateFormat(dateFromat);
			sdf.setLenient(false);

			try {

				// if not valid, it will throw ParseException
				Date date = sdf.parse(dateToValidate);
				System.out.println(date);

			} catch (ParseException e) {

				e.printStackTrace();
				return false;
			}

			return true;
		}
	}

	// custom date format 3
	// TC_07
	public static boolean validate_custom_Date_format3(String dateToValidate, String dateFromat) {

		String str[] = dateToValidate.split("-");
		System.out.println(str.length);
		if (dateToValidate == null || !str[0].matches("[a-zA-Z]+")) {
			return false;
		} else {

			SimpleDateFormat sdf = new SimpleDateFormat(dateFromat);
			sdf.setLenient(false);

			try {

				// if not valid, it will throw ParseException
				Date date = sdf.parse(dateToValidate);
				System.out.println(date);

			} catch (ParseException e) {

				e.printStackTrace();
				return false;
			}

			return true;
		}
	}

	// custom date format5
	public static boolean validate_custom_Date_format5(String dateToValidate, String dateFromat) {

		String str[] = dateToValidate.split("-");
		System.out.println(str.length);
		if (dateToValidate == null || !str[1].matches("[a-zA-Z]+")) {
			return false;
		} else {

			SimpleDateFormat sdf = new SimpleDateFormat(dateFromat);
			sdf.setLenient(false);

			try {

				// if not valid, it will throw ParseException
				Date date = sdf.parse(dateToValidate);
				System.out.println(date);

			} catch (ParseException e) {

				e.printStackTrace();
				return false;
			}

			return true;
		}
	}

	

}