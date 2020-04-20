package de.edgelord.ldjam46;

import de.edgelord.saltyengine.core.graphics.SaltyGraphics;
import de.edgelord.saltyengine.effect.image.SaltyImage;
import de.edgelord.saltyengine.gameobject.EmptyGameObject;
import de.edgelord.saltyengine.transform.Vector2f;
import de.edgelord.saltyengine.utils.SaltySystem;

import java.awt.*;

public class It extends EmptyGameObject {

    public static final String TAG = "it";

    private SaltyImage it = SaltySystem.createPreferredImage(250, 250);

    public It(float xPos, float yPos, float width, float height) {
        super(xPos, yPos, width, height, TAG);

        SaltyGraphics imageGraphics = it.getGraphics();
        imageGraphics.setColor(Color.BLACK);
        imageGraphics.setFont(imageGraphics.getFont().deriveFont(300f));
        imageGraphics.drawText("IT", 125, 125, SaltyGraphics.TextAnchor.CENTRE);
    }

    public It(Vector2f position, float width, float height) {
        this(position.getX(), position.getY(), width, height);
    }

    @Override
    public void draw(SaltyGraphics saltyGraphics) {
        saltyGraphics.drawImage(it, this);
    }
}
