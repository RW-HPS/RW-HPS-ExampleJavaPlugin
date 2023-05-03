package example;

import net.rwhps.server.data.HessModuleManage;
import net.rwhps.server.data.global.Data;
import net.rwhps.server.data.player.AbstractPlayer;
import net.rwhps.server.data.player.Player;
import net.rwhps.server.data.plugin.PluginData;
import net.rwhps.server.func.StrCons;
import net.rwhps.server.plugin.Plugin;
import net.rwhps.server.plugin.event.AbstractEvent;
import net.rwhps.server.util.Time;
import net.rwhps.server.util.game.CommandHandler;
import net.rwhps.server.util.log.Log;
import net.rwhps.server.util.log.exp.ImplementedException;

import java.util.Arrays;

/**
 * @author Dr
 */
public class Main extends Plugin {
	PluginData pluginData = new PluginData();
	/**
	 * 这里主要做初始化
	 */
	@Override
	public void init(){
		// 设置Bin文件位置
		pluginData.setFileUtil(this.pluginDataFileUtil.toFile("ExampleData.bin"));
		pluginData.read();

		/* 过滤消息 */
		Data.core.admin.addChatFilter((player, text) -> {
			if (text == null) {
				return null;
			}
			return text.replace("heck", "h*ck");
		});

		//读取数据
		long lastStartTime = this.pluginData.getData("lastStartTime",Time.concurrentMillis());
		String lastStartTimeString = this.pluginData.getData("lastStartTimeString",Time.getUtcMilliFormat(1));
		Log.info("lastStartTime",lastStartTime);
		Log.info("lastStartTimeString",lastStartTimeString);
		this.pluginData.getData("lastStartTime",Time.concurrentMillis());
		this.pluginData.getData("lastStartTimeString", Time.getUtcMilliFormat(1));
	}

	@Override
	public AbstractEvent registerEvents(){
		return new Event();
	}

	/**
	 * 注册服务器命令
	 */
	@Override
	public void registerServerCommands(CommandHandler handler){
		handler.<StrCons>register("hi", "#这是Server命令简介", (arg, log) ->
			log.get("hi")
		);

		handler.<StrCons>register("arg","<这是必填> [这是选填]", "#这是Server命令简介", (arg, log) ->
			log.get(Arrays.toString(arg))
		);

		handler.<StrCons>register("args","<这是必填...>", "#这是Server命令简介", (arg, log) ->
			log.get(arg[0])
		);
	}

	/**
	 * 注册客户端命令
	 * @param handler 注册
	 */
	@Override
	public void registerServerClientCommands(CommandHandler handler){
		//向自己回复消息
		handler.<Player>register("reply", "<text...>", "#只取第一个回复.", (args, player) -> {
			try {
				player.sendSystemMessage("你发的是: " + args[0]);
			} catch (ImplementedException.PlayerImplementedException e) {
				throw new RuntimeException(e);
			}
		});

		//向玩家发送
		handler.<Player>register("whisper", "<player> <text...>", "#向另一个玩家发消息.", (args, player) -> {
			//查找玩家
			AbstractPlayer other = HessModuleManage.INSTANCE.getHps().getRoom().getPlayerManage().playerGroup.find(p -> p.getName().equalsIgnoreCase(args[0]));

			if(other == null){
				try {
					player.sendSystemMessage("找不到这个玩家!");
				} catch (ImplementedException.PlayerImplementedException e) {
				}
				return;
			}

			//向玩家发消息
			try {
				other.sendSystemMessage("玩家: " + player.getName() + " 向你发送: " + args[1]);
			} catch (ImplementedException.PlayerImplementedException e) {
				throw new RuntimeException(e);
			}
		});
	}

	@Override
	public void onDisable() {
		super.onDisable();
		//Custom
		pluginData.save();
	}
}
