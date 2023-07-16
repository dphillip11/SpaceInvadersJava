package SpaceEvaders.Builder;

import java.util.ArrayList;
import java.util.List;

import SpaceEvaders.GameObjects.Spaceship;
import SpaceEvaders.Utilities.Vector2;

public class SpaceshipBuilder {
    //keep a list of presets
    List<SpaceshipPreset> presets = new ArrayList<SpaceshipPreset>();
    //keep a list of incidence rates
    List<Float> incidenceRates = new ArrayList<Float>();
    //total incidence
    float totalIncidence = 0;

    public void addPreset(SpaceshipPreset preset, float incidence) {
        presets.add(preset);
        incidenceRates.add(incidence);
        totalIncidence += incidence;
    }

    private int choosePreset() {
        float random = (float) Math.random() * totalIncidence;
        float sum = 0;
        for (int i = 0; i < incidenceRates.size(); i++) {
            sum += incidenceRates.get(i);
            if (random < sum) {
                //return the preset
                return i;
            }
        }
        //return the last preset
        return presets.size() - 1;
    }

    public void changeIncidence(int index, float new_incidence) {
        totalIncidence += new_incidence - incidenceRates.get(index);
        incidenceRates.set(index, new_incidence);
    }

    public Spaceship newShip(Vector2 position) {
        SpaceshipPreset preset = presets.get(choosePreset());
        Spaceship ship = new Spaceship();
        ship.applyPreset(preset);
        ship.setPosition(position);
        return ship;
    }
}
    
