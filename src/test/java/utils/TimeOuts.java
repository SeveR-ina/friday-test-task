package utils;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum TimeOuts {
    DEFAULT_TIMEOUT_IN_SECONDS(15);

    @Getter
    private long timeOutValue;
}
