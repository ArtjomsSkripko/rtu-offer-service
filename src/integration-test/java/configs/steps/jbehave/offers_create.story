Scenario: Create intercity offers
Given offer request for trip from RIGA to DAUGAVPILS
And add ticket type SINGLE_TICKET
And add passenger type ADULT
And add transport type BUS
And add company name NORDEKA
And set number of tickets to 1
When call create offers
Then response status is OK
And response has 1 offer(s)
And response has no regional offers
And response has intercity offers

Scenario: Create regional offers
Given offer request for trip from RIGA to RIGA
And add ticket type SINGLE_TICKET
And add passenger type ADULT
And add transport type BUS
And add company name RIGAS_SATIKSME
And set number of tickets to 1
When call create offers
Then response status is OK
And response has 2 offer(s)
And response has regional offers
And response has no intercity offers

Scenario: Create mixed offers
Given offer request for trip from RIGA to DAUGAVPILS
And add ticket type SINGLE_TICKET
And set departure street name Slokas iela and home number 22
And set arrival street name K.Valdemara iela and home number 6
And add passenger type ADULT
And add transport type BUS
And set number of tickets to 1
When call create offers
Then response status is OK
And response has 1 offer(s)
And response has regional offers
And response has intercity offers

Scenario: Create mixed offers failure
Given offer request for trip from RIGA to DAUGAVPILS
And add ticket type SINGLE_TICKET
And set departure street name Rigas iela and home number 1234
And set arrival street name Nepareiza iela and home number 44
And add passenger type ADULT
And add transport type BUS
And set number of tickets to 1
When regional service will return error
And call create offers
Then response status is NOT_FOUND

