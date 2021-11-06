using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace Paint {

    public class Caretaker {

        public Caretaker(FormWindow originator) {
            _originator = originator;
            _originator.Controls["RestoreButton"].Click += RestoreButtonClick;
            _originator.Controls["pictureBox"].MouseUp += SaveStateMouseUp;
            _originator.Load += SaveInitState;
            _mementos = new Stack<IMemento>();
        }

        private IMemento _initMemento;
        private IMemento _recentMemento;
        private Stack<IMemento> _mementos;
        private FormWindow _originator;

        private void SaveStateMouseUp(object sender, MouseEventArgs e) {
            _mementos.Push(_recentMemento);
            _recentMemento = _originator.SaveState();
        }

        private void RestoreButtonClick(object sender, EventArgs e) {
            if (_mementos.Count > 0) {
                _recentMemento = _mementos.Pop();
            }
            _originator.RestoreState(_recentMemento as ConcreteMemento);
        }

        private void SaveInitState(object sender, EventArgs e) {
            _initMemento = _originator.SaveState();
            _recentMemento = _initMemento;
        }

    }

}
