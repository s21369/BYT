using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Calculator.DTOs {

    public class Request {

        public Request(Number number1, Number number2, string operation) {
            _number1 = number1;
            _number2 = number2;
            _operation = operation;
        }

        private string _operation;

        public string Operation {
            get { return _operation; }
            set { _operation = value; }
        }

        private Number _number1;

        public Number Number1 {
            get { return _number1; }
            set { _number1 = value; }
        }

        private Number _number2;

        public Number Number2 {
            get { return _number2; }
            set { _number2 = value; }
        }

    }

}
