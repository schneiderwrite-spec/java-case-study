package com.solvians.showcase;

import java.math.BigDecimal;

public class CertificateUpdate {

    private final long timestamp;
    private final String isin;
    private final BigDecimal bidPrice;
    private final int bidSize;
    private final BigDecimal askPrice;
    private final int askSize;

    public CertificateUpdate(long timestamp,
                             String isin,
                             BigDecimal bidPrice,
                             int bidSize,
                             BigDecimal askPrice,
                             int askSize) {
        this.timestamp = timestamp;
        this.isin = isin;
        this.bidPrice = bidPrice;
        this.bidSize = bidSize;
        this.askPrice = askPrice;
        this.askSize = askSize;
    }

    @Override
    public String toString() {
        return String.format("%d,%s,%.2f,%d,%.2f,%d",
                this.timestamp,
                this.isin,
                this.bidPrice,
                this.bidSize,
                this.askPrice,
                this.askSize);
    }
}
