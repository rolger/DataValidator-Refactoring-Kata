package com.craftmanship;

import static org.approvaltests.Approvals.verify;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

class DataValidatorTest {

	@Test
	void shouldContainNoErrorsIfDataIsValid() {
		Map<Integer, List<String>> data = Map.of(1, List.of("Susi", "Sunshine", "AT", "1971-12-04", "2300.20"));
		
		List<ErrorInfo> errors = new DataValidator(withCountry("AT")).check(data);
		
		assertThat(errors).isEmpty();
	}

	
	@Test
	void shouldContainNoErrorsIfDataIsEmpty() {
		Map<Integer, List<String>> data = new HashMap<>();
		
		data.put(0, null);
		data.put(1, List.of());
		
		List<ErrorInfo> errors = new DataValidator(withCountry("AT")).check(data);
		
		assertThat(errors).isEmpty();
	}
	
	@Test
	void shouldContainErrorsForEachInvalidEntry() {
		Map<Integer, List<String>> data = Map.of(
				1, List.of("Susi", "", "AT", "1971-12-04", "2300.20"), 
				2, List.of("", "Sunshine", "AT", "1971-12-04", "2300.20"),
				3, List.of("A1", "Sunshine", "AT", "1971-12-04", "2300.20"),
				4, List.of("Susi", "Sunshine", "DE", "1971-12-04", "2300.20"),
				5, List.of("Susi", "Sunshine", "AT", "not a date", "2300.20"),
				6, List.of("Susi", "Sunshine", "AT", "1971-12-04", "not a BigDecimal"),
				7, List.of("Susi", "Sunshine", "AT", "1971-12-04", "-1")
				);
		
		List<ErrorInfo> errors = new DataValidator(withCountry("AT")).check(data);
		
		verify(errors.stream().map(ErrorInfo::message).sorted().collect(Collectors.joining("\n")));
	}

	private CountryInfoService withCountry(String contryCode) {
		CountryInfoService countryInfoService = new CountryInfoService() {
			@Override
			public List<String> getAllCountries() {
				return List.of(contryCode);
			}
		};
		return countryInfoService;
	}

}
