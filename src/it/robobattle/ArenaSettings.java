package it.robobattle;

import java.util.HashMap;

/**
 * Created by alex on 28-06-2016.
 * Settings per il componente Arena
 */
public class ArenaSettings {

    private HashMap<Tests, Boolean> enabledTests = new HashMap();
    private int sliderValue=0;


    public ArenaSettings() {
        for(Tests test : Tests.values()){
            enabledTests.put(test, true);
        }

        sliderValue=Arena.MIN_REQ_PER_SESSION + (Arena.MAX_REQ_PER_SESSION - Arena.MIN_REQ_PER_SESSION) / 2;

    }

    public void setTestEnabled(Tests test, boolean enabled){
        enabledTests.put(test, enabled);
    }

    public boolean isTestEnabled(Tests test){
        boolean bool=false;
        Boolean enabled=enabledTests.get(test);
        if(enabled!=null){
            bool=enabled;
        }
        return bool;

    }

    public int getSliderValue() {
        return sliderValue;
    }

    public void setSliderValue(int sliderValue) {
        this.sliderValue = sliderValue;
    }
}
