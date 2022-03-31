using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DataValidatorProject
{
    public class ErrorInfo: Object
    {
		private int row;
		private string detailMessage;

		public ErrorInfo(int row, string detailMessage)
		{
			this.row = row;
			this.detailMessage = detailMessage;
		}

		public string message()
		{
			return String.Format("Invalid data in row %d (%s)", row, detailMessage);
		}
	}
}
