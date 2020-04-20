package de.edgelord.ldjam46;

import de.edgelord.saltyengine.core.Game;
import de.edgelord.saltyengine.core.animation.KeyframeAnimation;
import de.edgelord.saltyengine.core.animation.LinearKeyframeAnimation;
import de.edgelord.saltyengine.core.graphics.SaltyGraphics;
import de.edgelord.saltyengine.gameobject.DrawingRoutine;
import de.edgelord.saltyengine.scene.Scene;
import de.edgelord.saltyengine.scene.SceneManager;
import de.edgelord.saltyengine.ui.elements.Button;
import de.edgelord.saltyengine.utils.ColorUtil;

import java.awt.*;
import java.awt.event.MouseEvent;

public class LostScene extends Scene {

    private Button tryAgain = new Button("Try Again", Game.getHost().getHorizontalCentrePosition(300), Game.getGameHeight() - 200, 300, 100) {
        @Override
        public void onClick(MouseEvent e) {
            SceneManager.setCurrentScene(new MainScene());
        }
    };

    private KeyframeAnimation tryAgainTransparencyAnimation = new LinearKeyframeAnimation();

    public LostScene(String sentence) {

        Main.audioPlayer.stop("maintheme");
        Main.audioPlayer.loop("raindrop");
        Main.audioPlayer.play("lost");

        Game.getHost().setBackgroundColor(ColorUtil.FIREBRICK_RED);

        tryAgain.setBackgroundColor(ColorUtil.withAlpha(ColorUtil.ORANGE, 0));
        tryAgain.setForegroundColor(ColorUtil.withAlpha(ColorUtil.NAVAJO_WHITE, 0));
        tryAgain.setFont(tryAgain.getFont().deriveFont(50f));
        tryAgain.setArc(50);

        getUI().addElement(tryAgain);

        tryAgainTransparencyAnimation.add(250, 0);
        tryAgainTransparencyAnimation.add(450, 255);
        tryAgainTransparencyAnimation.calculateAnimation();

        addDrawingRoutine(new DrawingRoutine(DrawingRoutine.DrawingPosition.AFTER_GAMEOBJECTS) {
            @Override
            public void draw(SaltyGraphics saltyGraphics) {
                saltyGraphics.setColor(Color.BLACK);
                saltyGraphics.setFont(saltyGraphics.getFont().deriveFont(50f));

                saltyGraphics.drawText(sentence, Game.getGameWidth() / 2f, Game.getGameHeight() / 2f, SaltyGraphics.TextAnchor.CENTRE);

                saltyGraphics.setFont(saltyGraphics.getFont().deriveFont(20f));
                saltyGraphics.drawText("Highscore: " + Main.highScore, 10, 5, SaltyGraphics.TextAnchor.TOP_LEFT_CORNER);
                saltyGraphics.drawText("Score: " + ReactionMinigame.count, Game.getGameWidth() - 10, 5, SaltyGraphics.TextAnchor.TOP_RIGHT_CORNER);
            }
        });
    }

    @Override
    public void onFixedTick() {
        int delta = Math.round(tryAgainTransparencyAnimation.nextDelta());
        int currAlpha = tryAgain.getBackgroundColor().getAlpha();

        if (delta + currAlpha <= 255) {
            tryAgain.setBackgroundColor(ColorUtil.withAlpha(tryAgain.getBackgroundColor(), currAlpha + delta));
            tryAgain.setForegroundColor(ColorUtil.withAlpha(tryAgain.getForegroundColor(), currAlpha + delta));
        }
        super.onFixedTick();
    }
}
