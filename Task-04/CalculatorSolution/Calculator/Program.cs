using Calculator.DTOs;
using Calculator.Handlers;
using Calculator.Models;
using Calculator.Operations;
using System;

namespace Calculator {

    public class Program {

        public static void Main(string[] args) {

            AbstractChain addChainOperation = new Add();
            AbstractChain subChainOperation = new Substract();
            AbstractChain divChainOperation = new Divide();
            AbstractChain mulChainOperation = new Multiply();

            addChainOperation.SetNext(subChainOperation).SetNext(divChainOperation).SetNext(mulChainOperation);

            var number1 = new Number(5);
            var number2 = new Number(7);
            string operation = "+";
            var request = new Request(number1, number2, operation);
            var result = addChainOperation.Handle(request);

            if (result is string) {
                Console.WriteLine(result);
            } else {
                Console.WriteLine($"There is no such operator: \"{operation}\"");
            }

        }

    }

}
