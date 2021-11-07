using CarShop.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace CarShop.Builders {

    public class ScooterBuilder : IVehicleBuilder {

        public ScooterBuilder() {
            Reset();
        }

        private Vehicle _vehicle;

        public void BuildEngine() {
            _vehicle.AddPart(new VehiclePart("Engine", "Electric"));
        }

        public void BuildFrame() {
            _vehicle.AddPart(new VehiclePart("Frame", "Scooter-frame"));
        }

        public void BuildWheels() {
            _vehicle.AddPart(new VehiclePart("Wheels", "2"));
        }

        private void Reset() {
            _vehicle = new Vehicle("Scooter");
        }

        public Vehicle GetVehicle() {
            Vehicle vehicle = _vehicle;
            Reset();
            return vehicle;
        }

    }

}
