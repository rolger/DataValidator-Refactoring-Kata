package com.craftmanship;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

// Vergleichbare andere Katas:
// Email Sender code refactroing kata
// error conditions refactroing kata?
// ValidateAndAddProduct-Refactoring-Kata

// Mögliche Änderungen:
// - neue Columns
// - Änderungen in der Reihenfolge der Columns
// - Änderungen/Fehler in Validierung
// - Mehrere Validierungen für ein Feld

// welches Design Möglichkeiten stehen zur Verfügung 
//  Liste von commands
//  Liste von Specifications
//  Monade
//  Composite Pattern
//  Template Method
//  Vavr Validation Framework?

//v1
//List<ErrorInfo> errors = new ValidatorCollector()
//	.validate(t -> Validators::validFirstName, "first name must contain characters", columns.get(0))
//	.validate(t -> Validators::validLastName, "last name must contain characters", columns.get(1))
//	.validate(t -> Validators::validXYZ, "last name must contain characters", columns.get(4))
//	.errors();
	
//v2	
//List<ValidationSpec> specs = List.of(
//	new ValidationSpec(t -> Validators::validFirstName, "first name must contain characters", columns.get(0))
//	new ValidationSpec(t -> Validators::validXYZ, "last name must contain characters", columns.get(4))
//);
//List<ErrorInfo> errors = specs.stream.flatMap(ValidationSpec::valdiate).collect(....);


// welche Wege stehen uns zur Verfügung
// bottom up refactoring
// top down
// mixed?

public class DataValidator {

	private CountryInfoService countryInfoService;

	public DataValidator(CountryInfoService countryInfoService) {
		this.countryInfoService = countryInfoService;
	}

	public List<ErrorInfo> check(Map<Integer, List<String>> data) {

		List<ErrorInfo> errors = new ArrayList<>();
		data.forEach((row, columns) -> {

			if (columns != null && !columns.isEmpty()) {
				{
					String value = columns.get(0);
					String message = "first name must contain characters";
					Predicate<String> validator = t -> validFirstName(t);

					List<ErrorInfo> errorInfo = validateField(row, value, message, validator);
					errors.addAll(errorInfo);
				}

				{
					String value = columns.get(1);
					String message = "last name must contain characters";
					Predicate<String> validator = t -> validLastName(t);

					List<ErrorInfo> errorInfo = validateField(row, value, message, validator);
					errors.addAll(errorInfo);
				}

				{
					String message = String.format("country code (%s) doesn't exist", columns.get(2));
					String value = columns.get(2);
					Predicate<String> validator = t -> validC(t);

					List<ErrorInfo> errorInfo = validateField(row, value, message, validator);
					errors.addAll(errorInfo);
				}

				{
					String value = columns.get(3);
					String message = String.format("birthdate (%s) can not be parsed", columns.get(3));
					Predicate<String> validator = t -> validDate(t);

					List<ErrorInfo> errorInfo = validateField(row, value, message, validator);
					errors.addAll(errorInfo);
				}

				{
					String value = columns.get(4);
					String message = String.format("income (%s) can not be parsed", columns.get(4));
					Predicate<String> validator = t -> validMoney(t);

					List<ErrorInfo> errorInfo = validateField(row, value, message, validator);
					errors.addAll(errorInfo);
				}
			}

		});
		return errors;
	}

	private boolean validFirstName(String t) {
		return validName(t);
	}

	private boolean validLastName(String t) {
		return validName(t);
	}

	private boolean validName(String value) {
		return value != null && !value.isBlank() && value.chars().allMatch(Character::isAlphabetic);
	}
	
	private List<ErrorInfo> validateField(Integer row, String value, String message, Predicate<String> validator) {
		List<ErrorInfo> errorInfo = new ArrayList<>();
		if (!validator.test(value)) {
			errorInfo.add(new ErrorInfo(row, message));
		}
		return errorInfo;
	}

	private boolean validC(String string) {
		return countryInfoService.getAllCountries().stream().anyMatch(c -> c.equals(string));
	}

	private static boolean validDate(String string) {
		try {
			LocalDate.parse(string);
		} catch (DateTimeParseException e) {
			return false;
		}
		return true;
	}

	private boolean validMoney(String s) {
		try {
			BigDecimal money = new BigDecimal(s);
			if (money.compareTo(BigDecimal.ZERO) == -1) {
				return false;
			}
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

}