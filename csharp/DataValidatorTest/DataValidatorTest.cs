using System.Collections;
using System.Collections.Generic;
using DataValidator;
using FluentAssertions;
using Xunit;

namespace DataValidatorTest;

public class DataValidatorTest
{
    [Fact]
    public void Test()
    {
        var dataValidator = new DataValidator.DataValidator(new CountryInfoServiceFake());

        var errors = dataValidator.Check(new Dictionary<int, List<string>>());

        errors.Should().Be("Implement your tests here");
    }
}

public class CountryInfoServiceFake : CountryInfoService
{
    public override IEnumerable GetAllCountries()
    {
        return new ArrayList();
    }
}