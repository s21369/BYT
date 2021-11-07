using CarShop.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace CarShop.Builders {

    public class CarBuilder : IVehicleBuilder {

        public CarBuilder() {
            Reset();
        }

        private Vehicle _vehicle;

        public void BuildDoors() {
            _vehicle.AddPart(new VehiclePart("Doors", "4"));
        }

        public void BuildSeats() {
            _vehicle.AddPart(new VehiclePart("Seats", "5"));
        }

        public void BuildEngine() {
            _vehicle.AddPart(new VehiclePart("Engine", "2.5L"));
        }

        public void BuildFrame() {
            _vehicle.AddPart(new VehiclePart("Frame", "Car-frame"));
        }

        public void BuildWheels() {
            _vehicle.AddPart(new VehiclePart("Wheels", "4"));
        }

        private void Reset() {
            _vehicle = new Vehicle("Car");
        }

        public Vehicle GetVehicle() {
            Vehicle vehicle = _vehicle;
            Reset();
            return vehicle;
        }

    }

}
