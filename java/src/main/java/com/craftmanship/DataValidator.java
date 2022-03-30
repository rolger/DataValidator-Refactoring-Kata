package com.craftmanship;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DataValidator {

	private CountryInfoService countryInfoService;

	public DataValidator(CountryInfoService countryInfoService) {
		this.countryInfoService = countryInfoService;
	}

	public List<ErrorInfo> check(Map<Integer, List<String>> data) {

		List<ErrorInfo> errors = new ArrayList<>();
		data.forEach((row, columns) -> {

			if (columns != null && !columns.isEmpty()) {
				if (!validName(columns.get(0))) {
					errors.add(new ErrorInfo(row, "first name must contain characters"));
				}

				if (!validName(columns.get(1))) {
					errors.add(new ErrorInfo(row, "last name must contain characters"));
				}

				if (!validC(columns.get(2))) {
					errors.add(new ErrorInfo(row, String.format("country code (%s) doesn't exist", columns.get(2))));
				}

				if (!validDate(columns.get(3))) {
					errors.add(new ErrorInfo(row, String.format("birthdate (%s) can not be parsed", columns.get(3))));
				}

				if (invalidMoney(columns.get(4))) {
					errors.add(new ErrorInfo(row, String.format("income (%s) can not be parsed", columns.get(4))));
				}
			}

		});
		return errors;
	}

	private boolean validName(String value) {
		return value != null && !value.isBlank() && value.chars().allMatch(Character::isAlphabetic);
	}

	private boolean validC(String string) {
		return countryInfoService.getAllCountries().stream().anyMatch(c -> c.equals(string));
	}

	private static boolean validDate(String string) {
		try {
			LocalDate.parse(string);
		} catch (DateTimeParseException  e) {
			return false;
		}
		return true;
	}

	private boolean invalidMoney(String s) {
		try {
			BigDecimal money = new BigDecimal(s);
			if (money.compareTo(BigDecimal.ZERO) == -1) {
				return true;
			}
			return false;
		} catch (NumberFormatException e) {
			return true;
		}
	}

}