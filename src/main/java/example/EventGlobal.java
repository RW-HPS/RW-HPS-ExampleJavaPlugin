package example;

import net.rwhps.server.net.core.ConnectionAgreement;
import net.rwhps.server.net.core.IRwHps;
import net.rwhps.server.plugin.event.AbstractGlobalEvent;
import net.rwhps.server.util.log.Log;
import org.jetbrains.annotations.NotNull;

/**
 * @author Dr
 */
public class EventGlobal implements AbstractGlobalEvent {
    @Override
    public void registerNewCloseEvent(@NotNull ConnectionAgreement connectionAgreement) {

    }

    @Override
    public boolean registerNewConnectEvent(@NotNull ConnectionAgreement connectionAgreement) {
        Log.clog("新的链接! IP: {0} , 协议: {1}",connectionAgreement.getIp(),connectionAgreement.getUseAgreement());
        return false;
    }

    @Override
    public void registerServerLoadEvent() {
        Log.clog("Example Plugin加载完了");
    }

    @Override
    public void registerGameLibLoadEvent(@NotNull String s) {
    }

    @Override
    public void registerServerStartTypeEvent(@NotNull IRwHps.NetType netType) {
    }
}
