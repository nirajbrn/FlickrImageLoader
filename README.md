# Flickr Android
## Architecture
```

                                       +--------------------+
                                       |                    |
                   +------------------->      Domain        |
                   |                   |                    <------------------+
                   |  +----------------+  (Business Logic)  +----------------+ |
                   |  |                |                    |                | |
                   |  |                +--------------------+                | |
                   |  |                                                      | |
                   |  |                                                      | |
         +---------+--v---------+                                  +---------v-+-----------+
         |                      |                                  |                       |
         |       Data           |                                  |        App            |
         |                      |                                  |                       |
         | (Data Layer Logic)   |                                  +   (Android APP Code)  |
         |                      |                                  |                       |
         +----------------------+                                  +-----------------------+
```

### Domain
Domain is a plain java library containing all the business logic related with App. This can be
considered as the blueprint of the app. None of the implementations are available inside this
library.

### Data
This is data layer of the Android framework. This module has different packages for different
types of communications. In app, currently there are 1 types of Data transactions.
 API calls, which is implemented inside ```api``` package.

### App
This module contains app related code.
