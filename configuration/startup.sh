#!/usr/bin/env bash
docker build infrastructure infrastructure
docker build challenges/EasyShaing easyshaing
docker build challenges/HappyCooking happycooking
docker run --rm -d -p 1000:80 easyshaing
docker run --rm -d -p 2000:80 happycooking
docker run --rm -d -p 80:80 -p 443:443 infrastructure
