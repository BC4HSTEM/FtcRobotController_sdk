package org.firstinspires.ftc.teamcode.globals;

public enum ArmAdjust {
    INSTANCE;

    public enum AdjustArm {
        ADJUST_NONE,
        ADJUST_DOWN,
        ADJUST_UP,
        ADJUST_RESET
    }


    private AdjustArm selectedAdjustArm = AdjustArm.ADJUST_NONE;

    public void setAdjustArm(AdjustArm adjustArm){
        selectedAdjustArm = adjustArm;
    }

    public AdjustArm getAdjustArm(){
        return selectedAdjustArm;
    }

    public static ArmAdjust getInstance(){
        return INSTANCE;
    }

}