# Documenation
- Books in reading list should be set (to remove duplicates)
- Genre should be unique in books 
- Deleting a User should delete a Reading List, but not delete Books in the Reading List

## Relationships
### Unidirectional
- Books -> Authors
- Users -> Reading List
- Reading List -> Books 

### Bidirectional
- Books <-> Genre

## Endpoints
- GET /api/books: return just book information, no author or genre
- GET /api/books/{id}: return book with the Author & Genre
- POST /api/books: accepts a Book with all possible fields(Author & genre)
- PUT /api/books/{id}: accepts a Book with all possible fields
- DELETE /api/books/{id}: returns just book information, NO Author & Genre
- GET /api/genre/{id}/books: returns just book information, NO Author & Genre
- POST api/users: accepts all user fields, including Reading List
- DELETE api/users/{id}: returns basic user information
- GET /api/users/{id}/reading_lists: 
- POST /api/users/{id}/reading_lists: Array of Reading List w. Basic Book Information
- GET /api/users/{id}/reading_lists/{list_id}: Reading List w. Advanced Book Information

## Validation
### User
- All fields should be @NotNull
    - RequestUserDTO with both fields
    - ResponseUserDTO with only one username field
### ReadingList
- All fields @NotNull
### Author
- All fields @NotNull
### Book
- Author should @NonNull
- published should be a valid Date (@Temporal), else make it null. It allowed to be null (some books have no known published dates)
- Pages must have @Min(0) and default of 0 if empty
-  published is empty give it the default value of today