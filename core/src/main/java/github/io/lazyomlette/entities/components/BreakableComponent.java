package github.io.lazyomlette.entities.components;

import com.badlogic.ashley.core.Component;

public class BreakableComponent implements Component {
    public byte brokenStage = 0;
    public byte toolStrength = 0;
}
