#!/usr/bin/bash

curl -X POST -H 'Content-Type: application/json' -d '{
     "username" = "'${1}'",
     "email" = "'${2}'",
     "password" = "'${3}'"
}' http://localhost:8080/WebShop/api/account
