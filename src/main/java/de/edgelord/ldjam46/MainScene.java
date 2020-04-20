package de.edgelord.ldjam46;

import de.edgelord.saltyengine.core.Game;
import de.edgelord.saltyengine.core.graphics.SaltyGraphics;
import de.edgelord.saltyengine.input.Input;
import de.edgelord.saltyengine.input.KeyboardInputHandler;
import de.edgelord.saltyengine.scene.Scene;
import de.edgelord.saltyengine.scene.SceneManager;

import java.awt.event.KeyEvent;

public class MainScene extends Scene {

    private Minigame minigame = new ReactionMinigame();

    private static MainScene currentInstance = null;

    public MainScene() {

        Main.audioPlayer.loop("maintheme");
        Main.audioPlayer.stop("raindrop");

        currentInstance = this;

        ReactionMinigame.resetDifficulty();

        addGameObject(Main.it);
        addDrawingRoutine(minigame);
    }

    @Override
    public void onFixedTick() {
        minigame.onFixedTick();
        super.onFixedTick();
    }

    @Override
    public void draw(SaltyGraphics saltyGraphics) {
        super.draw(saltyGraphics);

        saltyGraphics.setFont(saltyGraphics.getFont().deriveFont(20f));
        saltyGraphics.drawText("Highscore: " + Main.highScore, 10, 5, SaltyGraphics.TextAnchor.TOP_LEFT_CORNER);
        saltyGraphics.drawText("Score: " + ReactionMinigame.count, Game.getGameWidth() - 10, 5, SaltyGraphics.TextAnchor.TOP_RIGHT_CORNER);
    }

    public static void changeMinigame(Minigame minigame) {
        currentInstance.removeDrawingRoutine(currentInstance.minigame);
        currentInstance.addDrawingRoutine(minigame);
        currentInstance.minigame = minigame;

        if (ReactionMinigame.count > Main.highScore) {
            Main.highScore = ReactionMinigame.count;
        }
    }

    static {
        Input.addKeyboardInputHandler(new KeyboardInputHandler() {
            @Override
            public void keyPressed(KeyEvent e) {

                if (SceneManager.getCurrentScene().equals(currentInstance)) {
                    currentInstance.minigame.buttonPressed(e);
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {

            }

            @Override
            public void keyTyped(KeyEvent e) {

            }
        });
    }
}
