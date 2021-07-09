#tsBenchmark - benchmarking traveling salesman solutions

build this project with 
```
./gradlew installShadowDist
```
this will place binaries and a fat jar in build/install/tsBenchmark-shadow/

you can then run build/install/tsBenchmark-shadow/bin/tsBenchmark as a commandline script  -h or --help will list all the runtime options. baseurl defaults to http://localhost:8080

```
> tsBenchmark --help 

usage: smokeTest <options> [baseurl]

Run benchmarks against traveling salesmen instances

baseurl = location of services

-c,--cities <arg>       number of cities (default 6)
-d,--dist               get distances
-e,--exit               exit before benchmarks
-h,--help               print this message
-i,--iterations <arg>   number of loops through requests per thread
-p,--perms              get list of all perumutations
-s,--shortest           get the shortest routes
-t,--threads <arg>      number of parallel threads
```

Example:

```
> tsBenchmark -pds -c8 -t10 -i100 http://myurl.com:8080
```

The above command will calculate all permutations, distances, and shortest distance for 8 cities using 10 parallel 
threads. Each thread will loop through the requests 100 times hitting an endpoint at myurl.com on port 8080