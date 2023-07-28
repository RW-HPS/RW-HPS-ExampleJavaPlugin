package example;


import net.rwhps.server.game.event.core.EventListenerHost;
import net.rwhps.server.game.event.game.PlayerJoinEvent;
import net.rwhps.server.util.Time;
import net.rwhps.server.util.annotations.core.EventListenerHandler;
import net.rwhps.server.util.log.Log;
import net.rwhps.server.util.log.exp.ImplementedException;
import org.jetbrains.annotations.NotNull;

/**
 * 这个是Event的实现
 * 部分事件是同步的 如果进行高IO 那么游戏体验或许极差
 * 如果事件没有 @Async 那么是同步
 *
 * @author Dr
 */
public class Event implements EventListenerHost {
    @EventListenerHandler
    public void registerPlayerJoinEvent(PlayerJoinEvent player) {
        try {
            player.getPlayer().sendSystemMessage("你好!! 这是RW-HPS新的Event的实现");
            player.getPlayer().sendSystemMessage("Plugin测试 这是进入的时间 "+ Time.getUtcMilliFormat(1));
        } catch (ImplementedException.PlayerImplementedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void handleException(@NotNull Throwable throwable) {
        Log.error(throwable);
    }
}
