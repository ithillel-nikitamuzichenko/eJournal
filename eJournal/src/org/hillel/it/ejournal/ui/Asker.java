package org.hillel.it.ejournal.ui;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class Asker {
	public static String askString(String message, boolean isObligatory,
			Scanner scanner) {
		String string;
		do {
			System.out.print(message);
			string = scanner.nextLine();
		} while ((string.isEmpty()) && isObligatory);
		return string;
	}

	public static Integer askInteger(String message, boolean isObligatory,
			int minVal, int maxVal, Scanner scanner) {
		Integer value = null;
		boolean isCorrect = false;
		do {
			value = null;
			System.out.print(message);
			try {
				value = Integer.valueOf(scanner.nextLine());
			} catch (NumberFormatException e) {
				System.out.println("Ivalid number");
			}
			if ((value != null) && ((value < minVal) || (value > maxVal))) {
				System.out
						.println(String.format(
								"Value must be in range from %d to %d", minVal,
								maxVal));
			} else {
				isCorrect = true;
			}
		} while (((value == null) && isObligatory) || !isCorrect);
		return value;
	}

	public static Date askDate(String message, SimpleDateFormat sdf,
			boolean isObligatory, Scanner scanner) {
		Date date = null;
		boolean isCorrect = false;
		Calendar calendar = GregorianCalendar.getInstance();
		calendar.add(Calendar.YEAR, -90);
		do {
			date = null;
			try {
				System.out.print(message);
				date = sdf.parse(scanner.nextLine());
			} catch (ParseException e) {
				System.out.println("������ ����� ����");
			}
			if ((date != null)
					&& ((date.before(calendar.getTime())) || (date
							.after(Calendar.getInstance().getTime())))) {
				System.out.println(String.format(
						"Value must be in range from %s to %s",
						sdf.format(calendar.getTime()),
						sdf.format(Calendar.getInstance().getTime())));
			} else {
				isCorrect = true;
			}
		} while (((date == null) && isObligatory) || !isCorrect);
		return date;
	}
}
