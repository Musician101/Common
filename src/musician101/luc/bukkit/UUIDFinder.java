package musician101.luc.bukkit;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class UUIDFinder
{
	File file;
	JavaPlugin plugin;
	Map<String, String> uuids = new HashMap<String, String>();
	
	public UUIDFinder(JavaPlugin plugin) throws FileNotFoundException, IOException, InvalidConfigurationException
	{
		this.plugin = plugin;
		file = new File(plugin.getDataFolder(), "players.yml");
		loadUUIDs();
	}
	
	private void loadUUIDs() throws FileNotFoundException, IOException, InvalidConfigurationException
	{
		if (!file.exists())
		{
			file.createNewFile();
			BufferedWriter bw = new BufferedWriter(new FileWriter(file.getAbsoluteFile()));
			bw.write("# This file keeps track of players' last known username.");
			bw.newLine();
			bw.write("# Do not edit this file unless it is absolutely neccessary.");
			bw.close();
		}
		
		YamlConfiguration players = new YamlConfiguration();
		players.load(file);
		for (Entry<String, Object> entry : players.getValues(true).entrySet())
			uuids.put(entry.getKey(), entry.getValue().toString());
	}
	
	public void saveUUIDs() throws FileNotFoundException, IOException, InvalidConfigurationException
	{
		YamlConfiguration players = new YamlConfiguration();
		players.load(file);
		for (Entry<String, String> entry : uuids.entrySet())
			players.set(entry.getKey(), entry.getValue());
		
		players.save(file);
	}
	
	public void reloadUUIDs() throws FileNotFoundException, IOException, InvalidConfigurationException
	{
		saveUUIDs();
		loadUUIDs();
	}
	
	public Player getPlayer(String player)
	{
		if (!uuids.containsValue(player))
			return null;
		
		for (Entry<String, String> entry : uuids.entrySet())
			if (entry.getValue().equals(player))
				return Bukkit.getPlayer(UUID.fromString(entry.getKey()));
		
		return null;
	}
	
	public OfflinePlayer getOfflinePlayer(String player)
	{
		if (!uuids.containsValue(player))
			return null;
		
		for (Entry<String, String> entry : uuids.entrySet())
			if (entry.getValue().equals(player))
				return Bukkit.getOfflinePlayer(UUID.fromString(entry.getKey()));
		
		return getPlayer(player);
	}
	
	public void addPlayer(Player player)
	{
		uuids.put(player.getUniqueId().toString(), player.getName());
	}
	
	public void removePlayer(Player player)
	{
		uuids.remove(player.getUniqueId().toString());
	}
}
