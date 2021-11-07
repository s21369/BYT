using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace CarShop.Builders {

    public interface IVehicleBuilder {

        public void BuildFrame();
        public void BuildEngine();
        public void BuildWheels();

    }

}
