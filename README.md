# Asteroid Radar (early snapshot)

> **The complete version of this project is [darsh-7/Asteroid](https://github.com/darsh-7/Asteroid).** It adds the daily WorkManager sync, the today/week filter and accessibility fixes.

An early (September 2022) snapshot of the Udacity **Asteroid Radar** project. It shows near-Earth asteroids from NASA's NeoWs API and the Astronomy Picture of the Day.

**What's in this snapshot:** Retrofit (Scalars + Moshi), Room cache exposed as `LiveData`, MVVM with a repository, a RecyclerView `ListAdapter`, a detail screen via Navigation Safe Args, Data Binding adapters and Picasso. Unlike the complete version, the build uses Android Gradle Plugin 7.0.3, Kotlin 1.5.31 and AndroidX artifacts.

**Known gap:** the asteroid feed request in `api/ApiMang.kt` doesn't send a valid API key, so only the picture of the day loads. It's fixed in the complete version.

**Run it:** clone `https://github.com/darsh-7/Asteroid-NASA-API.git`, open it in Android Studio, put your [api.nasa.gov](https://api.nasa.gov) key in `Constants.API_KEY`, and run `app` (minSdk 21).

Built for Udacity's Android Kotlin Developer Nanodegree.

**Author:** Mostafa Ahmed · [GitHub @darsh-7](https://github.com/darsh-7) · [LinkedIn](https://www.linkedin.com/in/darsh7/)
