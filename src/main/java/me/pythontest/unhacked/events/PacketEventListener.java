package me.pythontest.unhacked.events;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.events.PacketListener;
import me.pythontest.unhacked.unhacked.Unhacked;

public class PacketEventListener {
    ProtocolManager protocolManager = ProtocolLibrary.getProtocolManager();
    public PacketEventListener(){
        this.registerEvent();
    }
    private void registerEvent(){
        protocolManager.addPacketListener(buildPacket);
    }
    PacketListener buildPacket = new PacketAdapter(Unhacked.getInstance(), PacketType.Play.Client.BLOCK_PLACE) {
        @Override
        public void onPacketReceiving(PacketEvent event) {
            super.onPacketReceiving(event);
        }
    };
}
