#!/bin/bash
docker rm -f $(docker ps -aq)
docker build . -t handasaimctf
docker run -d --privileged --name handasaimctf -p 443:443 -p 80:80 -p 1000:1000 -p 2000:2000 handasaimctf
sleep 10
docker exec handasaimctf /bin/sh -c "cd /home && /home/run.sh"