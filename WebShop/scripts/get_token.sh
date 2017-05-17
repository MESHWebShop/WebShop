#!/usr/bin/bash

curl -X POST -H 'Content-Type: application/json' -d '{
     "authentication" = "'${1}'",
     "password" = "'${2}'"
}' http://localhost:8080/WebShop/api/authentication
