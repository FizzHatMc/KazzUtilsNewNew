package org.kazz.kazzutils.hidden.hent;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.util.ResourceLocation;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class PictureRenderer {
    private ResourceLocation pictureResource;


    public PictureRenderer() {

            // Load the image file
            BufferedImage image = null; //ImageIO.read(new File(picturePath));
            do {
                int random = (int) (Math.random() * 100000);
                try {
                    //url = new URL("https://rule34.xxx/index.php?page=post&s=view&id="+random);
                    URL url = new URL("https://imgur.com/gallery/RqdHNEZ");
                    image = ImageIO.read(url);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } while (image == null);
            // Create a dynamic texture and resource location for the image
            DynamicTexture dynamicTexture = new DynamicTexture(image);
            pictureResource = Minecraft.getMinecraft().getTextureManager().getDynamicTextureLocation("hentai", dynamicTexture);

    }

    public ResourceLocation getPictureResource() {
        return pictureResource;
    }
}
