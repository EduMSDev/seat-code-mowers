package org.jakos176;

import lombok.Builder;
import lombok.Value;

import java.util.List;

@Builder
@Value
public class Plateau {

    List<List<Cell>> plateauDistribution;

    private boolean isPositionBusy(Integer posX, Integer posY) {
        return this.findCellPosition(posX, posY).isBusy();
    }

    private Cell findCellPosition(int posX, int posY) {
        return this.plateauDistribution.stream()
                .flatMap(List::stream)
                .filter(cell -> cell.getPointX() == posX && cell.getPointY() == posY)
                .findFirst()
                .orElse(null);
    }

    public void setPositionBusy(Integer posX, Integer posY) {
        this.findCellPosition(posX, posY).setBusy(true);
    }

    public void setPositionCut(Integer posX, Integer posY) {
        this.findCellPosition(posX, posY).setCut(true);
    }

    private boolean isPositionCut(Integer posX, Integer posY) {
        return this.findCellPosition(posX, posY).isCut();
    }

    public boolean positionIsWithInRange(Integer posX, Integer posY) {
        return posX <= this.plateauDistribution.get(0).size() && posY <= this.plateauDistribution.get(1).size();
    }

    public boolean setMowerInPlateau(Integer posX, Integer posY) {
        if (this.isPositionBusy(posX, posY)) {
            return false;
        }

        if (this.isPositionCut(posX, posY)) {
            return false;
        }

        this.setPositionBusy(posX, posY);
        return true;
    }

    @Override
    public String toString() {
        return "Plateau{" +
                "plateauDistribution=" + plateauDistribution +
                '}';
    }
}
