using Calculator.DTOs;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Calculator.Handlers {

    public interface IChain {

        public IChain SetNext(IChain chain);

        public object Handle(Request request);

    }

    public abstract class AbstractChain : IChain {

        private IChain _nextChain;

        public IChain SetNext(IChain chain) {
            _nextChain = chain;
            return chain;
        }

        public virtual object Handle(Request request) {
            if (_nextChain != null) {
                return _nextChain.Handle(request);
            }
            return null;
        }

    }

}
