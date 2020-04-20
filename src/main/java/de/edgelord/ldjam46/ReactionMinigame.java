package de.edgelord.ldjam46;

import de.edgelord.saltyengine.core.Game;
import de.edgelord.saltyengine.core.graphics.SaltyGraphics;
import de.edgelord.saltyengine.scene.SceneManager;
import de.edgelord.saltyengine.ui.elements.ProgressBar;
import de.edgelord.saltyengine.utils.ColorUtil;

import java.awt.*;
import java.awt.event.KeyEvent;

public class ReactionMinigame extends Minigame {

    private ProgressBar countdown = new ProgressBar(Game.getHost().getHorizontalCentrePosition(300), 25, 300, 25);

    private static float countdownPerTick = .015f;

    public static int count = 0;

    public ReactionMinigame() {
        super(SentenceEndings.pickFiveEndings());

        count++;

        if (count % 3 == 0) {
            countdownPerTick += .010f;
            System.out.println("the game just got a bit more difficult! (reference: " + countdownPerTick + ")");
        }

        countdown.setBackgroundColor(ColorUtil.BLACK);
        countdown.setForegroundColor(ColorUtil.BLACK);
        countdown.setCurrentValue(countdown.getMaxValue());
        Game.getHost().setBackgroundColor(ColorUtil.ACTIVE_GREEN);
    }

    public static void resetDifficulty() {
        countdownPerTick = .15f;
        count = 0;
    }

    @Override
    public void onFixedTick() {
        countdown.setCurrentValue(countdown.getCurrentValue() - countdownPerTick);
        Game.getHost().setBackgroundColor(ColorUtil.blend(ColorUtil.FIREBRICK_RED, ColorUtil.ACTIVE_GREEN, countdown.getCurrentValue() / 100f));

        if (countdown.getCurrentValue() <= 0) {
            String ending = getEndings().get(0);

            if (SentenceEndings.isGood(ending)) {
                ending = getEndings().get(1);
            }
            SceneManager.setCurrentScene(new LostScene("IT " + ending));
        }
    }

    @Override
    public void buttonPressed(KeyEvent e) {
        switch (e.getKeyChar()) {
            case '1':
                if (SentenceEndings.isGood(getEndings().get(0))) {
                    MainScene.changeMinigame(new ReactionMinigame());
                    Main.playSuccess();
                } else {
                    SceneManager.setCurrentScene(new LostScene("IT " + getEndings().get(0)));
                }
                break;
            case '2':
                if (SentenceEndings.isGood(getEndings().get(1))) {
                    MainScene.changeMinigame(new ReactionMinigame());
                    Main.playSuccess();
                } else {
                    SceneManager.setCurrentScene(new LostScene("IT " + getEndings().get(1)));
                }
                break;
            case '3':
                if (SentenceEndings.isGood(getEndings().get(2))) {
                    MainScene.changeMinigame(new ReactionMinigame());
                    Main.playSuccess();
                } else {
                    SceneManager.setCurrentScene(new LostScene("IT " + getEndings().get(2)));
                }
                break;
            case '4':
                if (SentenceEndings.isGood(getEndings().get(3))) {
                    MainScene.changeMinigame(new ReactionMinigame());
                    Main.playSuccess();
                } else {
                    SceneManager.setCurrentScene(new LostScene("IT " + getEndings().get(3)));
                }
                break;
            case '5':
                if (SentenceEndings.isGood(getEndings().get(4))) {
                    MainScene.changeMinigame(new ReactionMinigame());
                    Main.playSuccess();
                } else {
                    SceneManager.setCurrentScene(new LostScene("IT " + getEndings().get(4)));
                }
                break;
        }
    }

    @Override
    public void draw(SaltyGraphics saltyGraphics) {
        saltyGraphics.setColor(Color.BLACK);
        saltyGraphics.setFont(saltyGraphics.getFont().deriveFont(35f));

        int i = 0;
        for (String s : getEndings()) {
            saltyGraphics.drawText("..." + s + " [" + (i + 1) + "]", Game.getGameWidth(), /*offset*/75 + i * (saltyGraphics.getFont().getSize2D() + 100), SaltyGraphics.TextAnchor.TOP_RIGHT_CORNER);
            i++;
        }

        countdown.draw(saltyGraphics);
    }
}
