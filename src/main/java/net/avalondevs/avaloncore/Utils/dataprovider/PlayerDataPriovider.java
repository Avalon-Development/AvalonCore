package net.avalondevs.avaloncore.Utils.dataprovider;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

/**
 * Implementation of {@link DataProvider} for caching of player data classes, indexed with @{@link UUID} and gettable via {@link Player}
 * @param <T> dataclass
 */
public abstract class PlayerDataPriovider<T> extends DataProvider<UUID, T> {

   @Override
   public T get(UUID key) {

       T data = cache.get(key);

       if(data == null) {

           Player player = Bukkit.getPlayer(key);

           if(player == null) {

               data = databaseGet(key);
               if(data != null)
               cache.put(key, data);

           }else {

               data = create(player);
               cache.put(player.getUniqueId(), data);

           }

       }

       return data;

   }

   public T get(Player player) {

       if(!has(player.getUniqueId())) {

           T data = create(player);
           cache.put(player.getUniqueId(), data);

           return data;

       }else
           return super.get(player.getUniqueId());

   }

   public abstract T create(Player player);

}
