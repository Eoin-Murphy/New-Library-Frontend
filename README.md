# New-Library-Frontend

Homework assignment for Comyno

# FRONTEND (WEB OR JAVA)

University IT system requires New Library Fronted (NLF), to allow librarian to keep track of book loans.
NLF must support these actions:

- Library issues (loan) the book to student
- Student returns the book to library
- NLF must display all books currently borrowed by students.

We would like to see working solution and review code, project structure, tests. Please consider one of
these two options:

- Host your project online (example surge.sh), create and share your git repository
- (or) Send, via email, project sources and instructions how to start it

# Assumptions:

- It is not necessary to persist the state between runs of the program -> just using an in-memory DB and not proper
  persistence
- Book can only be loaned to a single student at a time.
- A student can loan many books at the same time.
- If a student tries to load a book currently loaned to another student their request is rejected.
- There is no time limit on loans and/or late fees.
- Deleting a loan should not delete either Book or Student
- Book/Student CRUD is not part of the assignment

# Dev Progress:

- Initially just set up a bare-bones Spring Boot App.
- Decided to use H2 in memory database for persistence.
- Defined a Book and Student entities.
- Added debug controller to verify entities are persisted:
- Debug controller populates Student & Book tables with dummy data when this endpoint is called:

`curl --request GET --url http://localhost:8080/api/debug/initDb`

- Debug controller clears all tables when this endpoint called:

`curl --request GET --url http://localhost:8080/api/debug/initDb`

- These endpoints return the lists of current books/students respectively

`curl --request GET --url http://localhost:8080/api/debug/books`

`curl --request GET --url http://localhost:8080/api/debug/students`

- Adding a Loan entity (inc. repo, service & controller). I decided to have a separate entity Loan instead of a
  one-to-many Student-Loan direct link for two reasons. (1) We want to be able to pull back the loans directly without
  having to query all students and (2) in a 'real' implementation we would probably want to have some kind of rules
  applied on loans, so they would justify their own entity.
- I spend a *lot* of time trying to get data structure unit tests working.
  So having defined the book/student/loan foreign keys in the entity fields, when I try to create a loan between a
  student and a book it works fine. But when I retrieve either the student or book, the loan is null.
- Jumped ahead to full system tests, and it all seems to be working as expected
- I do not have endpoints for adding/removing either books or students as they were not specified.
- The debug controller would have to be deleted before any deployment to live environments.

After download it can be built with `mvn package` and the generated jar file run as usual from a comand line `java -jar nlf-0.0.1-SNAPSHOT.jar`

Helpful API endpoints:

- db Init populates with some dummy data: 

`curl --request POST  \
  --url http://localhost:8080/api/debug/dbInit`
  
- db clear drops everything: 

`curl --request DELETE \
  --url http://localhost:8080/api/debug/clearAll`

- list all students:

`curl --request GET \
  --url http://localhost:8080/api/debug/students`

- list all books:

`curl --request GET \
  --url http://localhost:8080/api/debug/books`

- list all current loans: 

`curl --request GET \
  --url http://localhost:8080/api/loans`

- loan a book:

`curl --request POST \
  --url http://localhost:8080/api/loans \
  --header 'Content-Type: application/json' \
  --data '{
"isbn":"isbn2",
"studentId":"student2"
}'`

- return a book:

`curl --request DELETE \
  --url http://localhost:8080/api/loans \
  --header 'Content-Type: application/json' \
  --data '{
"isbn":"isbn2",
"studentId":"student2"
}'`
