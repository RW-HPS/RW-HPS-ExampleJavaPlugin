package example;

import net.rwhps.server.game.event.core.EventListenerHost;
import net.rwhps.server.game.event.global.NetConnectNewEvent;
import net.rwhps.server.util.annotations.core.EventListenerHandler;
import net.rwhps.server.util.log.Log;
import org.jetbrains.annotations.NotNull;

/**
 * @author Dr
 */
public class EventGlobal implements EventListenerHost {
    @EventListenerHandler
    public boolean fuckPlugin(NetConnectNewEvent netConnectNewEvent) {
        Log.clog(
                "新的链接! IP: {0} , 协议: {1}",
                netConnectNewEvent.getConnectionAgreement().getIp(),
                netConnectNewEvent.getConnectionAgreement().getUseAgreement()
        );
        return false;
    }

    @Override
    public void handleException(@NotNull Throwable throwable) {
        // 捕获错误
    }
}
