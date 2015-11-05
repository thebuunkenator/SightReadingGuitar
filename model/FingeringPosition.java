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
    C_CAGED,
    A_CAGED,
    G_CAGED,
    E_CAGED,
    D_CAGED,
    E_3_PER_STRING;

    @Override
    public String toString() {
        switch (this) {
            case C_CAGED:
                return "C (CAGED)";

            case A_CAGED:
                return "A (CAGED)";

            case G_CAGED:
                return "G (CAGED)";

            case E_CAGED:
                return "E (CAGED)";

            case D_CAGED:
                return "D (CAGED)";

            case E_3_PER_STRING:
                return "E (3 per string)";

            default:
                return"unknown";

        }
    }
}


