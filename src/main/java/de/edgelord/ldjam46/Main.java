package de.edgelord.ldjam46;

import de.edgelord.saltyengine.audio.AudioPlayer;
import de.edgelord.saltyengine.core.Game;
import de.edgelord.saltyengine.core.GameConfig;
import de.edgelord.saltyengine.factory.AudioFactory;
import de.edgelord.saltyengine.scene.SceneManager;
import de.edgelord.saltyengine.utils.ColorUtil;
import de.edgelord.saltyengine.utils.SaltySystem;

public class Main extends Game {

    public static It it;

    public static AudioPlayer audioPlayer;

    public static void main(String[] args) {
        init(GameConfig.config(850, 750, "Keep IT Alive", 5));
        start(30);

        audioPlayer = new AudioPlayer(new AudioFactory(SaltySystem.defaultResource));
        audioPlayer.loadNewAudio("maintheme", "Maintheme.wav");
        audioPlayer.loadNewAudio("raindrop", "Raindrop.wav");

        setDrawFPS(false);
        getHost().setBackgroundColor(ColorUtil.WHITE_SMOKE);

        it = new It(Game.getHost().getHorizontalCentrePosition(75), 50, 75, 75);
        SceneManager.setCurrentScene(new MenuScene());
    }
}
