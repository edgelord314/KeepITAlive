package de.edgelord.ldjam46;

import de.edgelord.saltyengine.core.graphics.SaltyGraphics;
import de.edgelord.saltyengine.effect.image.SaltyImage;
import de.edgelord.saltyengine.gameobject.EmptyGameObject;
import de.edgelord.saltyengine.io.serialization.Serializable;
import de.edgelord.saltyengine.io.serialization.Serializer;
import de.edgelord.saltyengine.io.serialization.Species;
import de.edgelord.saltyengine.transform.Vector2f;
import de.edgelord.saltyengine.utils.SaltySystem;

import java.awt.*;

public class It extends EmptyGameObject implements Serializable {

    public static final String TAG = "it";

    private SaltyImage it = SaltySystem.createPreferredImage(250, 250);

    public It(float xPos, float yPos, float width, float height) {
        super(xPos, yPos, width, height, TAG);

        Serializer.add(this);
        
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

    @Override
    public void serialize(Species species) {
        species.addTag("highscore", Main.highScore);
    }

    @Override
    public void deserialize(Species species) {
        Main.highScore = Integer.parseInt(species.getTagValue("highscore"));
    }

    @Override
    public String getDataSetName() {
        return "highscore";
    }
}
