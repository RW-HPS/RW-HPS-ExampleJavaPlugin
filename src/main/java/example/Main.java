package example;

import cn.rwhps.server.data.global.Data;
import cn.rwhps.server.data.player.Player;
import cn.rwhps.server.data.plugin.PluginData;
import cn.rwhps.server.func.StrCons;
import cn.rwhps.server.plugin.Plugin;
import cn.rwhps.server.plugin.event.AbstractEvent;
import cn.rwhps.server.util.Time;
import cn.rwhps.server.util.game.CommandHandler;
import cn.rwhps.server.util.log.Log;

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
		this.pluginData.setData("lastStartTime",Time.concurrentMillis());
		this.pluginData.setData("lastStartTimeString",Time.getUtcMilliFormat(1));
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
		handler.<Player>register("reply", "<text...>", "#只取第一个回复.", (args, player) ->
			player.sendSystemMessage("你发的是: " + args[0])
		);

		//向玩家发送
		handler.<Player>register("whisper", "<player> <text...>", "#向另一个玩家发消息.", (args, player) -> {
			//查找玩家
			Player other = Data.game.getPlayerManage().playerGroup.find(p -> p.name.equalsIgnoreCase(args[0]));

			if(other == null){
				player.sendSystemMessage("找不到这个玩家!");
				return;
			}

			//向玩家发消息
			other.sendSystemMessage("玩家: " + player.name + " 向你发送: " + args[1]);
		});
	}

	@Override
	public void onDisable() {
		super.onDisable();
		//Custom
		pluginData.save();
	}
}
