package de.edgelord.ldjam46;

import de.edgelord.saltyengine.input.Input;
import de.edgelord.saltyengine.input.KeyboardInputHandler;
import de.edgelord.saltyengine.scene.Scene;
import de.edgelord.saltyengine.scene.SceneManager;

import java.awt.event.KeyEvent;

public class MainScene extends Scene {

    private Minigame minigame = new ReactionMinigame();

    private static MainScene currentInstance = null;

    public MainScene() {

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

    public static void changeMinigame(Minigame minigame) {
        currentInstance.removeDrawingRoutine(currentInstance.minigame);
        currentInstance.addDrawingRoutine(minigame);
        currentInstance.minigame = minigame;
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
