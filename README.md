# HandasaimCTF

### Disclaimer
Yes yes, i know how irresponsible it is to upload my pk (ssl key) to GitHub, but I don't care - this domain expires in Febuary and the key in June so I don't really care

### Deployment
Create a host with Debian/Ubuntu/Whatever and install docker-ce, then run `run.sh`

#### Or
Run a self-contained docker thingy with `run-contained.sh`

## What's HandasaimCTF?
HandasaimCTF is a cybersecurity competition I made for my school (Handasaim), which I intend to turn in as my 5 point bagrut project.

## Challenges
### 0. Infrastructure
I wrote the infrastructure for this CTF, and it features a simple leaderboard, and a simple flag submission system.

### 1. DogePass
This challenge is relatively easy - it gives you a black page, with black text, and you have to use `Ctrl+A` to select the flag.

### 2. MySecret
This challenge is composed of an .exe file (Microsoft Windows Executable), which you can decompile or run `strings` on to get the flag.

### 3. LostPen
This challenge is composed of a PNG file (an image) which has black and white pixels, which one might understand are bits. Converting the whole picture into bits, then bytes, reveals an ascii string which is the flag.

### 4. EasySHAing
This challenge makes the solver think for a second and do some research about SHA1, which they then have to put in a "reversing" website (rainbow tables) which will give them the answer (top 10000 passwords).

### 5. HappyCooking
This challenge is definitely the hardest challenge in the competition. The user is expected to find a recipe which is the flag by using a timing attack - so by looking at a variable that represents processing time the could understand if their search was successful or not.