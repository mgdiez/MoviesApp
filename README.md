# MoviesApp [![Build Status](https://travis-ci.org/thebestpol/MoviesApp.svg?branch=master)](https://travis-ci.org/thebestpol/MoviesApp) [![codecov](https://codecov.io/gh/thebestpol/MoviesApp/branch/master/graph/badge.svg)](https://codecov.io/gh/thebestpol/MoviesApp) [![Quality Gate](https://sonarcloud.io/api/project_badges/measure?project=thebestpol_MoviesApp&metric=alert_status)](https://sonarcloud.io/dashboard?id=thebestpol_MoviesApp) [![ktlint](https://img.shields.io/badge/code%20style-%E2%9D%A4-FF4081.svg)](https://ktlint.github.io/)
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
#### Second: The project foundation
* Designed an ***User Story***, created to move Activity responsibilities as state changes, communication between fragment and the navigation. The main reason of this is to allow unit testing 
isolated from the Android Framework and improve the sociable unit tests. This design consist in a set of entities as:
    * ***StoryScreen***: Entity that defines the screen view and where will be placed.
    * ***StoryScreenContainer***: Entity that handles the screens.
    * ***UserStory*** handles the state and their child will define the navigation for each User Story.
    * ***StoryState*** the Story state.
* Added ***Dagger 2*** dependency to the project and created the basic configuration, defined custom scopes.
* Added ***RxJava 2*** and ***RxKotlin*** dependencies to the project, the architecture will be based on Reactive-Streams.
* Created an base User Story Activity and Application.
#### Third: Movies User Story
* Implemented all related entities to create the MoviesStory. Created a MoviesActivity that will contain the fragments. The activities will be only as a Fragment containers, created layout for movies activity as a container with a CoordinatorLayout.
* Added Mockito and Mockito Kotlin dependencies to the project and some unit testing for the movies story.
### Fourth: Movies List 
* Everything related to the presentation is defined in the MovieListContract, implemented each one and tested. We are using a MVP contract "vitaminated" with State and Navigation.
    * View and Presenter implementations dumb as possible. The presenter updates the state and the view render the model.
    * The ***State*** for the current view that is handled by the presenter and saved/restored by the UserStory, contains all the necessary data as movies, page and total pages.
    * The navigation callback that needs the screen, this callbacks are implemented in the MovieStory
* Created a **UseCase** to obtain paginated movies, defined a MovieRepository and executing using reactive-streams. This could we considered de domain layer of this project, so everything related are in the domain package, as bussines objects and other definitions.
* Added Robolectric dependency to the project to make UI unit testing: **MovieListFragmentTest** and mocking dependencies with DaggerMock, this allows isolated fragment testing.
* Implemented a sociable tests for the **MovieListPresenter** with the **GetMoviesPageUseCase** and solitary test for the others entities, the usecase is not tested due is an "interactor" doesn't have any business logic.
* Created custom test rules to allow mock in the injected dependencies for Robolectric and JUnit.
* Handling screen orientation, storing and restoring the state without data loss. Also the screen is rendering in different ways for landscape or portrait.
* Strong and advanced use of Dagger to inject dependencies everywhere.
* Refactorized first iteration of user story
* Added Lottie dependency to show custom animation for the error and the loading views.
    