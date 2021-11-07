using CarShop.Builders;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace CarShop {

    public class Director {

        public Director(IVehicleBuilder builder) {
            _builder = builder;
        }

        private IVehicleBuilder _builder;

        public IVehicleBuilder Builder {
            set { _builder = value; }
        }

        public void BuildCar() {
            _builder.BuildFrame();
            _builder.BuildEngine();
            (_builder as CarBuilder).BuildSeats();
            (_builder as CarBuilder).BuildDoors();
            _builder.BuildWheels();
        }

        public void BuildMotorcycle() {
            _builder.BuildFrame();
            _builder.BuildEngine();
            (_builder as MotorcycleBuilder).BuildSeats();
            _builder.BuildWheels();
        }

        public void BuildScooter() {
            _builder.BuildFrame();
            _builder.BuildEngine();
            _builder.BuildWheels();
        }

    }

}
