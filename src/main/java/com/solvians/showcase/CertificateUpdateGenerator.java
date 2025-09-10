package com.solvians.showcase;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Stream;

public class CertificateUpdateGenerator {
    private final int threads;
    private final int quotes;

    public CertificateUpdateGenerator(int threads, int quotes) {
        this.threads = threads;
        this.quotes = quotes;
    }

    public Stream<CertificateUpdate> generateQuotes() {
        return Stream.generate(this::generateCertificateUpdate).parallel().limit(quotes);
    }

    public CertificateUpdate generateCertificateUpdate() {

        ThreadLocalRandom random = ThreadLocalRandom.current();

        long timestamp = System.currentTimeMillis();
        String isin = IsinGenerator.generateIsin();
        int bidPriceCents = random.nextInt(10000, 20001);
        BigDecimal bidPrice = BigDecimal.valueOf(bidPriceCents, 2);
        int bidSize = random.nextInt(1000, 5001);
        int askPriceCents = random.nextInt(10000, 20001);
        BigDecimal askPrice = BigDecimal.valueOf(askPriceCents, 2);
        int askSize = random.nextInt(1000, 10001);

        return new CertificateUpdate(timestamp, isin, bidPrice, bidSize, askPrice, askSize);
    }
}
