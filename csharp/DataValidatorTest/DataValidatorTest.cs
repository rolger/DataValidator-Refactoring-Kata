using DataValidatorProject;
using Xunit;

namespace DataValidatorTest
{
    public class DataValidatorTest
    {
        [Fact]
        public void Test()
        {
            CountryInfoService countryInfoService = new CountryInfoService();
            DataValidator data = new DataValidator(countryInfoService);
            data.Check(null);
            Assert.True(false, "Not yet implemented");
        }
    }
}