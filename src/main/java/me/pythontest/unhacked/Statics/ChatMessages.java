package me.pythontest.unhacked.Statics;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.HoverEvent;
import net.kyori.adventure.text.format.NamedTextColor;

public class ChatMessages {
    public static Component testFailed(String username, String testID,Component moreInfo, Integer testFailed, Integer testToFailed){
        return Component.text("[Unhacked] "+username+" Failed "+testID+"(hover for more info) "+String.format("[%s/%s]",testFailed,testToFailed), NamedTextColor.RED).hoverEvent(HoverEvent.showText(moreInfo));
    }
    public static Component testFailedConsle(String username, String testID,Component moreInfo, Integer testFailed, Integer testToFailed){
        return Component.text("[Unhacked] "+username+" Failed "+testID+"(hover for more info) "+String.format("[%s/%s]\n",testFailed,testToFailed), NamedTextColor.RED).append(moreInfo);
    }
}
