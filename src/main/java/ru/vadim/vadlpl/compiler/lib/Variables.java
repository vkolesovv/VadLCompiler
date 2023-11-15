package ru.vadim.vadlpl.compiler.lib;

import ru.vadim.vadlpl.compiler.lib.values.NullValue;
import ru.vadim.vadlpl.compiler.lib.values.NumberValue;
import ru.vadim.vadlpl.compiler.lib.values.Value;

import java.util.HashMap;
import java.util.Map;

public final class Variables {
    private static final Map<String, Value> variables = new HashMap<>();

    static {
        variables.put("PI", new NumberValue(Math.PI));
    }

    public static boolean isExists(String key) {
        return variables.containsKey(key);
    }

    public static Value getVariable(String name) {
        if (!isExists(name)) return new NullValue();
        return variables.get(name);
    }

    public static void setVariable(String name, Value value) {
        variables.put(name, value);
    }
}
