package de.jpaw.bonaparte.jpa;

import java.io.Serializable;
import java.util.List;

/** Wrapper class to store general items which should be converted into database specific native JSON objects.
 * Provided as a hook for the converters.
 * 
 * @author mbi
 *
 */
public final class NativeJsonArray implements Serializable {
    private static final long serialVersionUID = 6234763103220203022L;

    public NativeJsonArray(List<Object> data) {
        this.data = data;
    }

    private List<Object> data;

    public List<Object> getData() {
        return data;
    }

    public void setData(List<Object> data) {
        this.data = data;
    }

    // standard Java stuff: toString(), hashCode(), equals()
    
    @Override
    public String toString() {
        return data == null ? "(null)" : data.toString();
    }
    
    @Override
    public int hashCode() {
        return data == null ? 0 : data.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        NativeJsonArray other = (NativeJsonArray) obj;
        if (data == null) {
            if (other.data != null)
                return false;
        } else if (!data.equals(other.data))
            return false;
        return true;
    }
}
