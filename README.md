# TheBeerApp

This is a final project for my Mobile devices course created by me in december 2021. I submitted the initial prototype for the application as my final project and recieved a 100%.

This app then evolved into a project that I have just been working on for fun someday I might publish it to the appstore. 

[Here's a link to a video demostrating the current functionality for january 2022](https://www.youtube.com/watch?v=TPls3t7gjEQ)

If you want to add anything to it or help work on it or have any questions feel free to submit a PR or call or text me at (317)-201-1994. Or if you see a but feel free to open an issue.

***Im also always open to work on cool open source projects***

Current functionality:
- Hits the openBreweryDB api and loads the results into a recycler view. 
- The cells inside the recycler view have an onClickListener where they can be selected and it opens a page for the specific brewery. The way I pass data from activity to activity is a bit redundant and sketchy but for the prototype I was under the gun to get it working. This has been added to the backlog.
- When you select an individual cell you can call the phone number for the brewery, open the gps app to the brewery location, and open the breweries website. provided they were reterned by the api as some breweries are missing info for some or all of thier information. 
- Inside the selected cells activity for a specific brewery you can also save the brewery to your favorites which is saved to persisting memory on the android device. So you can close the app and open it or turn the phone on and off and it will still be there. It does this using mySqlLite.


Backlog: 

- Encapsulate data into an object and just pass arrays of objects instead of like a million individual arrays between activities.

- Have username and password sign in. 
