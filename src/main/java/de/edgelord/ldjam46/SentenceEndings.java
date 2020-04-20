package de.edgelord.ldjam46;

import de.edgelord.saltyengine.utils.GeneralUtil;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class SentenceEndings {
    private static List<String> GOOD_ENDINGS = new ArrayList<>() {{
        add("rains.");
        add("is fine.");
        add("sucks.");
        add("hurts.");
        add("is a success.");
        add("it is cool.");
        add("is bad.");
        add("is gone.");
        add("is born.");
        add("is happening!");
        add("is alright.");
        add("smiles.");
    }};

    private static List<String> BAD_ENDINGS = new ArrayList<>() {{
        add("blows up.");
        add("dies.");
        add("chokes.");
        add("eats 1kg of salt.");
        add("rests in peace.");
        add("jumps out of a building.");
        add("plays with deadly snakes.");
        add("falls down a cliff.");
        add("has faded away.");
        //TODO add more
        add("eats a funny mushroom.");
        add("didn't wash ITs hands.");
        add("plays russian roulette.");
        add("is a Yedi during Order 66.");
        add("explodes.");
        add("meets a wild lion.");
        add("swims with hungry sharks.");
        add("sees a T-Rex.");
        add("hears Astronomia.");
    }};

    public static boolean isGood(String s) {
        return GOOD_ENDINGS.contains(s);
    }

    public static List<String> pickFiveEndings() {
        List<String> list = new LinkedList<>();
        list.add(GeneralUtil.randomObjectFromList(GOOD_ENDINGS).toString());

        for (int i = 0; i < 4; i++) {
            String s = GeneralUtil.randomObjectFromList(BAD_ENDINGS).toString();

            while (list.contains(s)) {
                s = GeneralUtil.randomObjectFromList(BAD_ENDINGS).toString();
            }
            list.add(s);
        }

        Collections.shuffle(list, new SecureRandom());
        return list;
    }
}
