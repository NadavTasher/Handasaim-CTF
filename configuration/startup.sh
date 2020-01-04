#!/bin/sh
docker build /home/infrastructure -t infrastructure
docker build /home/challenges/EasyShaing -t easyshaing
docker build /home/challenges/HappyCooking -t happycooking
docker run --rm -d -p 1000:80 easyshaing
docker run --rm -d -p 2000:80 happycooking
docker run --rm -d -p 80:80 -p 443:443 infrastructure
