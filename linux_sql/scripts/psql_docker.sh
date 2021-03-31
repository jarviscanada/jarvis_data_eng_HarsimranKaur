#!/bin/bash
command=$1
username=$2
password=$3
sudo systemctl status docker || systemctl start docker
case $command in

create)
  line_count='docker container ls -a -f name=jrvs-psql | wc -l'
  if [ $line_count -eq 2 ] ;
  then
  echo 'Created Already'
  exit 0
  fi

  if [ -z "$username" ] || [ -z "$password" ] ;
  then
    echo 'Username and Password not submitted'
    exit 0
  else
    docker run --name jrvs-psql -e POSTGRES_PASSWORD=${password} -e POSTGRES_USER=${username} -d -v pgdata:/var/lib/postgresql/data -p 5432:5432 postgres
    exit $?
  fi
  ;;
start)
  docker start jrvs-psql
  exit $?
  ;;
stop)
  docker stop jrvs-psql
  exit $?
  ;;
*)
  echo 'Invalid Command Line Argument'
  ;;
esac
end