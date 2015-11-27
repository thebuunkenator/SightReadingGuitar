package model;

/**
 * Scale
 *
 * @author erik
 * @version %I%, %G%
 * @date 30/10/15
 * @since 1.0
 */
public class Scale {
    private byte id;
    private String name;

    public Scale(byte id, String name) {
        this.id = id;
        this.name = name;
    }


    public String getName() {
        return name;
    }


    @Override
    public String toString() {
        return name;
    }
}
