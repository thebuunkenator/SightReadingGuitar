package model;

/**
 * FingeringPosition
 *
 * @author erik
 * @version %I%, %G%
 * @since 1.0
 * date:      05/11/15
 */
public enum FingeringPosition {
    C_CAGED ("C (CAGED)"),
    A_CAGED ("A (CAGED)"),
    G_CAGED ("G (CAGED)"),
    E_CAGED ("E (CAGED)"),
    D_CAGED ("D (CAGED)");

    private String nameAsString;

    FingeringPosition (String name) {
        this.nameAsString =name;
    }

    @Override
    public String toString() {
        return this.nameAsString;
    }
}


