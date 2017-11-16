organization in ThisBuild := "com.example"
version in ThisBuild := "1.0-SNAPSHOT"

// the Scala version that will be used for cross-compiled libraries
scalaVersion in ThisBuild := "2.11.8"

//lazy val `lagom-java-workshop` = (project in file("."))
//  .aggregate(
//    `basket-api`, `basket-impl`
//  )

lazy val `basket-api` = project("basket-api")
  .settings(common: _*)
  .settings(
    libraryDependencies ++= Seq(
      lagomJavadslApi,
      lombok
    )
  )

lazy val `basket-impl` = project("basket-impl")
  .enablePlugins(LagomJava)
  .settings(common: _*)
  .settings(
    libraryDependencies ++= Seq(
      lagomJavadslTestKit,
      lagomJavadslJackson,
      lombok
    )
  )
  .settings(lagomForkedTestSettings: _*)
  .dependsOn(`basket-api`)

def project(id: String) = Project(s"${id}", base = file(id))
   .settings(javacOptions in compile ++= Seq("-encoding", "UTF-8", "-source", "1.8", "-target    ",     "1.8", "-Xlint:unchecked", "-Xlint:deprecation"))
   .settings(jacksonParameterNamesJavacSettings: _*)

val lombok = "org.projectlombok" % "lombok" % "1.16.10"

def common = Seq(
  javacOptions in compile += "-parameters"
)

// See https://github.com/FasterXML/jackson-module-parameter-names
lazy val jacksonParameterNamesJavacSettings = Seq(
  javacOptions in compile += "-parameters"
)