package me.minemis.topkamc.system;

public class PlayerCache {

    private long killsAmount = 0;
    private long deathAmount = 0;

    public PlayerCache() {
    }

    public long getDeathAmount() {
        return deathAmount;
    }
    public long getKillsAmount() {
        return killsAmount;
    }

    public void setDeathAmount(long deathAmount) {
        this.deathAmount = deathAmount;
    }
    public void setKillsAmount(long killsAmount) {
        this.killsAmount = killsAmount;
    }

    public void incrementKillsAmount(){
        killsAmount++;
    }
    public void incrementDeathAmount(){
        deathAmount++;
    }

}
