package bee.insanity.item;

import bee.insanity.NothingsThere;
import net.minecraft.item.equipment.ArmorMaterial;
import net.minecraft.item.equipment.EquipmentAsset;
import net.minecraft.item.equipment.EquipmentAssetKeys;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;

public class DemoniteArmorMaterial {
    //ArmorMaterial NETHERITE = new ArmorMaterial(37, createDefenseMap(3, 6, 8, 3, 19), 15, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 3.0F, 0.1F, ItemTags.REPAIRS_NETHERITE_ARMOR, EquipmentAssetKeys.NETHERITE);

    public static final int BASE_DURABILITY = 37;

    public static final ResourceKey<EquipmentAsset> GUIDITE_ARMOR_MATERIAL_KEY = ResourceKey.create(EquipmentAssets.ROOT_ID, Identifier.of(NothingsThere.MOD_ID, "guidite"));

}
