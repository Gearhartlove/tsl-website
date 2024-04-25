# Backender's Spellbook : my response to your request
How should the backend respond to a request? 

This question has no objectively right answer. It really depends on the situation. But I'm going to 
try to pitch my philosophy in **give them everything** and **maintain structure**. 

caveat : if you have sensitive information, then of course redact that information; this data should never be given to the client to begin with.

## Example : Pet Shelter 
In this fictitious example, I work as a volunteer software developer at an dog animal shelter. I need to 
keep track of every dog that comes into the shelter for legal and bookkeeping purposes. We have a 
website that shows the names and pictures of our pets. 

On the backend, we collect a lot of information about each pet. This includes : 
- name 
- breed
- location-found
- start-date
- leave-date
- isAdopted
- s3ImageId
(this could go on for quite a while ...)

As mentioned earlier, the website needs to render a picture of the pet along with their name. 

In an attempt to **give them everything**, the UI will fetch all the pets in the database (footnote 1), and
the backend will respond with a list of pets. This list of pets will include ALL the information for each pet in the database. 
My point is that I don't need or want to conform to the requirements of the frontend; 
even though the name and picture are the only requirements, I still give them info about the location-found, start-date, etc...
It is realistic and responsible to expect changes in your application. 
The front end could decide that want to list the breed and list when the pet first arrived. If I did not return all the information to 
begin with, then I would need to change the contract. Changing the contract is a hassle and can be avoided.

To my second point in **maintain structure**, there was no custom `ListDogResponse` response class. I just returned all the 
information the `Dog` class had to begin with. I've seen a lot of people implement this idea of an `opaqueClass` or `transparentClass`
which hides or shows data about your class. This confuses me because often the data I care about is missing. 

in summary
- I believe it is necessary and important for the frontend to shape the data they deal with. 
- I don't believe in the backend needing to bend and make compromises and conveniences for the front end. 
- I believe transport `opaque` and `transparent` classes to be confusing and dishonest.

With a broad and accurate transfer of data, the backend and frontend can treat the alike. This will make code paths and
execution behave more similarly across network requests. 

---
(1) I'm ignoring any paging requirements for simplicity. Were a small animal shelter.
