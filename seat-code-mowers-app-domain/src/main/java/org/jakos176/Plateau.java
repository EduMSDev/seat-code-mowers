package org.jakos176;

import lombok.Builder;
import lombok.Value;

import java.util.List;

//todo cambiar boolean por Boolean
// todo implement exceptions

@Builder
@Value
public class Plateau {

    List<List<Cell>> plateauDistribution;

    public boolean isPositionBusy(Integer posX, Integer posY) {
        return plateauDistribution.get(posX).get(posY).isBusy();
    }

    public boolean isPositionCut(Integer posX, Integer posY) {
        return plateauDistribution.get(posX).get(posY).isCut();
    }

    public boolean positionIsWithInRange(Integer posX, Integer posY) {
        return posX <= this.plateauDistribution.get(0).size() && posY <= this.plateauDistribution.get(1).size();
    }

    public boolean setMowerInPlateau(Integer posX, Integer posY) {
        if (this.isPositionBusy(posX, posY)) {
            return false;
        }

        if (this.isPositionBusy(posX, posY)) {
            return false;
        }

        this.plateauDistribution.get(posX).get(posY).setBusy(true);
        return true;
    }
}
