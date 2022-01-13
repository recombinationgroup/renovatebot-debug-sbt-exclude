# Minimum working example of an issue with the sbt manager of renovatebot

## The issue

When running renovatebot (version 31.23.1) agains the files in this repository, it only detects two dependencies:
```
 INFO: Dependency extraction complete (repository=THISREPO)
       "baseBranch": "main",
       "stats": {
         "managers": {"sbt": {"fileCount": 1, "depCount": 2}},
         "total": {"fileCount": 1, "depCount": 2}
       }
```
these dependencies are
```
DEBUG: packageFiles with updates (repository=THISREPO)
       "config": {
         "sbt": [
           {
             "packageFile": "build.sbt",
             "deps": [
               {
                 "registryUrls": ["https://repo.maven.apache.org/maven2"],
                 "datasource": "maven",
                 "depName": "scala",
                 "lookupName": "org.scala-lang:scala-library",
                 "currentValue": "2.13.8",
                 "separateMinorPatch": true,
                 "depIndex": 0,
                 "updates": [],
                 "warnings": [],
                 "versioning": "ivy",
                 "sourceUrl": "https://github.com/scala/scala",
                 "homepage": "https://www.scala-lang.org/",
                 "currentVersion": "2.13.8",
                 "fixedVersion": "2.13.8"
               },
               {
                 "registryUrls": ["https://repo.maven.apache.org/maven2"],
                 "depName": "com.lightbend.akka:akka-stream-alpakka-s3",
                 "lookupName": "com.lightbend.akka:akka-stream-alpakka-s3_2.13",
                 "currentValue": "2.0.0",
                 "datasource": "sbt-package",
                 "depIndex": 1,
                 "updates": [
                   {
                     "bucket": "non-major",
                     "newVersion": "2.0.2",
                     "newValue": "2.0.2",
                     "newMajor": 2,
                     "newMinor": 0,
                     "updateType": "patch",
                     "branchName": "renovate/com.lightbend.akka-akka-stream-alpakka-s3-2.x"
                   },
                   {
                     "bucket": "major",
                     "newVersion": "3.0.4",
                     "newValue": "3.0.4",
                     "newMajor": 3,
                     "newMinor": 0,
                     "updateType": "major",
                     "branchName": "renovate/com.lightbend.akka-akka-stream-alpakka-s3-3.x"
                   }
                 ],
                 "warnings": [],
                 "versioning": "ivy",
                 "sourceUrl": "https://github.com/akka/alpakka",
                 "homepage": "https://doc.akka.io/docs/alpakka/current",
                 "dependencyUrl": "https://repo.maven.apache.org/maven2/com/lightbend/akka",
                 "currentVersion": "2.0.0",
                 "isSingleVersion": true,
                 "fixedVersion": "2.0.0"
               }
             ]
           }
         ]
       }
```
While it does detect `scala` and `akka-stream-alpakka-s3`, it does _not_ detect `akka-stream-alpakka-csv`, which is also present in `build.sbt`.

The reason seems to be the `excludeAll ...` portion of the dependency notation.

My expectation would have been for the `sbt` manager to ignore the `excludeAll` qualifier and pick up `akka-stream-alpakka-csv`.
