#!/bin/bash

psql_host=$1;
psql_port=$2;
db_name=$3;
psql_user=$4;
psql_password=$5;

timestamp=$(date +"%Y-%m-%d %H:%M:%S")
memory_free=$(cat /proc/meminfo | egrep "^MemFree\:" | awk '{print $2}' | xargs)
cpu_idle=$(vmstat -t|tail -1|awk '{print $15}')
cpu_kernel=$(vmstat -t| tail -1|awk '{print $14}')
disk_io=$(vmstat --unit M | tail -1 | awk '{print $9}')
disk_available=$(df -BM | awk 'NR==2{print substr($4, 1, length($4)-1)}')

insert_stmt="INSERT INTO host_usage(timestamp,memory_free,cpu_idle,cpu_kernel,disk_io,disk_available)
VALUES('$timestamp', $memory_free, $cpu_idle, $cpu_kernel, $disk_io, $disk_available);"

export PGPASSWORD=$psql_password
psql -h $psql_host -p $psql_port -U $psql_user -d $db_name -c "$insert_stmt"
end