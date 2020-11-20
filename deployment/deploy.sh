#!/usr/bin/bash

systemctl status reader.service
sudo systemctl restart reader,service
systemctl status reader.service
