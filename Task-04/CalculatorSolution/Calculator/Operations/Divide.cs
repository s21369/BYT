using Calculator.DTOs;
using Calculator.Handlers;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Calculator.Operations {

    public class Divide : AbstractChain {

        public override object Handle(Request request) {
            if (request.Operation.Equals("/") || request.Operation.Equals("div")) {
                var result = new Number(request.Number1.Value / request.Number2.Value);
                return $"{request.Number1.Value} / {request.Number2.Value} = {result.Value:0.###}";
            }
            return base.Handle(request);
        }

    }

}
