using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using PastorsDesk.Shared.Network;
using PastorsDesk.Shared.Util;

namespace PastorsDesk.Network
{
    public class PacketHandleManager
    {
        public delegate void PacketHandlerFunc(PdClient client, Packet packet);

        private Dictionary<int, PacketHandlerFunc> _handlers;

        public PacketHandleManager()
        {
            _handlers = new Dictionary<int, PacketHandlerFunc>();
        }

        public void Add(int op, PacketHandlerFunc handler)
        {
            if (_handlers.ContainsKey(op))
                Log.Warning("PacketHandlerManager: Overwriting handler for '{0:X4}' with '{1}'.", op, handler.Method.DeclaringType + "." + handler.Method.Name);

            _handlers[op] = handler;
        }

        public void AutoLoad()
        {
            foreach (var method in this.GetType().GetMethods())
            {
                foreach (PacketHandlerAttribute attr in method.GetCustomAttributes(typeof(PacketHandlerAttribute), false))
                {
                    var del = (PacketHandlerFunc)Delegate.CreateDelegate(typeof(PacketHandlerFunc), this, method);
                    foreach (var op in attr.Ops)
                        this.Add(op, del);
                }
            }
        }

        public void Handle(PdClient client, Packet packet)
        {
            PacketHandlerFunc handler;
            if (!_handlers.TryGetValue(packet.Op, out handler))
            {
                this.UnknownPacket(client, packet);
                return;
            }

            handler(client, packet);
        }

        public void UnknownPacket(PdClient client, Packet packet)
        {
            Log.Unimplemented("PacketHandlerManager: Handler for '{0:X4}'", packet.Op);
            Log.Debug(packet);
        }
    }

    public class PacketHandlerAttribute : Attribute
    {
        public int[] Ops { get; protected set; }

        public PacketHandlerAttribute(params int[] ops)
        {
            this.Ops = ops;
        }
    }
}
