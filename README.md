# movies_app

This project consumes an external API ```https://dev-candidates.wifiesta.com/```, it uses a Clean Architecture to follow the SOLID principles.

### It also uses:

- Retrofit to make server calls
- MVVM pattern architecture
- Repository pattern as intermediate for getting the data from a single source, a later implementation can be with Room schemas to add more persistency to app
- Dagger2 for dependency injection
- Coroutines to get suspend responses from API
- Glide for picture loading

Splash screen gets a token the first time app is opened (This can be replaced by a simple email screen so token is adquired with user's email), after that it moves to MoviesList automatically.

Unit testing will be added later.
