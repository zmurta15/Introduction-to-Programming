package boleia;

public class BasicDate {
	private static final int NUM_FIELDS = 3;

	private int[] rawDate;

	private static final int JANUARY = 1;
	private static final int FEBRUARY = 2;
	private static final int MARCH = 3;
	private static final int APRIL = 4;
	private static final int MAY = 5;
	private static final int JUNE = 6;
	private static final int JULY = 7;
	private static final int AUGUST = 8;
	private static final int SEPTEMBER = 9;
	private static final int OCTOBER = 10;
	private static final int NOVEMBER = 11;
	private static final int DECEMBER = 12;

	/***
	 * Builds a new raw date object.
	 * 
	 * @param date -- a string of the form N1-N2-N3, where N1,N2,N3 are positive
	 *             numbers representable as integers.
	 */
	public BasicDate(String date) {
		String[] split = date.split("-");
		rawDate = new int[NUM_FIELDS];

		for (int i = 0; i < split.length; i++) {
			rawDate[i] = Integer.parseInt(split[i].trim());
		}

	}

	/**
	 * Verifica se a data e valida
	 * 
	 * @return true, se a data e valida ou false, caso contrario
	 */
	public boolean isValid() {
		return (getDay() > 0 && getDay() <= returnDays(getMonth(), getYear()) && getYear() > 0 && getMonth() > 0
				&& getMonth() <= 12);

	}

	/**
	 * Retorna os dias, o mes e ano de uma data valida
	 * 
	 * @param month
	 * @param year
	 * @return days
	 */
	private int returnDays(int month, int year) {
		int days = 0;
		switch (month) {
		case JANUARY:
		case MARCH:
		case MAY:
		case JULY:
		case AUGUST:
		case OCTOBER:
		case DECEMBER:
			days = 31;
			break;
		case APRIL:
		case JUNE:
		case SEPTEMBER:
		case NOVEMBER:
			days = 30;
		case FEBRUARY:
			if (isLeapYear(year)) {
				days = 29;
			} else {
				days = 28;
			}
		}
		return days;
	}

	/**
	 * Returns the year field of this date, assuming the string used in the
	 * constructor was a valid date (i.e., isValid() ).
	 * 
	 */
	public int getYear() {
		return rawDate[2];
	}

	/**
	 * Returns the day field of this date, assuming the string used in the
	 * constructor was a valid date (i.e., isValid() ).
	 * 
	 */
	public int getDay() {
		return rawDate[0];
	}

	/**
	 * Returns the month field of this date, assuming the string used in the
	 * constructor was a valid date (i.e., isValid() ).
	 * 
	 */
	public int getMonth() {
		return rawDate[1];
	}

	/**
	 * Verifica se o ano e bissexto
	 * 
	 * @param year
	 * @return true, caso seja bissexto, false caso contrario
	 */
	private boolean isLeapYear(int year) {
		int i = 0;
		if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {
			i = 1;
		}
		return i == 1;
	}

}
