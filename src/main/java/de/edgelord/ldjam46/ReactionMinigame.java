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

    private static float countdownPerTick = .10f;

    public static int count = 0;

    public ReactionMinigame() {
        super(SentenceEndings.pickFiveEndings());

        count++;

        if (count >= 2) {
            countdownPerTick += .025f;
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
                    // TODO: SFX
                    MainScene.changeMinigame(new ReactionMinigame());
                }
                break;
            case '2':
                if (SentenceEndings.isGood(getEndings().get(1))) {
                    // TODO: SFX
                    MainScene.changeMinigame(new ReactionMinigame());
                }
                break;
            case '3':
                if (SentenceEndings.isGood(getEndings().get(2))) {
                    // TODO: SFX
                    MainScene.changeMinigame(new ReactionMinigame());
                }
                break;
            case '4':
                if (SentenceEndings.isGood(getEndings().get(3))) {
                    // TODO: SFX
                    MainScene.changeMinigame(new ReactionMinigame());
                }
                break;
            case '5':
                if (SentenceEndings.isGood(getEndings().get(4))) {
                    // TODO: SFX
                    MainScene.changeMinigame(new ReactionMinigame());
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
