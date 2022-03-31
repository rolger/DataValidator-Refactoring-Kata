using System;
using System.Collections;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DataValidatorProject
{
    public class DataValidator
    {
        public CountryInfoService countryInfoService;

        public DataValidator(CountryInfoService countryInfoService)
        {
            this.countryInfoService = countryInfoService;
        }

        public List<ErrorInfo> Check(Dictionary<int, List<String>> data)
        {
            List<ErrorInfo> errors = new List<ErrorInfo>();
            foreach (KeyValuePair<int, List<String>> entryKey in data)
            {
                int row = entryKey.Key;
                List<string> columns = entryKey.Value;
                if (columns != null && !columns.Any())
                {
                    if (!ValidName(columns[0]))
                    {
                        errors.Add(new ErrorInfo(row, "first name must contain characters"));
                    }

                    if (!ValidName(columns[1]))
                    {
                        errors.Add(new ErrorInfo(row, "last name must contain characters"));
                    }

                    if (!ValidC(columns[2]))
                    {
                        errors.Add(new ErrorInfo(row, String.Format("country code (%s) doesn't exist", columns[2])));
                    }

                    if (!ValidDate(columns[3]))
                    {
                        errors.Add(new ErrorInfo(row, String.Format("birthdate (%s) can not be parsed", columns[3])));
                    }

                    if (InvalidMoney(columns[4]))
                    {
                        errors.Add(new ErrorInfo(row, String.Format("income (%s) can not be parsed", columns[4])));
                    }
                }

            };
            return errors;
        }

        private bool InvalidMoney(string v)
        {
            try
            {
                decimal money = Convert.ToDecimal(v);
                if (money == -1)
                {
                    return true;
                }
                return false;
            }
            catch (FormatException)
            {
                return true;
            }
        }

        private bool ValidDate(string string1)
        {
            try
            {
                Convert.ToDateTime(string1);
            }
            catch (FormatException)
            {
                return false;
            }
            return true;
        }

        private bool ValidC(string String)
        {
            List<string> allCountries = countryInfoService.getAllCountries();
            foreach (string country in allCountries)
            {
                if (country == String)
                {
                    return true;
                }
            }
            return false;
        }

        private bool ValidName(string value)
        {
            return value != null && String.IsNullOrEmpty(value) && value.Any(char.IsLetter);
        }
    }

}
