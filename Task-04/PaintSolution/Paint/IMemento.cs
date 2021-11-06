using System;
using System.Collections.Generic;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Paint {

    public interface IMemento {

        public DateTime GetDate();

    }

    public class ConcreteMemento : IMemento {

        public ConcreteMemento(Bitmap state) {
            _state = state;
            _date = DateTime.Now;
        }

        private Bitmap _state;
        private DateTime _date;

        public DateTime GetDate() {
            return _date;
        }

        public Bitmap GetState() {
            return _state;
        }

    }

}
