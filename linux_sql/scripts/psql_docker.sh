#! bin/bash
sudo systemctl status docker || systemctl start docker
case $1 in
create)
  $line_count = docker container ls -a -f name=jrvs-psql|wc -l
  if $line_count -eq 2 ;
  then
  echo 'Created Already'
  exit
  fi

  # shellcheck disable=SC1009
  if [-z $2] || [-z $3];
  then
    echo 'Username and Password not submitted'
  else
    docker run --name jrvs-psql -e POSTGRES_PASSWORD=$3 -e POSTGRES_USER=$2 -d -v pgdata:/var/lib/postgresql/data -p 5432:5432 postgres
    echo 'Created Successfully'
  fi
  ;;
start)
  docker start -ti jrvs-psql
  echo 'Started Successfully'
  exit $?
  ;;
stop)
  docker start -ti jrvs-psql
  echo 'Stopped Successfully'
  exit $?
  ;;
esac