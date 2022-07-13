# Coding_Assignment
Assignment to create a trade store with validations


This uses a in-memory H2 database.

Post command to insert data to the datastore :

curl -H "Content-Type: application/json" -X POST -d {\"tradeId\" : \"T1\",\"version\":3,\"counterPartId\":\"CP-1\",\"bookId\":\"B3\",\"maturityDate\":\"11/07/2022\"} http://localhost:8080/Trade/addItem


Testcases tested :

1) Discarding lower version of the same trade.
2) Updating the record if the version is the same.
3) Discarding the record if the maturity date is less than today.
4) Scheduled job to update the expiry which is run everyday mid night at 23:59 pm to update all records expiring today.
