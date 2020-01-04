#!/bin/sh
docker rm -f $(docker ps -aq)
docker build infrastructure -t infrastructure
docker build challenges/EasyShaing -t easyshaing
docker build challenges/HappyCooking -t happycooking
docker run --rm -d -p 1000:80 easyshaing
docker run --rm -d -p 2000:80 happycooking
docker run --rm -d -p 80:80 -p 443:443 infrastructure