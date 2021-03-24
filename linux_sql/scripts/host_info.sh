#!/bin/bash

psql_host=$1;
psql_port=$2;
db_name=$3;
psql_user=$4;
psql_password=$5;

hostname=$(hostname -f)
cpu_number=$(lscpu | egrep "^CPU\(s\):" | awk '{print $2}' | xargs)
cpu_architecture=$(lscpu | egrep "^Architecture:" | awk '{print $2}' | xargs)
cpu_model=$(lscpu | egrep "^Model:" | awk '{print $2}' | xargs)
cpu_mhz=$(lscpu | egrep "^CPU MHz:" | awk '{print $3}' | xargs)
l2_cache=$(lscpu | egrep "^L2 cache:" | awk '{print substr($3, 1, length($3)-1)}' | xargs)
total_mem=$(cat /proc/meminfo | egrep "^MemTotal\:" | awk '{print $2}' | xargs)
timestamp=$(date +"%Y-%m-%d %H:%M:%S")

insert_stmt="INSERT INTO host_info(hostname,cpu_number,cpu_architecture,cpu_model,
cpu_mhz, l2_cache, total_mem, timestamp) VALUES('$hostname',$cpu_number,'$cpu_architecture','$cpu_model',
$cpu_mhz, $l2_cache, $total_mem, '$timestamp'),
('$hostname',2,'$cpu_architecture','$cpu_model',$cpu_mhz, $l2_cache, 3452, '$timestamp'),
('$hostname',3,'$cpu_architecture','$cpu_model',$cpu_mhz, $l2_cache, 4512, '$timestamp'),
('$hostname',1,'$cpu_architecture','$cpu_model',$cpu_mhz, $l2_cache, 2165, '$timestamp'),
('$hostname',2,'$cpu_architecture','$cpu_model',$cpu_mhz, $l2_cache, 9023, '$timestamp'),
('$hostname',3,'$cpu_architecture','$cpu_model',$cpu_mhz, $l2_cache, 4521, '$timestamp'),
('$hostname',2,'$cpu_architecture','$cpu_model',$cpu_mhz, $l2_cache, 8734, '$timestamp'),
('$hostname',3,'$cpu_architecture','$cpu_model',$cpu_mhz, $l2_cache, 6741, '$timestamp'),
('$hostname',1,'$cpu_architecture','$cpu_model',$cpu_mhz, $l2_cache, 1254, '$timestamp');"

export PGPASSWORD=$psql_password
psql -h $psql_host -p $psql_port -U $psql_user -d $db_name -c "$insert_stmt"
end