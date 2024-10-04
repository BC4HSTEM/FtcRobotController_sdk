package org.firstinspires.ftc.teamcode.globals;

public enum Side {
    INSTANCE;

    public enum PositionSide {
        BASKETS_SIDE,
        OBSERVATION_ZONE_SIDE
    }

    private PositionSide selectedPositionSide = PositionSide.OBSERVATION_ZONE_SIDE;

    public void setPositionSide(PositionSide positionSide){
        selectedPositionSide = positionSide;
    }

    public PositionSide getPositionSide(){
        return selectedPositionSide;
    }

    public static Side getInstance(){
        return INSTANCE;
    }
}