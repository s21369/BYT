using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace Paint {

    public partial class FormWindow : Form {

        public FormWindow() {
            InitializeComponent();
            _image = new Bitmap(Size.Width, Size.Height);
            _g = Graphics.FromImage(_image);
            _g.Clear(Color.WhiteSmoke);
            pictureBox.Image = _image;
            _pen = new Pen(Color.Black, 3);
        }

        private Bitmap _image;
        private Graphics _g;
        private bool _startPaint = false;
        private int _initX;
        private int _initY;
        private Pen _pen;

        private void PaintMouseDown(object sender, MouseEventArgs e) {
            _startPaint = true;
            _initX = e.X;
            _initY = e.Y;
        }

        private void PaintMouseMove(object sender, MouseEventArgs e) {
            if (_startPaint) {
                _g.DrawLine(_pen, new Point(_initX, _initY), new Point(e.X, e.Y));
                _initX = e.X;
                _initY = e.Y;
            }
            pictureBox.Refresh();
        }

        private void PaintMouseUp(object sender, MouseEventArgs e) {
            _startPaint = false;
        }

        public IMemento SaveState() {
            return new ConcreteMemento(new Bitmap(_image));
        }

        public void RestoreState(ConcreteMemento memento) {
            _image = memento.GetState();
            _g = Graphics.FromImage(_image);
            pictureBox.Image = _image;
            pictureBox.Refresh();
        }

    }

}
