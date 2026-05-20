package bee.insanity.item;

import bee.insanity.NothingsThere;
import bee.insanity.registry.ModTags;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;
import net.minecraft.world.item.equipment.EquipmentAsset;
import net.minecraft.world.item.equipment.EquipmentAssets;

import java.util.Map;

public class ModArmorMaterials {

    public static final int DEMONITE_BASE_DURABILITY = 37;
    public static final ResourceKey<EquipmentAsset> DEMONITE_ARMOR_MATERIAL_KEY = ResourceKey.create(EquipmentAssets.ROOT_ID, Identifier.fromNamespaceAndPath(NothingsThere.MOD_ID, "demonite"));

    public static final ArmorMaterial DEMONITE_INSTANCE = new ArmorMaterial(
            DEMONITE_BASE_DURABILITY,
            Map.of(
                    ArmorType.HELMET, 3,
                    ArmorType.CHESTPLATE, 8,
                    ArmorType.LEGGINGS, 6,
                    ArmorType.BOOTS, 3
            ),
            9,
            SoundEvents.ARMOR_EQUIP_NETHERITE,
            3.0F,
            0.1F,
            ModTags.REPAIRS_DEMONITE_ARMOR,
            DEMONITE_ARMOR_MATERIAL_KEY
    );

}
