package example;


import net.rwhps.server.data.event.GameOverData;
import net.rwhps.server.data.player.AbstractPlayer;
import net.rwhps.server.game.GameUnitType;
import net.rwhps.server.plugin.event.AbstractEvent;
import net.rwhps.server.util.Time;
import net.rwhps.server.util.log.exp.ImplementedException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * 这个是新的Event的实现
 * 如果要使用.我推荐使用新的实现
 * 因为新的实现有同步和异步
 * 旧的事件是同步的 如果进行高IO 那么游戏体验或许极差
 * @author Dr
 */
public class Event implements AbstractEvent {
    @Override
    public void registerPlayerJoinEvent(AbstractPlayer player) {
        try {
            player.sendSystemMessage("你好!! 这是RW-HPS新的Event的实现");
            player.sendSystemMessage("Plugin测试 这是进入的时间 "+ Time.getUtcMilliFormat(1));
        } catch (ImplementedException.PlayerImplementedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void registerGameOverEvent(@Nullable GameOverData gameOverData) {

    }

    @Override
    public void registerGameStartEvent() {

    }

    @Override
    public void registerPlayerBanEvent(@NotNull AbstractPlayer abstractPlayer) {

    }

    @Override
    public void registerPlayerChatEvent(@NotNull AbstractPlayer abstractPlayer, @NotNull String s) {

    }

    @Override
    public void registerPlayerIpBanEvent(@NotNull AbstractPlayer abstractPlayer) {

    }

    @Override
    public void registerPlayerIpUnbanEvent(@NotNull String s) {

    }

    @Override
    public void registerPlayerLeaveEvent(@NotNull AbstractPlayer abstractPlayer) {

    }

    @Override
    public boolean registerPlayerOperationUnitEvent(@NotNull AbstractPlayer abstractPlayer, @NotNull GameUnitType.GameActions gameActions, @NotNull GameUnitType.GameUnits gameUnits, float v, float v1) {
        return false;
    }

    @Override
    public void registerPlayerUnbanEvent(@NotNull AbstractPlayer abstractPlayer) {

    }

    @Override
    public void registerServerHessStartPort() {

    }
}
