package it.robobattle;

/**
 * Created by alex on 16/06/16.
 */
public enum Tests {
    SORT_WORD("sortWord"),
    INVERT_WORD("invertWord"),
    CALC_CKECKSUM("calcChecksum"),
    DECRYPT_WORD("decryptWord");

    private String testName;

    private Tests(String testName) {
        this.testName = testName;
    }

    public String getTestName() {
        return testName;
    }
}
