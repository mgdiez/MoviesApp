# MoviesApp
Simple Android app that displays movies from TMDb.
## Summary
### Workflow
The way of work in this repository is using [GitFlow](https://datasift.github.io/gitflow/IntroducingGitFlow.html) adapted to the situation
* Working by features in a branch per feature from develop.
* Solving bugs in a fix branch per bug from master or in the release branch.
* Each task should create a pull request with minimum description of the job. To merge the pull request should be approved after a code review. In this case we are going to simulate the PR process.


### Iterations
#### First one: Setting up a development environment
* Continuous integration using [Travis](https://travis-ci.org) that enables builds for each push and pull request also run the unit tests and the intrumentation tests.
* Using Jacoco to generate unified test reports and uploading it to [Codecov](https://codecov.io/) to analyze the test coverage of the project.
* Continuous inspection of the code quality using SonarQube in [SonarCloud](https://sonarcloud.io/) to perform automatic reviews static analysis of code to detect bugs, code smells, and security vulnerabilities.
* Checks with a Kotlin linter with built-in formatter [Ktlint](https://ktlint.github.io/).

