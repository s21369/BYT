using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Calculator.DTOs {

    public class Number {

        public Number(double num) {
            _num = num;
        }

        private double _num;

        public double Value {
            get { return _num; }
            set { _num = value; }
        }

    }

}
