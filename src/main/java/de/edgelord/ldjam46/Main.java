package de.edgelord.ldjam46;

import de.edgelord.saltyengine.audio.AudioPlayer;
import de.edgelord.saltyengine.core.Game;
import de.edgelord.saltyengine.core.GameConfig;
import de.edgelord.saltyengine.factory.AudioFactory;
import de.edgelord.saltyengine.io.serialization.Serializer;
import de.edgelord.saltyengine.scene.SceneManager;
import de.edgelord.saltyengine.utils.ColorUtil;
import de.edgelord.saltyengine.utils.GeneralUtil;
import de.edgelord.saltyengine.utils.SaltySystem;

import java.io.IOException;

public class Main extends Game {

    public static It it;

    public static AudioPlayer audioPlayer;

    public static int highScore = 0;

    public static void main(String[] args) throws IOException {
        init(GameConfig.config(850, 750, "Keep IT Alive", 5));
        start(30);

        serializeOnExit();

        audioPlayer = new AudioPlayer(new AudioFactory(SaltySystem.defaultResource));
        audioPlayer.loadNewAudio("maintheme", "Maintheme.wav");
        audioPlayer.loadNewAudio("raindrop", "Raindrop.wav");

        audioPlayer.loadNewAudio("success", "Success.wav");
        audioPlayer.setClipVolume("success", .6f);
        audioPlayer.loadNewAudio("lost", "Lost.wav");
        audioPlayer.loadNewAudio("yes", "Yes.wav");
        audioPlayer.loadNewAudio("nice", "Nice.wav");

        setDrawFPS(false);
        getHost().setBackgroundColor(ColorUtil.WHITE_SMOKE);

        it = new It(Game.getHost().getHorizontalCentrePosition(75), 50, 75, 75);
        SceneManager.setCurrentScene(new MenuScene());

        if (Serializer.doDeserialization()) {
            System.out.println("The save file is corrupted as the checksum does not match. Most likely, it has been manually edited!");
            highScore = 0;
        }
    }

    public static void playSuccess() {
        audioPlayer.play("success");

        int i;
        if ((i = GeneralUtil.randomInt(0, 4)) <= 1) {
            audioPlayer.play(i == 0 ? "yes" : "nice");
        }
    }
}
