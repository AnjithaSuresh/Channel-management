# Channel-management
A GUI application for managing a TV channel which deals with list of programs running in the channel, advertisements of sponsors and time allotted for each.

Specifications

1.	There are two actors, a TV channel program manager and an advertisement manager. Both of them have userid and password to login.
2.	TV channel program manager can add programs to be played, time at which it is airing, category of programs and the time allotted for each program.
3.	He/she can also delete any program wherever required.
4.	Programs are categorized into news and entertainments.
5.	There is a separate list of programs for weekends and weekdays. Separate schedule for weekends and weekdays is created.
6.	Entertainment programs are given more priority during weekends.
7.	Advertisements played in the channel are categorized into two; advertisements of channel sponsors and advertisements of specific show sponsors.
8.	Advertisement manager adds the ad list, with its duration, whether it is a channel ad or program ad.
9.	If it is a program ad, we have to get the priority and the programs they are sponsoring. Higher priority for the main sponsors.
10.	If it is a channel ad, we get the year till which the contract is valid.
11.	If the program corresponding to the advertisement sponsoring it, is not present, we return an error message and the ad manager has to resend the list.
12.	For movies, a fixed time of three and half hours is allotted and advertisements are inserted in between them according to this time.
13.	One hour is allotted for news and one hour for all other kind of shows like reality shows, music shows and talk shows.
14.	Every 15 minutes, we insert an advertisement break of 3 minutes. So total 3 times in one-hour programs.
15.	For every program, both channel sponsor’s advertisement and those of the show sponsors are played. If there are not enough show sponsors, channel sponsor’s ads are played to fill the time.
