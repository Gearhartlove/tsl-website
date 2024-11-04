# Shaping Goblin Fight Club

![goblin-fight-club-app](../assets/png/gfc-app.png)

[Use Goblin Fight Club now!](https://goblin-fight-club.fly.dev/)

I'm a pathfinder GM. I realized that planning out every session tooth and nail takes a lot of work and stresses me out. I enjoy playing pathfinder the most when I have a looser gaming experience where players can do what they want. I really like combat, and I want players to engage other monsters in combat. Sometimes it's good to encourage improvisation and non-script combat. 

I needed a way to **quickly generate a balanced encounter**. Goblin Fight Club looks to fill this niche by specifying the party member's levels, the desired combat difficulty, and the type of battle. With a single click of a button I can have balanced encounters for the party.

When approaching this project I really thought about what I needed. I embraced [37 signal's Shape Up](https://basecamp.com/shapeup) product development strategy. It's tag line is "**Stop Running in Circles and Ship Work that Matters**". The methodology encourages thinking and *shaping* work before jumping into code. The beginning steps of a project include spending time creating a pitch to give to a theoretical team (in my case I was convincing myself this was worth while). This pitch includes *rough* ***fat marker*** drawings, pitfalls, rabbit-holes, and well thought out solutions BEFORE resources are invested in a project. 

This blog will document my experience with **Shape Up** through specific examples building Goblin Fight Club. (I won't include the entire process as this blog won't be published if that's the case haha)

## The Pitch
How many of you reading this believe in your ideas? How many of you reading this want to contribute to designs in a more meaningful way? How many of you have tried but fallen short in these aspirations?


A pitch is designed to encapsulate a well thought out solution to a problem. The intention is to share the idea with other developers and cultivate an understanding around a sticky issue.

In order to write start a pitch it's important to **set boundaries**.

### Setting Boundries
A concept **Shape Up** describes is setting an appetite for a problem. An appetite is a team's eagerness and willingness to work on something. To some of you in the workplace, this may sound like Jira sprint pointing, but it's actually almost the opposite. 

An appetite starts with a number, and ends with a body of work. A [Jira](https://www.atlassian.com/software/jira) issue starts with a body of work and ends with a number. 

The key here is that sometimes something needs to get done, but to what degree? How great does it need to be? Wouldn't it be better if developers worked their best work on things they cared about? That's the basic idea here. 

Anyways, my appetite for goblin fight club was high. This is a real problem that other game systems like kobold fight club have already solved and I needed. This was not re-inventing the wheel so I knew this idea was solvable, and I wanted it for my weekly pathfinder sessions. 

### Fixed Time, Variable Scope
It's important to set a deadline. If a project is not bounded by time, it will often never complete. A deadline is not your enemy, it is your timer for you to take those cookies out of the oven (their going to burn if you keep whipping away at the unplanned magical frosting - like aren't they sweet enough as is?). 

**Shape Up** recommends 6 weeks from start to finish for larger teams, so that's what I chose. As of writing it took me 3 weeks to wrap this up, as I cut down on the scope and became better friends with Reality. 

This gets into the idea of a variable scope. Be OK with change and adjusting the initial design. It's hard to know what the best solution is, if you have never been in the head space before. That was the case for me and I made many changes, including: 
- no totally random encounters, just use templates
- just render the monster name and level, I don't need a monster viewer
- I'm OK with a basic fly.io domain, I don't need to spend cycles on this
- the first pathfinder bestiary is good enough for me, I don't need all the monsters ever
- ... (their is a lot more that could go here) ...

The key is that you really don't know until you know with some things, and that's OK. 

### Breadboarding & Fat Marker Sketches
It's great to talk about an idea, but what does it actually look like. How can we work on that? The key here is to breadboard and draw with fat markers.

Breadboarding is a concept from electrical engineering that has all the components and wiring of a real device but not industrial design. The idea is to "sketch and discuss the key components and connections of an interface idea without specifying a particular visual design" ([source](https://basecamp.com/shapeup/1.3-chapter-04#breadboarding)).

There are three categories to breadboarding, including *places* (things you can navigate to), *affordances* (buttons + inputs), and connection lines (which *affordances* take to which *place*).

Here was my scrappy list: 
- places: 
    - starting page
    - ~~render of monster on right side~~ (cut)
- affordances: 
    - party level
    - party count
    - ~~encounter difficulty rating~~ (cut)
- connection lines: none, I only have one page

This simple list helped me narrow down on the inputs from client to server. 

The **Fat Marker Sketch** takes theses breadboarding concepts and looks to draw them out with little detail. The idea is that the marker is so fat that you are not able to write words and use squiggles and shapes instead. Here is what my fat marker drawing looked like. The selection is on the left and the monster rendering is on the right. (note: this design will change again in the future - a common theme of flexibility)

![goblin-fight-club-fat-marker-sketch](../assets/png/gfc-fat-marker.png)

### Risks and Rabbit Holes
When you evaluate a solution it's important to think of what could trip you up. Rabbit Holes are bodies of work that you could spend too long on. They are problems that demand time for different reasons. It could be hard, new territory, or not feasible to begin with; It's hard to know *exactly* on project a project's inception, but it's somewhat easy to scope out. I asked myself a number of question so to expose possible rabbit holes on this project. I cut scope on displaying the recommended monsters. Other websites have excellent renders of the monsters and parsing monsters differently to display would have added a good bit of complexity to the design and database schema. 

### High Fidelity Sketch
It's useful to create a more detail sketch once you are presenting a pitch. Here's what I came up with using the excellent FigJam.

![goblin-fight-club-figjam](../assets/png/gfc-figjam.png)


## Development
I took on a number of additional challenges with this project. I'm proud of my decisions and I'm excited to continue.

### Tech Stack
First and foremost, I used the excellent Erlang/OTP framework through the Elixir programming language. Elixir is a simple functional programming language that levelarges the BEAM virtual machine. 

The Phoenix web framework is the best way to develop applications using Elixir.

Phoenix comes with Tailwind pre-bundled and setup which made developing with Tailwind a breeze. 

It’s common today for web applications to seamlessly replace dom elements with web-socket communication. I decided to not use this, as it would overcomplicated my goal and I stuck to using a form. This worked out great for what I was trying to do. 

For my database I used Postgres. I have one table with five-ish columns so it really wasn't that complicated to set up. I have a setup "seed" script which de-serializes monsters and adds each as a row in my database. 

For my deployment platform, I used fly.io. This worked out great for me. I was overall really impressed with the level of support and boilerplate setup for me already. A Phoenix web application is one of their well supported applications so everything worked like a breeze. I'm excited to explore this platform more. It feels great for getting something of the ground and flying (get it --- "fly".io). There is a lot more that goes into fly like SSHing into you machine, connecting to a live process via IEX, pulling up metrics, and finding your files on your live machine. I worked through these snags and feel great about using this platform to get my ideas out the door!

For branding with the logo, I found an SVG online and tweaked it with the excellent open source Inkscape. I added some ears and made the goblin green. I'm really happy with this result. 

### All finished! 
I could spend a lot more on this blog just like I could spend a lot more on this project. I'm at a point in my life where I want to grow by creating. I need to actually finish the projects that I've given myself so that I can start on something else, using the knowledge and experience I have gained thus far. Cheers to many more DEV hours!
