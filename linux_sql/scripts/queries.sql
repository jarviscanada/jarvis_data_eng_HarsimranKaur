SELECT cpu_number,id,total_mem from host_info 
ORDER BY cpu_number,total_mem DESC;

SELECT AVG(7492108-memory_free),timestamp FROM (SELECT memory_free,date_trunc('hour', timestamp) + date_part('minute', timestamp):: int / 5 * interval '5 min' as timestamp FROM host_usage) as ss 
GROUP BY timestamp 
ORDER BY timestamp;

SELECT timestamp,COUNT(timestamp) 
FROM (SELECT host_id,date_trunc('hour', timestamp) + date_part('minute', timestamp):: int / 5 * interval '5 min' as timestamp FROM host_usage) as ss 
GROUP BY timestamp 
HAVING COUNT(timestamp)<3 
ORDER BY timestamp;
