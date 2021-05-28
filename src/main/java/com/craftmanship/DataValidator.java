package com.craftmanship;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.lang.String.format;

public class DataValidator {

    private CountryInfoService countryInfoService;

    public DataValidator(CountryInfoService countryInfoService) {
        this.countryInfoService = countryInfoService;
    }

    public List<ErrorInfo> check(Map<Integer, List<String>> data) {
        List<ErrorInfo> errors = new ArrayList<>();
        data.entrySet().stream()
                .filter(entry -> entry.getValue() != null)
                .filter(entry -> !entry.getValue().isEmpty())
                .forEach(entry -> {
                    Integer row = entry.getKey();
                    List<String> columns = entry.getValue();

                    errors.addAll(validateField(row, new Validator(columns.get(0), "first name must contain characters", this::validFirstName)));
                    errors.addAll(validateField(row, new Validator(columns.get(1), "last name must contain characters", this::validLastName)));
                    errors.addAll(validateField(row, new Validator(columns.get(2), format("country code (%s) doesn't exist", columns.get(2)), this::validCountry)));
                    errors.addAll(validateField(row, new Validator(columns.get(3), format("birthdate (%s) can not be parsed", columns.get(3)), this::validDate)));
                    errors.addAll(validateField(row, new Validator(columns.get(4), format("income (%s) can not be parsed", columns.get(4)), this::validMoney)));
                });
        return errors;
    }

    private List<ErrorInfo> validateField(Integer row, Validator validator) {
        List<ErrorInfo> errorInfo = new ArrayList<>();
        if (!validator.isValid()) {
            errorInfo.add(new ErrorInfo(row, validator.getMessage()));
        }
        return errorInfo;
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

    private boolean validCountry(String string) {
        return countryInfoService.getAllCountries().stream().anyMatch(c -> c.equals(string));
    }

    private boolean validDate(String string) {
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