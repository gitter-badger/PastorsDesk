//
// Translated by CS2J (http://www.cs2j.com): 3/22/2015 4:20:15 AM
//

package Aura.Shared.Network;

import Aura.Shared.Network.BaseClient;
import Aura.Shared.Network.Packet;
import Aura.Shared.Network.PacketHandlerAttribute;
import Aura.Shared.Util.Log;
import CS2JNet.JavaSupport.language.RefSupport;
import CS2JNet.JavaSupport.util.ListSupport;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

// Copyright (c) Aura development team - Licensed under GNU GPL
// For more information, see license file in the main folder
/**
* Packet handler manager base class.
*/
public abstract class PacketHandlerManager <TClient extends BaseClient>  
{
    public static class __MultiPacketHandlerFunc   implements PacketHandlerFunc
    {
        public void invoke(TClient client, Packet packet) throws Exception {
            List<PacketHandlerFunc> copy, members = this.getInvocationList();
            synchronized (members)
            {
                copy = new LinkedList<PacketHandlerFunc>(members);
            }
            for (PacketHandlerFunc d : copy)
            {
                d.invoke(client, packet);
            }
        }

        private List<PacketHandlerFunc> _invocationList = new ArrayList<PacketHandlerFunc>();
        public static PacketHandlerFunc combine(PacketHandlerFunc a, PacketHandlerFunc b) throws Exception {
            if (a == null)
                return b;
             
            if (b == null)
                return a;
             
            __MultiPacketHandlerFunc ret = new __MultiPacketHandlerFunc();
            ret._invocationList = a.getInvocationList();
            ret._invocationList.addAll(b.getInvocationList());
            return ret;
        }

        /**
        * Adds and/or overwrites handler.
        * 
        *  @param op 
        *  @param handler
        */
        public static PacketHandlerFunc remove(PacketHandlerFunc a, PacketHandlerFunc b) throws Exception {
            if (a == null || b == null)
                return a;
             
            List<PacketHandlerFunc> aInvList = a.getInvocationList();
            /**
            * Adds all methods with a Handler attribute.
            */
            List<PacketHandlerFunc> newInvList = ListSupport.removeFinalStretch(aInvList, b.getInvocationList());
            if (aInvList == newInvList)
            {
                return a;
            }
            else
            {
                __MultiPacketHandlerFunc ret = new __MultiPacketHandlerFunc();
                ret._invocationList = newInvList;
                return ret;
            } 
        }

        public List<PacketHandlerFunc> getInvocationList() throws Exception {
            return _invocationList;
        }
    
    }

    public static interface PacketHandlerFunc   
    {
        void invoke(TClient client, Packet packet) throws Exception ;

        List<PacketHandlerFunc> getInvocationList() throws Exception ;
    
    }

    private HashMap<Integer,PacketHandlerFunc> _handlers;
    protected PacketHandlerManager() throws Exception {
        _handlers = new HashMap<Integer,PacketHandlerFunc>();
    }

    public void add(int op, PacketHandlerFunc handler) throws Exception {
        if (_handlers.containsKey(op))
            Log.warning("PacketHandlerManager: Overwriting handler for '{0:X4}' with '{1}'.",op,handler.Method.DeclaringType + "." + handler.Method.Name);
         
        _handlers.put(op, handler);
    }

    public void autoLoad() throws Exception {
        for (/* [UNSUPPORTED] 'var' as type is unsupported "var" */ method : this.getClass().GetMethods())
        {
            for (Object __dummyForeachVar2 : method.GetCustomAttributes(PacketHandlerAttribute.class, false))
            {
                PacketHandlerAttribute attr = (PacketHandlerAttribute)__dummyForeachVar2;
                PacketHandlerFunc del = (PacketHandlerFunc)Delegate.CreateDelegate(PacketHandlerFunc.class, this, method);
                for (Integer op : attr.getOps())
                    this.add(op,del);
            }
        }
    }

    /**
    * Runs handler for packet's op, or logs it as unimplemented.
    * 
    *  @param client 
    *  @param packet
    */
    public void handle(TClient client, Packet packet) throws Exception {
        // Don't log internal packets
        //if (packet.Op < Op.Internal.ServerIdentify)
        //    Log.Debug("R: " + packet);
        PacketHandlerFunc handler;
        RefSupport<PacketHandlerFunc> refVar___0 = new RefSupport<PacketHandlerFunc>();
        boolean boolVar___0 = !_handlers.TryGetValue(packet.getOp(), refVar___0);
        handler = refVar___0.getValue();
        if (boolVar___0)
        {
            this.unknownPacket(client,packet);
            return ;
        }
         
        handler.invoke(client,packet);
    }

    public void unknownPacket(TClient client, Packet packet) throws Exception {
        Log.unimplemented("PacketHandlerManager: Handler for '{0:X4}'",packet.getOp());
        Log.debug(packet);
    }

}


