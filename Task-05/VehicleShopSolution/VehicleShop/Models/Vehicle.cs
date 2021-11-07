using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace CarShop.Models {

    public class Vehicle {

        public Vehicle(string vehicleType) {
            _vehicleType = vehicleType;
            _parts = new List<VehiclePart>();
        }

        private string _vehicleType;

        public string VehicleType {
            get { return _vehicleType; }
        }

        private List<VehiclePart> _parts;

        public void AddPart(VehiclePart part) {
            _parts.Add(part);
        }

        public void DisplayDetails() {
            Console.WriteLine($"Vehicle: {_vehicleType}\nParts: ");
            foreach (var part in _parts) {
                Console.WriteLine($"{part.Name, 10}: {part.Description}");
            }
        }

    }

    public class VehiclePart {

        public VehiclePart(string name, string desc) {
            _name = name;
            _desc = desc;
        }

        private string _name;

        public string Name {
            get { return _name; }
        }

        private string _desc;

        public string Description {
            get { return _desc; }
        }

    }

}
