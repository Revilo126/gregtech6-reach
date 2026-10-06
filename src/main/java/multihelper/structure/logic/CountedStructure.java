package multihelper.structure.logic;

import static gregapi.data.CS.*;

import java.util.HashMap;
import java.util.Map;

public abstract class CountedStructure implements ICountedStructure {

    protected final Map<String, Integer> counts = new HashMap<String, Integer>();

    @Override
    public int getCount(String key) {
        Integer v = counts.get(key);
        return v == null ? 0 : v.intValue();
    }

    @Override
    public void setCount(String key, int value) {
        counts.put(key, value);
    }

    @Override
    public void decCount(String key) {
        setCount(key, getCount(key) - 1);
    }

    public boolean checkCounts() {
        boolean tSuccess = T;
        for (String i : counts.keySet()) {
            if (getCount(i) != 0) tSuccess = F;
        }
        return tSuccess;
    }
}
