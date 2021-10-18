# account-manager

provided 2 apis:

1. `/api/account/{accountNumber}` for retrieving balance
2. `/api/account/transfer` for transferring balance

transfer balance operation need to be locked while transferring money between customers because we need to ensure the data correctness. therefore, when one customer trying to transfer money to the account, other customers need to wait for the customer who is editing the account to complete its job
first.

for banking operation, sometimes we need to let customers know why the operations are failed, so the design of `transfer` api will return specific error code to let customers know why his/her operation failed.
