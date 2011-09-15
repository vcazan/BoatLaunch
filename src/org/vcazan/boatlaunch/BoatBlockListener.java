package org.vcazan.boatlaunch;

import java.util.logging.Logger;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;

import org.bukkit.craftbukkit.entity.CraftBoat;

import org.bukkit.event.block.BlockDispenseEvent;
import org.bukkit.event.block.BlockListener;
import org.bukkit.inventory.ItemStack;

public class BoatBlockListener extends BlockListener{

	public final BoatLaunch plugin;
	
	public BoatBlockListener(BoatLaunch instance) {
		this.plugin = instance;
	}
	Logger log = Logger.getLogger("Minecraft");
	Location spawnBoat;
 

	public void onBlockDispense(BlockDispenseEvent event){
		ItemStack dispenseItem = event.getItem();
		
		if (dispenseItem.getTypeId() == 333){

			event.setCancelled(true);
			Block block = event.getBlock();
			Location under = block.getLocation();
			under.setY(block.getY()-1);
			
			if (checkForWater(block.getLocation()) == true || checkForWater(under) == true){
				for(World world : this.plugin.getServer().getWorlds()) {
					if(world.getBlockAt(block.getLocation()) == block){							world.spawn(spawnBoat, CraftBoat.class); break;
					}
				}
			}				
		
		}
	}

	public boolean checkForWater(Location loc){
		Block block = loc.getBlock();
		log.info(block.getType().toString());

		for(BlockFace face : BlockFace.values()) {
			if (block.getRelative(face).getTypeId() == 8 || block.getRelative(face).getTypeId() == 9) {
				loc.setY(block.getY() + face.getModY() );
				loc.setZ(block.getZ() + face.getModZ() );
				loc.setX(block.getX() + face.getModX() );
				spawnBoat = loc;
				return true;
			}
			
		}
		return false;
	}
}