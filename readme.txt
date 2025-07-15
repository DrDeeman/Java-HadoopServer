 1) docker-compose up --build -d
 2) docker exec -it hive-server /bin/bash
 3) beeline -u jdbc:hive2://localhost:10000/default -n root -p root -f /data/data.sql
 
 check data(optional)
 1)docker exec -it namenode /bin/bash
 2)hadoop fs -ls /user/hive/warehouse/test_hive.db
