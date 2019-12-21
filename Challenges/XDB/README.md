# XDB

XDB is an information security challenge in the Miscellaneous category, and was presented to participants of [KAF CTF 2019](https://ctf.kipodafterfree.com)

## Challenge story

We need you to hack this phonebook for us - we got inside information about the database, and we included the user manual for it. [XDB User Manual.pdf]

## Challenge exploit

Database query injection

## Challenge solution

No need

## Building and installing

[Clone](https://github.com/NadavTasher/2019-XDB/archive/master.zip) the repository, then type the following command to build the container:
```bash
docker build . -t xdb
```

To run the challenge, execute the following command:
```bash
docker run --rm -d -p 1090:80 xdb
```

## Usage

You may now access the challenge interface through : `nc localhost 1090`

## Flag

Flag is:
```flagscript
KAF{s1mpl3_and_1ns3cure_}
```

## License
[MIT License](https://choosealicense.com/licenses/mit/)