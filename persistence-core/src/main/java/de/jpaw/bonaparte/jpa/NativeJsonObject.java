package de.jpaw.bonaparte.jpa;

import java.util.Map;

/** Wrapper class to store general items which should be converted into database specific native JSON objects.
 * Provided as a hook for the converters.
 * 
 * @author mbi
 *
 */
public class NativeJsonObject {
    public NativeJsonObject(Map<String, Object> data) {
        this.data = data;
    }

    private Map<String, Object> data;

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }
}
