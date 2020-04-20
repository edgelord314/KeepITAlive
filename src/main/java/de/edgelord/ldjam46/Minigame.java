package de.edgelord.ldjam46;

import de.edgelord.saltyengine.core.interfaces.FixedTickRoutine;
import de.edgelord.saltyengine.gameobject.DrawingRoutine;

import java.awt.event.KeyEvent;
import java.util.List;

public abstract class Minigame extends DrawingRoutine implements FixedTickRoutine {

    private List<String> endings;

    public Minigame(List<String> endings) {
        super(DrawingPosition.BEFORE_GAMEOBJECTS);

        this.endings = endings;
    }

    public abstract void buttonPressed(KeyEvent e);

    /**
     * Gets {@link #endings}.
     *
     * @return the value of {@link #endings}
     */
    public List<String> getEndings() {
        return endings;
    }

    /**
     * Sets {@link #endings}.
     *
     * @param endings the new value of {@link #endings}
     */
    public void setEndings(List<String> endings) {
        this.endings = endings;
    }
}
