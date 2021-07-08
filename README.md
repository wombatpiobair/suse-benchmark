# suse-benchmark
a place to store benchmarking code for SUSE

travSales - brute force implementation of traveling salesman problem.

travelSalesSB - spring boot REST wrapper for travSales.

travelSalesQ - Quarkus REST wrapper for travSales.
To use: 
- Install GraalVM https://www.graalvm.org/docs/getting-started/macos/
- set GRAALVM_HOME env var
- add $GRAALVM_HOME/bin to PATH
- `gu install native-image`
- `./gradlew build -Dquarkus.package.type=native` (OS specific; add `-Dquarkus.native.container-build=true` for containers)

tsClient - travelingsalesman rest client for integration with benchmark tests.
