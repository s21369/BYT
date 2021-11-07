using CarShop.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace CarShop.Builders {

    public class MotorcycleBuilder : IVehicleBuilder {

        public MotorcycleBuilder() {
            Reset();
        }

        private Vehicle _vehicle;

        public void BuildSeats() {
            _vehicle.AddPart(new VehiclePart("Seats", "2"));
        }

        public void BuildEngine() {
            _vehicle.AddPart(new VehiclePart("Engine", "1.0L"));
        }

        public void BuildFrame() {
            _vehicle.AddPart(new VehiclePart("Frame", "Motorcycle-frame"));
        }

        public void BuildWheels() {
            _vehicle.AddPart(new VehiclePart("Wheels", "2"));
        }

        private void Reset() {
            _vehicle = new Vehicle("Motorcycle");
        }

        public Vehicle GetVehicle() {
            Vehicle vehicle = _vehicle;
            Reset();
            return vehicle;
        }

    }

}
