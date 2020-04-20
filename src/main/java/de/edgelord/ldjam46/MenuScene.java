package de.edgelord.ldjam46;

import de.edgelord.saltyengine.core.Game;
import de.edgelord.saltyengine.core.animation.KeyframeAnimation;
import de.edgelord.saltyengine.core.animation.LinearKeyframeAnimation;
import de.edgelord.saltyengine.core.graphics.SaltyGraphics;
import de.edgelord.saltyengine.gameobject.DrawingRoutine;
import de.edgelord.saltyengine.gameobject.FixedTask;
import de.edgelord.saltyengine.scene.Scene;
import de.edgelord.saltyengine.scene.SceneManager;
import de.edgelord.saltyengine.ui.elements.Button;
import de.edgelord.saltyengine.utils.ColorUtil;

import java.awt.*;
import java.awt.event.MouseEvent;

public class MenuScene extends Scene {

    private float textOffset = 225;
    private boolean sceneTransition = false;

    public MenuScene() {

        Main.audioPlayer.loop("maintheme");
        Main.audioPlayer.stop("raindrop");

        Game.getHost().setBackgroundColor(ColorUtil.ACTIVE_GREEN);

        KeyframeAnimation textOffsetAnimation = new LinearKeyframeAnimation();
        textOffsetAnimation.add(50, -600);
        textOffsetAnimation.calculateAnimation();

        KeyframeAnimation itAnimationX = new LinearKeyframeAnimation();
        itAnimationX.add(100, -Game.getGameWidth() / 2f + Main.it.getWidth() / 2f);
        itAnimationX.calculateAnimation();

        KeyframeAnimation itAnimationY = new LinearKeyframeAnimation();
        itAnimationY.add(100, Game.getHost().getVerticalCentrePosition(Main.it.getHeight()) - (Main.it.getY() + Main.it.getHeight() / 2f));
        itAnimationY.calculateAnimation();

        addGameObject(Main.it);

        addDrawingRoutine(new DrawingRoutine(DrawingRoutine.DrawingPosition.AFTER_GAMEOBJECTS) {
            @Override
            public void draw(SaltyGraphics saltyGraphics) {
                saltyGraphics.setColor(Color.BLACK);
                saltyGraphics.setFont(saltyGraphics.getFont().deriveFont(50f));

                saltyGraphics.drawText("Keep", textOffset, 50, SaltyGraphics.TextAnchor.TOP_LEFT_CORNER);
                saltyGraphics.drawText("Alive", Game.getGameWidth() - textOffset, 50, SaltyGraphics.TextAnchor.TOP_RIGHT_CORNER);

                saltyGraphics.setFont(saltyGraphics.getFont().deriveFont(15f));
                saltyGraphics.drawText("A reaction game made by Malte Dostal (edgelord314) in occasion of the Ludum Dare Gamejam 46.", 80, Game.getGameHeight() - 15, SaltyGraphics.TextAnchor.BOTTOM_LEFT_CORNER);
            }
        });

        addFixedTask(new FixedTask() {
            @Override
            public void onFixedTick() {
                if (sceneTransition) {
                    textOffset += textOffsetAnimation.nextDelta();
                    Main.it.getPosition().add(itAnimationX.nextDelta(), itAnimationY.nextDelta());

                    if (itAnimationX.animationEnded()) {
                        SceneManager.setCurrentScene(new CountdownScene(new MainScene()));
                    }
                }
            }
        });

        Button startButton = new Button("Start", Game.getHost().getCentrePosition(300, 100), 300, 100) {
            @Override
            public void onClick(MouseEvent e) {
                sceneTransition = true;
                getUI().removeElement(this);
            }
        };

        startButton.setBackgroundColor(ColorUtil.ORANGE);
        startButton.setForegroundColor(ColorUtil.NAVAJO_WHITE);
        startButton.setFont(startButton.getFont().deriveFont(50f));
        startButton.setArc(50);

        getUI().addElement(startButton);
    }
}
