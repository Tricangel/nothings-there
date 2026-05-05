package bee.insanity.mixin;

import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GlyphSource;
import net.minecraft.client.gui.font.glyphs.BakedGlyph;
import net.minecraft.network.chat.Style;
import net.minecraft.util.RandomSource;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Font.class)
public class FontMixin {
    @Shadow
    @Final
    private RandomSource random;

    @Inject(at = @At("HEAD"), method = "getGlyph", cancellable = true)
    private void init(int codepoint, Style style, CallbackInfoReturnable<BakedGlyph> cir) {
        GlyphSource glyphSource = ((Font) (Object) this).getGlyphSource(style.getFont());
        BakedGlyph glyph;
        if ( style.isObfuscated() && codepoint != 32 && style.isBold()) {
            int number = random.nextInt(48, 57);
            // 48 = 0 49 = 1 50 = 2 51 = 3 52 = 4 53 = 5 54 = 6 55 = 7 56 = 8 57 = 9
            glyph = glyphSource.getGlyph(number);
            cir.setReturnValue(glyph);

        }

    }
}