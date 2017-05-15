package io.t2l.matrix.minecraft.matrix;

/**
 * Represents a user on Matrix
 */
public class MatrixUser {

    private String mxid;
    private String name;

    MatrixUser(String mxid, String displayName) {
        this.mxid = mxid;
        this.name = displayName;
    }

    /**
     * Gets the Matrix ID of this user
     *
     * @return the user's ID
     */
    public String getUserId() {
        return this.mxid;
    }

    /**
     * Gets the display name for this user
     *
     * @return the user's display name
     */
    public String getDisplayName() {
        return this.name;
    }
}
