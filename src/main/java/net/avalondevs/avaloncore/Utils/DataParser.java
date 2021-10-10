package net.avalondevs.avaloncore.Utils;

import lombok.experimental.UtilityClass;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class DataParser {

    public static Object tryToParse(String string, Class<?> classType){

        System.out.println(classType.getSimpleName());

        switch (classType.getSimpleName()) {

            case "String": return string;
            case "int": return Integer.parseInt(string);
            case "double": return Double.parseDouble(string);
            case "long": return Long.parseLong(string);

        }

        return null;

    }

    public static boolean isType(String data, Class<?> type) {

        return tryToParse(data, type) != null;

    }

    public static boolean isEitherType(String data, Class<?> ... types) {

        for (Class<?> type : types) {
            if(isType(data, type))
                return true;
        }

        return false;

    }

    /**
     * Converts the short form of an item:
     * {@code MATERIAL:DATA:AMOUNT} (where {@code :AMOUNT} is optional)
     * into an {@link ItemStack}
     * @param string the short form
     * @return the resulting item stack, null if the material is invalid
     */
    public static ItemStack readItem(String string) {

        String[] split = string.split(":");

        Material material = StrUtil.nameToEnum(split[0], Material.class);

        if(material == null)
            return null;

        int amount = Integer.parseInt(ArrayUtil.getOrDefault(split, 1, "1"));

        return new ItemStack(material, amount);
    }

}
