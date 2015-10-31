import java.util.EventObject;

/**
 * FormEvent
 *
 * @author erik
 * @version %I%, %G%
 * date 31/10/15
 * @since 1.0
 */
public class FormEvent extends EventObject {

    private String key;
    private String fingeringSystem;
    private String scale;

    public FormEvent(Object source) {
        super(source);
    }

    public FormEvent(Object source, String key, String fingeringSystem, String scale) {
        super(source);
        this.key = key;
        this.fingeringSystem = fingeringSystem;
        this.scale = scale;

        System.out.println("Form Event: We have been notified.");
    }

    /*
    Getters and setters
     */
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getFingeringSystem() {
        return fingeringSystem;
    }

    public void setFingeringSystem(String fingeringSystem) {
        this.fingeringSystem = fingeringSystem;
    }

    public String getScale() {
        return scale;
    }

    public void setScale(String scale) {
        this.scale = scale;
    }
}
