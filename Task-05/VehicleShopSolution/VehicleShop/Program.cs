using CarShop.Builders;
using System;

namespace CarShop {

    public class Program {
        
        public static void Main(string[] args) {

            var carBuilder = new CarBuilder();
            var director = new Director(carBuilder);
            director.BuildCar();
            carBuilder.GetVehicle().DisplayDetails();
            Console.WriteLine();

            var motorcycleBuilder = new MotorcycleBuilder();
            director.Builder = motorcycleBuilder;
            director.BuildMotorcycle();
            motorcycleBuilder.GetVehicle().DisplayDetails();
            Console.WriteLine();

            var scooterBuilder = new ScooterBuilder();
            director.Builder = scooterBuilder;
            director.BuildScooter();
            scooterBuilder.GetVehicle().DisplayDetails();
            Console.WriteLine();

        }

    }

}
