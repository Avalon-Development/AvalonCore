package net.avalondevs.avaloncore.Commands.Kits;

import java.util.ArrayList;
import java.util.UUID;

public class Config {
    public static String KitSpecialKitName = "&fSpecial";
    public static String KitHeroKitName = "&2Hero";
    public static String KitLegendKitName = "&6Legend";
    public static String KitImmortalKitName = "&dImmortal";
    public static String KitEternalKitName = "&cEternal";
    public static String KitZeusKitName = "&e&lZeuz";
    public static ArrayList<UUID> KitSpecialCooldownTime = new ArrayList<UUID>();
    public static ArrayList<UUID> KitHeroCooldownTime = new ArrayList<UUID>();
    public static ArrayList<UUID> KitLegendCooldownTime = new ArrayList<UUID>();
    public static ArrayList<UUID> KitImmortalCooldownTime = new ArrayList<UUID>();
    public static ArrayList<UUID> KitEternalCooldownTime = new ArrayList<UUID>();
    public static ArrayList<UUID> KitZeusCooldownTime = new ArrayList<UUID>();
    public static String HeroRankPermission = "rank.hero";
    public static String LegendRankPermission = "rank.legend";
    public static String ImmortalRankPermission = "rank.immortal";
    public static String EternalRankPermission = "rank.eternal";
    public static String ZeusRankPermission = "rank.zeus";
    static int KitSpecialCooldownTimeSccondsInt = 10;
    static int KitHeroCooldownTimeSccondsInt = 10;
    static int KitLegendCooldownTimeSccondsInt = 10;
    static int KitImmortalCooldownTimeSccondsInt = 10;
    static int KitEternalCooldownTimeSccondsInt = 10;
    static int KitZeusCooldownTimeSccondsInt = 10;
}
