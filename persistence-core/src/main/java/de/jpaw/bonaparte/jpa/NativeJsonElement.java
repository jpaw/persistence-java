package de.jpaw.bonaparte.jpa;

/** Wrapper class to store general items which should be converted into database specific native JSON objects.
 * Provided as a hook for the converters.
 * 
 * @author mbi
 *
 */
public class NativeJsonElement {
    public NativeJsonElement(Object data) {
        this.data = data;
    }

    private Object data;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
