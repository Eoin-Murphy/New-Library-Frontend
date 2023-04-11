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


# Dev Progress:

- Initially just set up a bare-bones Spring Boot App.
- Decided to use H2 in memory database for persistence.
- Defined a Book and Student entities.
- Main application pre-populates Student & Book tables with dummy data.
- Added debug controller to verify entities are persisted:

`curl --request GET --url http://localhost:8080/books`

`curl --request GET --url http://localhost:8080/students`