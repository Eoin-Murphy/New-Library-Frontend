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

- It is not necessary to persist the state between runs of the program -> just using an in-memory DB and not proper persistence
- Book can only be loaned to a single student at a time.
- A student can loan many books at the same time.
- If a student tries to load a book currently loaned to another student their request is rejected.
- There is no time limit on loans and/or late fees.

# Dev Progress:

- Initially just set up a bare-bones Spring Boot App.
- Decided to use H2 in memory database for persistence.
- Defined a Book and Student entities.
- Main application pre-populates Student & Book tables with dummy data.
- Added debug controller to verify entities are persisted:

`curl --request GET --url http://localhost:8080/api/debug/books`

`curl --request GET --url http://localhost:8080/api/debug/students`

- Adding a Loan entity (inc. repo, service & controller). I decided to have a separate entity Loan instead of a one-to-many Student-Loan direct link for two reasons. (1) We want to be able to pull back the loans directly without having to query all students and (2) in a 'real' implementation we would probably want to have some kind of rules applied on loans so they would justify their own entity.