package de.edgelord.ldjam46;

import de.edgelord.saltyengine.core.Game;
import de.edgelord.saltyengine.core.graphics.SaltyGraphics;
import de.edgelord.saltyengine.effect.image.SaltyImage;
import de.edgelord.saltyengine.scene.Scene;
import de.edgelord.saltyengine.scene.SceneManager;
import de.edgelord.saltyengine.utils.ColorUtil;
import de.edgelord.saltyengine.utils.SaltySystem;

public class CountdownScene extends Scene {

    private int ticks = 0;
    private int currentNumber = 3;
    private Scene nextScene;

    private SaltyImage bgImg = SaltySystem.createPreferredImage(Game.getGameWidth(), Game.getGameHeight());

    public CountdownScene(Scene nextScene) {
        this.nextScene = nextScene;

        nextScene.draw(bgImg.getGraphics());
    }

    @Override
    public void draw(SaltyGraphics saltyGraphics) {
        saltyGraphics.drawImage(bgImg, 0, 0);
        saltyGraphics.setColor(ColorUtil.DARK_GRAY);
        saltyGraphics.setFont(saltyGraphics.getFont().deriveFont(150f));
        saltyGraphics.drawText(currentNumber, Game.getGameWidth() / 2f, Game.getGameHeight() / 2f, SaltyGraphics.TextAnchor.CENTRE);
    }

    @Override
    public void onFixedTick() {
        if (ticks >= 100) {
            currentNumber--;
            ticks = 0;
            if (currentNumber <= -1) {
                SceneManager.setCurrentScene(nextScene);
            }
        } else {
            ticks++;
        }
    }
}
